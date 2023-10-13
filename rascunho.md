# BD

## Objetos necessarios para usar todos os metodos do bd





### Produto_Estoque

    vai ter que ter findAll, select * from estoque_completo
                    insert, select adicionar_produto(?, ?, ?, ?), nome, qunatidade, preco e fabricante 
                    update, select atualizar_produto(?, ?, ?, ?)
                    delete, select remover_produto(?)

    estoque completo é necessario:
        id INT
        nome String
        produto_id INT
        quantidade INT
        preco INT
        fabricante String


### Auditoria

    List<Auditoria>findAll() select * from Auditoria;

        id SERIAL PRIMARY KEY,
        produto_id INTEGER,
        produto_nome VARCHAR(255),
        data_hora TIMESTAMP DEFAULT NOW(),
        acao BOOLEAN



### Vendas_Produto

   List<Vendas>findAll() ( teria que ser modificado a classe java pra receber nome tbm)
   select * from venda_com_nome

        int id;
        int cliente_id
        float valorTotal;
        int produto_id;
        String produto_nome;


   insert nao é necessario (mas eu tenho uns planos de fazer um insert de venda interessante)
   
   delete (acho que nem pode)


### Transportadora

    List<Transportadora>findAll(), (select * from Transportadora)
    insert(Transportadora), (insert into Transportadora VALUES  (?, ?, ?, ?, ?))
    deleteById(Transportadora), (delete from Transportadora WHERE id = ?)
---
        id SERIAL PRIMARY KEY,
        nome VARCHAR(255),
        telefone VARCHAR(255),
        cnp VARCHAR(255),
        endereco VARCHAR(255)
---

### Clientes
    
    List<Cliente>findAll

(select * from Clientes)
        id SERIAL PRIMARY KEY,
        nome VARCHAR(255),
        telefone VARCHAR(255),
        email VARCHAR(255)





### Gerente
oq faltaria de classe é gerente, que talvez tenha que existir uma consulta pra fazer o login do gerente, e assim verificar as infos 

e produto estoque estao em 1 mesma classe pq mesclei as infos delas

