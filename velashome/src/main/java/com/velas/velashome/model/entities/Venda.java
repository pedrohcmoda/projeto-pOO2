package com.velas.velashome.model.entities;

import java.util.Date;

public class Venda {
    private int id;
    private int clienteId;
    private float valorTotal;
    private int produtoId;
    private Date data;
    private int quantidadeProduto;
    private int transportadoraId;

    public Venda(int id, int clienteId, float valorTotal, int produtoId, Date data, int quantidadeProduto, int transportadoraId) {
        this.id = id;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.produtoId = produtoId;
        this.data = data;
        this.quantidadeProduto = quantidadeProduto;
        this.transportadoraId = transportadoraId;
    }

    public Venda() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public int getTransportadoraId() {
        return transportadoraId;
    }

    public void setTransportadoraId(int transportadoraId) {
        this.transportadoraId = transportadoraId;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", valorTotal=" + valorTotal +
                ", produtoId=" + produtoId +
                ", data=" + data +
                ", quantidadeProduto=" + quantidadeProduto +
                ", transportadoraId=" + transportadoraId +
                '}';
    }
}
