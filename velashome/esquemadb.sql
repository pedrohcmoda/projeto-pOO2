do $$
declare
    table_name text;

function_name text;

trigger_name text;

begin
-- Dropping all tables
    for table_name in (
select
	information_schema.tables.table_name
from
	information_schema.tables
where
	information_schema.tables.table_schema = 'public'
	and information_schema.tables.table_type = 'BASE TABLE')
    loop
        execute 'DROP TABLE IF EXISTS public.' || table_name || ' CASCADE';
end loop;
-- Dropping all functions
    for function_name in (
select
	routine_name
from
	information_schema.routines
where
	routine_type = 'FUNCTION'
	and specific_schema = 'public')
    loop
        execute 'DROP FUNCTION IF EXISTS public.' || function_name || ' CASCADE';
end loop;
-- Dropping all triggers
    for trigger_name in (
select
	information_schema.triggers.trigger_name
from
	information_schema.triggers
where
	trigger_schema = 'public')
    loop
        execute 'DROP TRIGGER IF EXISTS public.' || information_schema.triggers.trigger_name || ' ON ' || information_schema.triggers.trigger_name || ' CASCADE';
end loop;
end $$;

create table Fornecedora(
    forId serial primary key,
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
    funId serial primary key,
    funNome VARCHAR(50) not null,
    funSobrenome VARCHAR(50) not null,
    funCpf VARCHAR(11) not null,
    funTelefone VARCHAR(11) not null,
    funDepartamento VARCHAR(50) not null,
    funSalario FLOAT not null
);

create table transportadora (
    traId SERIAL primary key,
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

alter table Produto
add constraint fk_produto_Fornecedora
foreign key (forId) references Fornecedora(forId);

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

select
	*
from
	ver_produto_estoque

create or replace
	function add_produto_estoque(
    p_pronome VARCHAR(50),
	p_propreco FLOAT,
	p_procategoria INTEGER,
	p_forID INTEGER,
	p_estquantidade INTEGER,
	p_estlocal VARCHAR(50),
	p_estdataentrada DATE,
	p_estdatavalidade DATE
) returns VOID as $$
declare
    new_produto_id INTEGER;

begin 
    if not exists (
select
	1
from
	produto
natural join estoque
where
	proNome = p_pronome
	and estDataValidade = p_estdatavalidade
    ) then
        insert
	into
	produto (proNome,
	proPreco,
	proCategoria,
	forId)
values (p_pronome,
p_propreco,
p_procategoria,
p_forID);

select
	proid
        into
	new_produto_id
from
	produto
natural join estoque
where
	proNome = p_pronome
	and estoque.estDataValidade = p_estdatavalidade
limit 1;

if new_produto_id is not null then
        insert
	into
	estoque (proId,
	estQuantidade,
	estLocal,
	estDataEntrada,
	estDataValidade)
values (new_produto_id,
p_estquantidade,
p_estlocal,
p_estdataentrada,
p_estdatavalidade);
end if;
end if;
end $$ language plpgsql;

create or replace
function delete_produto_estoque(proID INTEGER) returns VOID as $$
begin
    delete
from
	estoque
where
	proId = proID;

delete
from
	produto
where
	proId = proID;
end $$ language plpgsql;

create or replace
function upd_produto_estoque(
    proID INTEGER,
    pronome VARCHAR(50),
    propreco FLOAT,
    procategoria INTEGER,
    forID INTEGER,
    estquantidade INTEGER,
    estlocal VARCHAR(50),
    estdataentrada DATE,
    estdatavalidade DATE
) returns VOID as $$
begin
    update
	produto
set
	proNome = coalesce(pronome,
	proNome),
	proPreco = coalesce(propreco,
	proPreco),
	proCategoria = coalesce(procategoria,
	proCategoria),
	forId = coalesce(forID,
	forId)
where
	proId = proID;

update
	estoque
set
	estQuantidade = coalesce(estquantidade,
	estQuantidade),
	estLocal = coalesce(estlocal,
	estLocal),
	estDataEntrada = coalesce(estdataentrada,
	estDataEntrada),
	estDataValidade = coalesce(estdatavalidade,
	estDataValidade)
where
	proId = proID;
end $$ language plpgsql;

insert
	into
	Fornecedora (forCnpj,
	forRazaoSocial,
	forEmail,
	forTelefone,
	forLogradouro,
	forNumero,
	forCep,
	forCidade,
	forEstado)
values ('12345678901234',
'Fornecedor Teste',
'fornecedor@teste.com',
'12345678901',
'Rua Teste',
123,
'12345678',
'Cidade Teste',
'TE');

insert
	into
	Produto (proNome,
	proPreco,
	proCategoria,
	forId)
values ('Produto Teste',
10.99,
1,
1);

insert
	into
	Estoque (estQuantidade,
	estLocal,
	estDataEntrada,
	estDataValidade,
	proId)
values (100,
'Local Teste',
'2023-01-01',
'2023-12-31',
1);

insert
	into
	Funcionario (funNome,
	funSobrenome,
	funCpf,
	funTelefone,
	funDepartamento,
	funSalario)
values ('João',
'Silva',
'12345678901',
'98765432101',
'RH',
5000.00);

insert
	into
	Transportadora (traCnpj,
	traRazaoSocial,
	traEmail,
	traTelefone,
	traLogradouro,
	traNumero,
	traCep,
	traCidade,
	traEstado)
values ('98765432101234',
'Transportadora Teste',
'transportadora@teste.com',
'98765432101',
'Rua Transporte',
456,
'87654321',
'Cidade Transporte',
'TT');

insert
	into
	Auditoria (funId,
	proId,
	datahora)
values (1,
1,
'2023-01-01');

select
	upd_produto_estoque(1,
	'Novo Nome',
	null,
	null,
	null,
	150,
	null,
	'2023-06-01',
	null);

select
	delete_produto_estoque(1);

select
	add_produto_estoque('Produto Novo',
	15.99,
	2,
	1,
	200,
	'Estoque Teste',
	'2023-01-15',
	'2023-12-31');

select
	*
from
	estoque

select
	*
from
	produto
