// Nome: Pedro Henrique Cunha Moda RA: 2476576

public class Cartografia extends ItemDeBiblioteca{
    private String escala;
    private String projecao;
    private String fonte;

    public Cartografia (String titulo, String codigo, String escala, String projecao, String fonte, String dataPubli, String prateleira, String secao, boolean baixavel){
        super(titulo, codigo, dataPubli, prateleira, secao, baixavel);
        this.escala=escala;
        this.projecao=projecao;
        this.fonte=fonte; 
    }
    public void setEscala(String escala) {
        this.escala = escala;
    }
    public String getEscala() {
        return escala;
    }
    public void setProjecao(String projecao) {
        this.projecao = projecao;
    }
    public String getProjecao() {
        return projecao;
    }
    public void setFonte(String fonte) {
        this.fonte = fonte;
    }
    public String getFonte() {
        return fonte;
    }

    //Sobrescrita
    @Override
    public void mostrarDetalhes() {
        System.out.println("\n !! Cartografia !! \n");
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Codigo: " + getCodigo());
        System.out.println("Escala: " + escala);
        System.out.println("Projecao: " + projecao);
        System.out.println("Fonte: " + fonte);
        System.out.println("Data de publicacao: " + getDataPubli());
        System.out.println("Secao " + getSecao() +"/ Prateleira " + getPrateleira());
        if(getBaixavel()){
            System.out.println("Disponivel para Download!");
        }else{
            System.out.println("Indisponivel para download");
        }
        }
}
