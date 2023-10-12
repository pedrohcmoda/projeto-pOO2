package com.velas.velashome.model.entities;

public class Transportadora {
    private int id;
    private String nome;
    private String telefone;
    private String cnpj;
    private String endereco;

    public Transportadora(int id, String nome, String telefone, String cnpj, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public Transportadora() {
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Transportadora{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
