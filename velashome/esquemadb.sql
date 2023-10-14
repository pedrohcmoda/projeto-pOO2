-- Exclua tudo
CREATE OR REPLACE PROCEDURE droparTudo() AS $$
BEGIN
    -- Exclua as triggers
    DROP TRIGGER trigger_alteracao_estoque ON Estoque;
    DROP TRIGGER trigger_subtrai_estoque ON Venda;
    -- Exclua as funções
    DROP FUNCTION registrar_alteracao_estoque();
    DROP FUNCTION adicionar_produto(VARCHAR, INTEGER, FLOAT, VARCHAR);
    DROP FUNCTION preco_final(INTEGER, INTEGER);
    DROP FUNCTION subtrai_quant_estoque();

    -- Exclua as tabelas
    DROP TABLE Venda;
    DROP TABLE Auditoria;
    DROP TABLE Estoque;
    DROP TABLE Produto;
    DROP TABLE Cliente;
    DROP TABLE Transportadora;
    DROP TABLE Gerente;

END; $$ LANGUAGE plpgsql;





    CREATE TABLE Produto (
        id SERIAL PRIMARY KEY,
        nome VARCHAR(255),
        preco FLOAT,
        fabricante VARCHAR(255)
    );

    CREATE TABLE Estoque (
        id SERIAL PRIMARY KEY,
        produto_id INTEGER REFERENCES Produto(id),
        quantidade INTEGER
    );

    CREATE TABLE Gerente (
        id SERIAL PRIMARY KEY,
        nome VARCHAR(255),
        email VARCHAR(255),
        senha VARCHAR(255)
    );

    CREATE TABLE Auditoria (
        id SERIAL PRIMARY KEY,
        produto_id INTEGER,
        produto_nome VARCHAR(255),
        data_hora TIMESTAMP DEFAULT NOW(),
        acao BOOLEAN
    );

    CREATE TABLE Cliente (
        id SERIAL PRIMARY KEY,
        nome VARCHAR(255),
        telefone VARCHAR(255),
        email VARCHAR(255)
    );

    CREATE TABLE Transportadora (
        id SERIAL PRIMARY KEY,
        nome VARCHAR(255),
        telefone VARCHAR(255),
        cnp VARCHAR(255),
        endereco VARCHAR(255)
    );

    CREATE TABLE Venda (
        id SERIAL PRIMARY KEY,
        cliente_id INTEGER REFERENCES Cliente(id),
        valorTotal FLOAT,
        produto_id INT REFERENCES Produto(id),
        data_hora TIMESTAMP DEFAULT NOW(),
        quantidade_produto INTEGER,
        transportadora_id INTEGER REFERENCES Transportadora(id)
    );

    -- Adicionando chaves estrangeiras (constraints)

    -- Tabela Estoque: Chave estrangeira para Produto
    ALTER TABLE Estoque
    ADD CONSTRAINT fk_estoque_produto
    FOREIGN KEY (produto_id)
    REFERENCES Produto (id);

    -- Tabela Venda: Chave estrangeira para Cliente, Produto e Transportadora 
    ALTER TABLE Venda
    ADD CONSTRAINT fk_venda_cliente
    FOREIGN KEY (cliente_id)
    REFERENCES Cliente (id);

    ALTER TABLE Venda
    ADD CONSTRAINT fk_venda_produto
    FOREIGN KEY (produto_id)
    REFERENCES Produto (id);

    ALTER TABLE Venda
    ADD CONSTRAINT fk_venda_transportadora
    FOREIGN KEY (transportadora_id)
    REFERENCES Transportadora (id);


-- Function para registrar alterações no estoque na tabela de auditoria
CREATE OR REPLACE FUNCTION registrar_alteracao_estoque() RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO Auditoria (produto_id, produto_nome, data_hora, acao) 
        SELECT NEW.produto_id, p.nome, NOW(), TRUE
        FROM Produto p
        WHERE p.id = NEW.produto_id;
    ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO Auditoria (produto_id, produto_nome, data_hora, acao) 
        SELECT OLD.produto_id, p.nome, NOW(), TRUE
        FROM Produto p
        WHERE p.id = OLD.produto_id;
    END IF;
    RETURN NULL;
