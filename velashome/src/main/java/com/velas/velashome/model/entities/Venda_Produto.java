package com.velas.velashome.model.entities;
import java.sql.Date;


public class Venda_Produto {
    int id;
    int cliente_id;
    float valorTotal;
    int produto_id;
    String produto_nome;
    Date data_hora;
    int quantidade_produto;
    int transportadora_id;
    public Venda_Produto() {
    }
    public Venda_Produto(int id, int cliente_id, float valorTotal, int produto_id, String produto_nome, Date data_hora,
            int quantidade_produto, int transportadora_id) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.valorTotal = valorTotal;
        this.produto_id = produto_id;
        this.produto_nome = produto_nome;
        this.data_hora = data_hora;
        this.quantidade_produto = quantidade_produto;
        this.transportadora_id = transportadora_id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCliente_id() {
        return cliente_id;
    }
    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }
    public float getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
    public int getProduto_id() {
        return produto_id;
    }
    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }
    public String getProduto_nome() {
        return produto_nome;
    }
    public void setProduto_nome(String produto_nome) {
        this.produto_nome = produto_nome;
    }
    public Date getData_hora() {
        return data_hora;
    }
    public void setData_hora(Date date) {
        this.data_hora = date;
    }
    public int getQuantidade_produto() {
        return quantidade_produto;
    }
    public void setQuantidade_produto(int quantidade_produto) {
        this.quantidade_produto = quantidade_produto;
    }
    public int getTransportadora_id() {
        return transportadora_id;
    }
    public void setTransportadora_id(int transportadora_id) {
        this.transportadora_id = transportadora_id;
    }
    @Override
    public String toString() {
        return "Venda_Produto [id=" + id + ", cliente_id=" + cliente_id + ", valorTotal=" + valorTotal + ", produto_id="
                + produto_id + ", produto_nome=" + produto_nome + ", data_hora=" + data_hora + ", quantidade_produto="
                + quantidade_produto + ", transportadora_id=" + transportadora_id + "]";
    }
    
     
    
}
