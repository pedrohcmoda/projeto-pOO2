package com.velas.velashome.model.entities;

public class Produto_Estoque {
    int id;
    String nome;
    int produto_id;
    int quantidade;
    int preco;
    String fabricante;
    public Produto_Estoque() {
    }
    public Produto_Estoque(int id, String nome, int produto_id, int quantidade, int preco, String fabricante) {
        this.id = id;
        this.nome = nome;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.fabricante = fabricante;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getProduto_id() {
        return produto_id;
    }
    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getPreco() {
        return preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }
    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    @Override
    public String toString() {
        return "Produto_Estoque [id=" + id + ", nome=" + nome + ", produto_id=" + produto_id + ", quantidade="
                + quantidade + ", preco=" + preco + ", fabricante=" + fabricante + "]";
    }
}