END; $$ LANGUAGE plpgsql;



   CREATE OR REPLACE FUNCTION adicionar_produto(nome_produto VARCHAR(255), quantidade_produto INTEGER, preco_produto FLOAT, fabricante_produto VARCHAR(255)) RETURNS VOID AS $$
    DECLARE
        id_prod INTEGER;
    BEGIN
        IF EXISTS (SELECT 1 FROM Produto WHERE nome = nome_produto) THEN
            SELECT id INTO id_prod FROM Produto WHERE nome = nome_produto;
            UPDATE Estoque SET quantidade = quantidade + quantidade_produto WHERE produto_id = id_prod;
        ELSE
            INSERT INTO Produto (id, nome, preco, fabricante) VALUES (DEFAULT, nome_produto, preco_produto, fabricante_produto);
            INSERT INTO Estoque (id, produto_id, quantidade) VALUES (DEFAULT, (SELECT id FROM Produto WHERE nome = nome_produto), quantidade_produto);
        END IF;
    END; $$ LANGUAGE plpgsql;


    CREATE OR REPLACE FUNCTION atualizar_produto(nome_produto VARCHAR(255), quantidade_produto INTEGER, preco_produto FLOAT, fabricante_produto VARCHAR(255)) RETURNS VOID AS $$
    DECLARE
        id_prod INTEGER;
    BEGIN
        IF EXISTS (SELECT 1 FROM Produto WHERE nome = nome_produto) THEN
            SELECT id INTO id_prod FROM Produto WHERE nome = nome_produto;
            UPDATE Produto 
            SET
                preco = preco_produto, 
                fabricante = fabricante_produto
            WHERE id = id_prod;
            UPDATE Estoque
            SET 
                quantidade = quantidade_produto
            WHERE produto_id = id_prod;
        ELSE
            RAISE EXCEPTION 'Produto não encontrado: %', nome_produto;
        END IF;
    END; 
    $$ LANGUAGE plpgsql;

    CREATE OR REPLACE FUNCTION remover_produto(produto_idd INTEGER) RETURNS VOID AS $$
    BEGIN
        IF EXISTS (SELECT 1 FROM Produto WHERE id = produto_id) THEN
            DELETE FROM Estoque WHERE produto_id = produto_idd;
            DELETE FROM Produto WHERE id = produto_id;
        ELSE
            RAISE EXCEPTION 'Produto com ID % não encontrado', produto_idd;
        END IF;
    END;
    $$ LANGUAGE plpgsql;

    CREATE VIEW estoque_completo AS
    SELECT e.id, p.nome, e.id AS produto_id, e.quantidade, p.preco, p.fabricante
    FROM Produto p, Estoque e
    WHERE e.id = p.id;

        
    CREATE VIEW venda_produto AS
    SELECT v.id, v.cliente_id, v.valorTotal, v.produto_id, p.nome AS produto_nome, v.data_hora, v.quantidade_produto, v.transportadora_id
    FROM Venda v
    JOIN Produto p ON v.produto_id = p.id;




    -- Função para pegar o preço final e inserir na tabela Venda
    CREATE OR REPLACE FUNCTION preco_final(quantidade_prod INTEGER, id_prod INTEGER) RETURNS FLOAT AS $$
    DECLARE
        preco_final FLOAT;
    BEGIN
        SELECT (quantidade_prod * (SELECT preco FROM Produto WHERE id = id_prod)) INTO preco_final;
        RETURN preco_final;
    END; $$ LANGUAGE plpgsql;


    -- Função para subtrair do estoque a quantidade de produtos que foi comprada
    CREATE OR REPLACE FUNCTION subtrai_quant_estoque() RETURNS TRIGGER AS $$
    DECLARE 
        quantidade_prod INTEGER;
        id_prod INTEGER;
    BEGIN
        SELECT produto_id INTO id_prod FROM Venda WHERE id = NEW.id;
        SELECT quantidade_produto INTO quantidade_prod FROM Venda WHERE id = NEW.id;
        UPDATE Estoque SET quantidade = quantidade - quantidade_prod WHERE produto_id = id_prod;
        RETURN NEW;
    END; $$ LANGUAGE plpgsql;


    --trigger para atualizar quantidade de estoque apos cada venda
    CREATE TRIGGER trigger_subtrai_estoque AFTER INSERT ON Venda FOR EACH ROW EXECUTE FUNCTION subtrai_quant_estoque();

    
    -- Trigger para guardar alterações no estoque
    CREATE TRIGGER trigger_alteracao_estoque AFTER INSERT OR DELETE ON Estoque FOR EACH ROW EXECUTE FUNCTION registrar_alteracao_estoque();


