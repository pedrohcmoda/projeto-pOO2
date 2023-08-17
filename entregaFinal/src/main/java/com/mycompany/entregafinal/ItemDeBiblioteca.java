// Nome: Pedro Henrique Cunha Moda RA: 2476576

import java.util.ArrayList;

public abstract class ItemDeBiblioteca{
    private String titulo;
    private String codigo;
    private String dataPubli;
    private String secao;
    private String prateleira;
    private boolean baixavel;

    public ItemDeBiblioteca(String titulo, String codigo, String dataPubli, String prateleira, String secao, boolean baixavel) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.dataPubli = dataPubli;
        this.prateleira=prateleira;
        this.secao=secao;
        this.baixavel=baixavel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo, ArrayList<ItemDeBiblioteca> itens) throws codIgualException{
        boolean codConf=true;
        for (ItemDeBiblioteca item : itens) {
            if (item.getCodigo().equals(codigo)) {
                codConf= false;
                throw new codIgualException();
            }
        }
        if(codConf){
            this.codigo = codigo;
        };
    }
    public String getDataPubli() {
        return dataPubli;
    }
    public void setDataPubli(String dataPubli) {
        this.dataPubli = dataPubli;
    }
    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }
    public String getPrateleira() {
        return prateleira;
    }
    public void setSecao(String secao) {
        this.secao = secao;
    }
    public String getSecao() {
        return secao;
    }
    public boolean getBaixavel(){
        return baixavel;
    }
    public abstract void mostrarDetalhes();
}
