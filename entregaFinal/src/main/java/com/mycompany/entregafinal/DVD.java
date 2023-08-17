// Nome: Pedro Henrique Cunha Moda RA: 2476576

public class DVD extends ItemDeBiblioteca{
    private String diretor;
    private String elenco;
    private int duracao;
    public DVD(String titulo, String codigo, String diretor, String elenco, int duracao, String dataPubli, String prateleira, String secao, boolean baixavel) {
        super(titulo, codigo, dataPubli, prateleira, secao, baixavel);
        this.diretor = diretor;
        this.elenco = elenco;
        this.duracao = duracao;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
        this.elenco = elenco;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    //Sobrescrita
    @Override
    public void mostrarDetalhes() {
        System.out.println("\n !! DVD !! \n");
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Codigo: " + getCodigo());
        System.out.println("Diretor: " + diretor);
        System.out.println("Elenco: " + elenco);
        System.out.println("Duracao: " + duracao);
        System.out.println("Data de publicacao: " + getDataPubli());
        System.out.println("Secao " + getSecao() +"/ Prateleira " + getPrateleira());
        if(getBaixavel()){
            System.out.println("Disponivel para Download!");
        }else{
            System.out.println("Indisponivel para download");
        }
    }
}
