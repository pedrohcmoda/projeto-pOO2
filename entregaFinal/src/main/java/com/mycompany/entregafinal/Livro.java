//Nome: Pedro Henrique Cunha Moda RA: 2476576

//Heranca

public class Livro extends ItemDeBiblioteca{
    private String autor;
    private String editora;
    private int numPaginas;

    public Livro(String titulo, String codigo, String autor, String editora, int numPaginas, String dataPubli, String prateleira, String secao, boolean baixavel) {
        super(titulo, codigo, dataPubli, prateleira, secao, baixavel);
        this.autor = autor;
        this.editora = editora;
        this.numPaginas = numPaginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }


    //Sobrescrita
    @Override
    public void mostrarDetalhes() {
        System.out.println("\n !! Livro !! \n");
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Codigo: " + getCodigo());
        System.out.println("Autor: " + autor);
        System.out.println("Editora: " + editora);
        System.out.println("Numero de Paginas: " + numPaginas);
        System.out.println("Data de publicacao: " + getDataPubli());
        System.out.println("Secao " + getSecao() +"/ Prateleira " + getPrateleira());
        if(getBaixavel()){
            System.out.println("Disponivel para Download!");
        }else{
            System.out.println("Indisponivel para download");
        }
    }

}