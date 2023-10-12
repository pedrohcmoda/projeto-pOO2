package com.velas.velashome.model.entities;

public class Auditoria {
    private int id;
    private int produtoId;
    private boolean acao;

    public Auditoria(int id, int produtoId, boolean acao) {
        this.id = id;
        this.produtoId = produtoId;
        this.acao = acao;
    }

    public Auditoria() {
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

    public boolean isAcao() {
        return acao;
    }

    public void setAcao(boolean acao) {
        this.acao = acao;
    }

    @Override
    public String toString() {
        return "Auditoria{" +
                "id=" + id +
                ", produtoId=" + produtoId +
                ", acao=" + acao +
                '}';
    }
}
