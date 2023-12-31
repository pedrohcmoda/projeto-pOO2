package model.entities;

public class Fornecedora {
    private int forId;
    private String forCnpj;
    private String forRazaoSocial;
    private String forEmail;
    private String forTelefone;
    private String forLogradouro;
    private int forNumero;
    private int forCep;
    private String forCidade;
    private String forEstado;
    private int traId;

    public Fornecedora() {
    }

    public Fornecedora(int forId, String forCnpj, String forRazaoSocial, String forEmail, String forTelefone, String forLogradouro, int forNumero, int forCep, String forCidade, String forEstado, int traId) {
        this.forId = forId;
        this.forCnpj = forCnpj;
        this.forRazaoSocial = forRazaoSocial;
        this.forEmail = forEmail;
        this.forTelefone = forTelefone;
        this.forLogradouro = forLogradouro;
        this.forNumero = forNumero;
        this.forCep = forCep;
        this.forCidade = forCidade;
        this.forEstado = forEstado;
        this.traId = traId;
    }

    public int getForId() {
        return forId;
    }

    public void setForId(int forId) {
        this.forId = forId;
    }

    public String getForCnpj() {
        return forCnpj;
    }

    public void setForCnpj(String forCnpj) {
        this.forCnpj = forCnpj;
    }

    public String getForRazaoSocial() {
        return forRazaoSocial;
    }

    public void setForRazaoSocial(String forRazaoSocial) {
        this.forRazaoSocial = forRazaoSocial;
    }

    public String getForEmail() {
        return forEmail;
    }

    public void setForEmail(String forEmail) {
        this.forEmail = forEmail;
    }

    public String getForTelefone() {
        return forTelefone;
    }

    public void setForTelefone(String forTelefone) {
        this.forTelefone = forTelefone;
    }

    public String getForLogradouro() {
        return forLogradouro;
    }

    public void setForLogradouro(String forLogradouro) {
        this.forLogradouro = forLogradouro;
    }

    public int getForNumero() {
        return forNumero;
    }

    public void setForNumero(int forNumero) {
        this.forNumero = forNumero;
    }

    public int getForCep() {
        return forCep;
    }

    public void setForCep(int forCep) {
        this.forCep = forCep;
    }

    public String getForCidade() {
        return forCidade;
    }

    public void setForCidade(String forCidade) {
        this.forCidade = forCidade;
    }

    public String getForEstado() {
        return forEstado;
    }

    public void setForEstado(String forEstado) {
        this.forEstado = forEstado;
    }

    public int getTraId() {
        return traId;
    }

    public void setTraId(int traId) {
        this.traId = traId;
    }

    @Override
    public String toString() {
        return "Fornecedora [forId=" + forId + ", forCnpj=" + forCnpj + ", forRazaoSocial=" + forRazaoSocial
                + ", forEmail=" + forEmail + ", forTelefone=" + forTelefone + ", forLogradouro=" + forLogradouro
                + ", forNumero=" + forNumero + ", forCep=" + forCep + ", forCidade=" + forCidade + ", forEstado="
                + forEstado + ", traId=" + traId + "]";
    }    
    
    
}
