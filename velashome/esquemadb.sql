create table Fornecedor(
    forId SERIAL primary key,
    forCnpj VARCHAR(14) not null,
    forRazaoSocial VARCHAR(50) not null,
    forEmail VARCHAR(50) not null,
    forTelefone VARCHAR(11) not null,
    forLogradouro VARCHAR(50) not null,
    forNumero INTEGER not null,
    forCep VARCHAR(8) not null,
	forCidade VARCHAR(50) not null,
    forEstado VARCHAR(2) not null
);

create table Produto(
    proId serial primary key,
    proNome VARCHAR(50) not null,
    proPreco FLOAT not null,
    proCategoria INTEGER not null,
    forId INTEGER not null
);

create table Estoque(
    estId serial primary key, 
    estQuantidade INTEGER not null, 
    estLocal VARCHAR(50) not null,
    estDataEntrada DATE not null,
    estDataValidade DATE not null,
    proId INTEGER not null
);

create table funcionario (
    funId INTEGER primary key,
    funNome VARCHAR(50) not null,
    funSobrenome VARCHAR(50) not null,
    funCpf VARCHAR(11) not null,
    funTelefone VARCHAR(11) not null,
    funDepartamento VARCHAR(50) not null,
    funSalario FLOAT not null
);
-- Adicionei ponto e vírgula aqui

create table transportadora (
    traId INTEGER primary key,
    traCnpj VARCHAR(14) not null,
    traRazaoSocial VARCHAR(50) not null,
    traEmail VARCHAR(50) not null,
    traTelefone VARCHAR(11) not null,
    traLogradouro VARCHAR(50) not null,
    traNumero INTEGER not null,
    traCep INTEGER not null,
    traCidade VARCHAR(50) not null,
    traEstado VARCHAR(50) not null
);

create table auditoria (
    audId SERIAL primary key,
    funId INTEGER,
    proId INTEGER,
    datahora DATE
);
-- Adicionei ponto e vírgula aqui


alter table Produto
add constraint fk_produto_fornecedor
foreign key (forId) references Fornecedor(forId);

alter table Estoque
add constraint fk_estoque_produto
foreign key (proId) references Produto(proId);

alter table Auditoria
add constraint fk_auditoria_funcionario
foreign key (funId) references Funcionario(funId);

alter table Auditoria
add constraint fk_auditoria_produto
foreign key (proId) references Produto(proId);

create view ver_produto_estoque as
select
	*
from
	produto
natural join estoque
where
	estoque.estquantidade > 0;

create or replace
function add_produto_estoque(
    proNome VARCHAR(50),
    proPreco FLOAT,
    proCategoria INTEGER,
    forId INTEGER,
    estQuantidade INTEGER,
    estLocal VARCHAR(50),
    estDataEntrada DATE,
    estDataValidade DATE
) returns VOID as $$
declare new_produto_id INTEGER;

begin 
    if not exists (
select
	1
from
	produto
where
	pronome = proNome
	and prodatavalidade = estDataValidade
    ) then
        insert
	into
	produto (proNome,
	proPreco,
	proCategoria,
	forId)
values (proNome,
proPreco,
proCategoria,
forId);

select
	proid
into
	new_produto_id
from
	produto
where
	pronome = proNome
	and prodatavalidade = estDataValidade
limit 1;

insert
	into
	estoque (proid,
	estquantidade,
	estlocal,
	estdataentrada,
	estdatavalidade)
values (new_produto_id,
estQuantidade,
estLocal,
estDataEntrada,
estDataValidade);
end if;
end $$ language plpgsql;

create or replace
function delete_produto_estoque(proID INTEGER) returns VOID as $$
begin
    delete
from
	estoque
where
	proid = proID;

delete
from
	produto
where
	proid = proID;
end $$ language plpgsql;

create or replace
function upd_produto_estoque(
    proID INTEGER,
    proNome VARCHAR(50),
    proPreco FLOAT,
    proCategoria INTEGER,
    forId INTEGER,
    estQuantidade INTEGER,
    estLocal VARCHAR(50),
    estDataEntrada DATE,
    estDataValidade DATE
) returns VOID as $$
begin
    update
	produto
set
	pronome = proNome,
	propreco = proPreco,
	procategoria = proCategoria,
	forid = forId
where
	proid = proID;

update
	estoque
set
	estquantidade = estQuantidade,
	estlocal = estLocal,
	estdataentrada = estDataEntrada,
	estdatavalidade = estDataValidade
where
	proid = proID;
end $$ language plpgsql;