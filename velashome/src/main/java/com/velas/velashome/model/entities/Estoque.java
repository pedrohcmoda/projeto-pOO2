package com.velas.velashome.model.entities;

public class Estoque {
    private int id;
    private int produtoId;
    private int quantidade;

    public Estoque(int id, int produtoId, int quantidade) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Estoque [id=" + id + ", produtoId=" + produtoId + ", quantidade=" + quantidade + "]";
    }
}
