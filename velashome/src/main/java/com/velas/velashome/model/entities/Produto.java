package com.velas.velashome.model.entities;

public class Produto {
    private int id;
    private String nome;
    private float preco;
    private String fabricante;

    public Produto(int id, String nome, float preco, String fabricante) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.fabricante = fabricante;
    }
    public Produto() {
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
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
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
        return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", fabricante=" + fabricante + "]";
    }
    
    
}
