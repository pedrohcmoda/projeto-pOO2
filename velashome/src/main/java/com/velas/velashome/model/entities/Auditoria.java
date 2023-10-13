package com.velas.velashome.model.entities;

import java.sql.Date;

public class Auditoria {
    private int id;
    private int produtoId;
    private String produto_nome;
    private Date data_hora;
    private boolean acao;
    public Auditoria() {
    }
    public Auditoria(int id, int produtoId, String produto_nome, Date data_hora, boolean acao) {
        this.id = id;
        this.produtoId = produtoId;
        this.produto_nome = produto_nome;
        this.data_hora = data_hora;
        this.acao = acao;
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
    public String getProduto_nome() {
        return produto_nome;
    }
    public void setProduto_nome(String produto_nome) {
        this.produto_nome = produto_nome;
    }
    public Date getData_hora() {
        return data_hora;
    }
    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }
    public boolean isAcao() {
        return acao;
    }
    public void setAcao(boolean acao) {
        this.acao = acao;
    }
    @Override
    public String toString() {
        return "Auditoria [id=" + id + ", produtoId=" + produtoId + ", produto_nome=" + produto_nome + ", data_hora="
                + data_hora + ", acao=" + acao + "]";
    }
    
    
}
