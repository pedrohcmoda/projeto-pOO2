//Nome: Pedro Henrique Cunha Moda RA: 2476576

public class Manuscrito extends ItemDeBiblioteca{
    private String material;
    private String origem;
    public Manuscrito(String titulo, String codigo, String dataPubli, String material, String origem, String prateleira, String secao, boolean baixavel){
        super(titulo, codigo, dataPubli, prateleira, secao, baixavel);
        this.material=material;
        this.origem=origem;
    };
    public void setMaterial(String material) {
        this.material = material;
    }
    public void setOrigem(String origem) {
        this.origem = origem;
    }
    public String getMaterial() {
        return material;
    }
    public String getOrigem() {
        return origem;
    }

    //Sobrescrita
    @Override
    public void mostrarDetalhes() {
        System.out.println("\n !! Manuscrito !! \n");
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Codigo: " + getCodigo());
        System.out.println("Material: " + material);
        System.out.println("Origem: " + origem);
        System.out.println("Data de publicacao: " + getDataPubli());
        System.out.println("Secao " + getSecao() +"/ Prateleira " + getPrateleira());
        if(getBaixavel()){
            System.out.println("Disponivel para Download!");
        }else{
            System.out.println("Indisponivel para download");
        }
    }
}
