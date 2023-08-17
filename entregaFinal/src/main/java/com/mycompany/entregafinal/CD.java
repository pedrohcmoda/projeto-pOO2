// Nome: Pedro Henrique Cunha Moda RA: 2476576

public class CD extends ItemDeBiblioteca{
    private String artista;
    private int numFaixas;

    public CD(String titulo, String codigo, String artista, int numFaixas, String dataPubli, String prateleira, String secao, boolean baixavel) {
        super(titulo, codigo, dataPubli, prateleira, secao, baixavel);
        this.artista = artista;
        this.numFaixas = numFaixas;
    }

    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public int getNumFaixas() {
        return numFaixas;
    }

    public void setNumFaixas(int numFaixas) {
        this.numFaixas = numFaixas;
    }


    //Sobrescrita
    @Override
    public void mostrarDetalhes() {
        System.out.println("\n !! CD !! \n");
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Codigo: " + getCodigo());
        System.out.println("Artista: " + artista);
        System.out.println("Numero de Faixas: " + numFaixas);
        System.out.println("Data de publicacao: " + getDataPubli());
        System.out.println("Secao " + getSecao() +"/ Prateleira " + getPrateleira());
        if(getBaixavel()){
            System.out.println("Disponivel para Download!");
        }else{
            System.out.println("Indisponivel para download");
        }
    }
}
