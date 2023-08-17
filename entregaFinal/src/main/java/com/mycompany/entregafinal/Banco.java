//Nome: Pedro Henrique Cunha Moda RA: 2476576

import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.lang.annotation.*;

public class Banco{
    
    //Encapsulamento

    private static Banco bd;
    private Banco(){}
    
    public static Banco getBanco(){
        if(bd==null){
            bd=new Banco();
        }
        return bd;
    }
    private int escolha=0;
    final int zero =0;
    String codigo;
    private String tituloPesquisa;
    private boolean encontrado;
    ArrayList<ItemDeBiblioteca> itens = new ArrayList<ItemDeBiblioteca>();

    public void opcoes(int opc) {
        Method[] methods = getClass().getDeclaredMethods();
        boolean achou = false;

        for (Method method : methods) {
            if (method.isAnnotationPresent(Option.class)) {
                Option option = method.getAnnotation(Option.class);
                if (option.value() == opc) {
                    try {
                        method.invoke(this);
                        achou = true;
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        if (!achou) {
            System.out.println("Opcao invalida.");
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Option {
        int value();
    }

    @Option(1)
    public void addLivro(){
        Livro livro = new Livro(null, null, null, null, zero, null, null, null, true);
        livro.setTitulo(l.entDados("Digite o titulo: "));
        try{
            livro.setCodigo(l.entDados("Digite o codigo: "), itens);
        }
        catch(codIgualException eci){
            livro=(Livro)eci.codigoIgual(itens, livro);
        }
        livro.setAutor(l.entDados("Digite o autor: "));
        livro.setEditora(l.entDados("Digite a editora: "));
        try{
            livro.setNumPaginas(Integer.parseInt(l.entDados("Digite o numero de paginas: ")));
        }catch(NumberFormatException nf){
            livro.setNumPaginas(Integer.parseInt(l.entDados("Valor invalido, digite novamente o numero de paginas: ")));
        }
        livro.setDataPubli(l.entDados("Digite a data de publicacao (DD/MM/AAAA): "));
        livro.setSecao(l.entDados("Digite a secao que contem o item: "));
        livro.setPrateleira(l.entDados("Digite a prateleira que contem o item: "));
        itens.add(livro);
        try{
            escolha= Integer.parseInt(l.entDados("Digite 1 para cadastrar mais um item ou 0 para terminar o cadastro: "));
        }catch(NumberFormatException nfe){
            escolha= Integer.parseInt(l.entDados("Valor invalido, digite 1 para cadastrar mais um item ou 0 para terminar o cadastro: "));
        }
        if(escolha==1){
            //Reflexividade
            addLivro();
        }
    };
    @Option(2)
    public void addDVD(){
        DVD dvd = new DVD(null, null, null, null, zero, null, null, null, true);
        dvd.setTitulo(l.entDados("Digite o titulo: "));
        try{
            dvd.setCodigo(l.entDados("Digite o codigo: "), itens);
        }
        catch(codIgualException eci){
            dvd=(DVD)(eci.codigoIgual(itens,dvd));
        };
        dvd.setDiretor(l.entDados("Digite o diretor: "));
        try{
            dvd.setDuracao(Integer.parseInt(l.entDados("Digite a duracao (minutos): ")));
        }catch(NumberFormatException nf){
            dvd.setDuracao(Integer.parseInt(l.entDados("Valor invalido, digite novamente a duracao (minutos): ")));
        }
        dvd.setElenco(l.entDados("Digite o elenco: "));
        dvd.setDataPubli(l.entDados("Digite a data de publicacao (DD/MM/AAAA): "));
        dvd.setSecao(l.entDados("Digite a secao que contem o item: "));
        dvd.setPrateleira(l.entDados("Digite a prateleira que contem o item: "));
        itens.add(dvd);
        try{
            escolha= Integer.parseInt(l.entDados("Digite 1 para cadastrar mais um item ou 0 para terminar o cadastro: "));
        }catch(NumberFormatException nfe){
            escolha= Integer.parseInt(l.entDados("Valor invalido, digite 1 para cadastrar mais um item ou 0 para terminar o cadastro: "));
        }
        if(escolha==1){
            //Reflexividade
            addDVD();
        }
    };
    @Option(3)
    public void addCD(){
        CD cd = new CD(null, null, null, zero, null, null, null, true);
        cd.setTitulo(l.entDados("Digite o titulo: "));
        try{
            cd.setCodigo(l.entDados("Digite o codigo: "), itens);
        }
        catch(codIgualException eci){
            cd=(CD)(eci.codigoIgual(itens,cd));
        };
        cd.setArtista(l.entDados("Digite o/ a(s) artista(s): "));
        try{
            cd.setNumFaixas(Integer.parseInt(l.entDados("Digite o numero de faixas: ")));
        }catch(NumberFormatException nf){
            cd.setNumFaixas(Integer.parseInt(l.entDados("Valor invalido, digite novamente o numero de faixas: ")));
        }
        cd.setDataPubli(l.entDados("Digite a data de publicacao (DD/MM/AAAA): "));
        cd.setSecao(l.entDados("Digite a secao que contem o item: "));
        cd.setPrateleira(l.entDados("Digite a prateleira que contem o item: "));
        itens.add(cd);
        try{
            escolha= Integer.parseInt(l.entDados("Digite 1 para cadastrar mais um item ou 0 para terminar o cadastro: "));
        }catch(NumberFormatException nfe){
            escolha= Integer.parseInt(l.entDados("Valor invalido, digite 1 para cadastrar mais um item ou 0 para terminar o cadastro: "));
        }
        if(escolha==1){
            //Reflexividade
            addCD();
        }
    };
    @Option(4)
    public void addManuscrito(){
        Manuscrito mnc = new Manuscrito(null, null, null, null, null, null, null,false );
        mnc.setTitulo(l.entDados("Digite o titulo: "));
        try{
            mnc.setCodigo(l.entDados("Digite o codigo: "), itens);
        }
        catch(codIgualException eci){
            mnc=(Manuscrito)(eci.codigoIgual(itens,mnc));
        };
        mnc.setMaterial(l.entDados("Digite o material: "));
        mnc.setOrigem(l.entDados("Digite a origem: "));
        mnc.setDataPubli(l.entDados("Digite a data de publicacao (DD/MM/AAAA): "));
        mnc.setSecao(l.entDados("Digite a secao que contem o item: "));
        mnc.setPrateleira(l.entDados("Digite a prateleira que contem o item: "));
        itens.add(mnc);
        try{
            escolha= Integer.parseInt(l.entDados("Digite 1 para cadastrar mais um item ou 0 para terminar o cadastro: "));
        }catch(NumberFormatException nfe){
            escolha= Integer.parseInt(l.entDados("Valor invalido, digite 1 para cadastrar mais um item ou 0 para terminar o cadastro: "));
        }
        if(escolha==1){
            //Reflexividade
            addManuscrito();
        }
    };
    @Option(5)
    public void addCartografia(){
        Cartografia cart = new Cartografia(null, null, null, null, null, null, null, null, true);
        cart.setTitulo(l.entDados("Digite o titulo: "));
        try{
            cart.setCodigo(l.entDados("Digite o codigo: "), itens);
        }
        catch(codIgualException eci){
            cart=(Cartografia)(eci.codigoIgual(itens,cart));
        };
        cart.setEscala(l.entDados("Digite a escala: "));
        cart.setProjecao(l.entDados("Digite a projecao: "));;
        cart.setFonte(l.entDados("Digite a fonte: "));
        cart.setDataPubli(l.entDados("Digite a data de publicacao (DD/MM/AAAA): "));
        cart.setSecao(l.entDados("Digite a secao que contem o item: "));
        cart.setPrateleira(l.entDados("Digite a prateleira que contem o item: "));
        itens.add(cart);
        try{
            escolha= Integer.parseInt(l.entDados("Digite 1 para cadastrar mais um item ou 0 para terminar o cadastro: "));
        }catch(NumberFormatException nfe){
            escolha= Integer.parseInt(l.entDados("Valor invalido, digite 1 para cadastrar mais um item ou 0 para terminar o cadastro: "));
        }
        if(escolha==1){
            //Reflexividade
            addCartografia();
        }
    };
    public void pesquisa(String tituloPesquisa){
        encontrado = false;
        for (ItemDeBiblioteca item : itens) {
            if (item.getTitulo().equalsIgnoreCase(tituloPesquisa)) {
                item.mostrarDetalhes();
                System.out.println("------");
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum item encontrado com o título informado.");
        }
    }
    //Sobrecarga
    public void pesquisa(boolean vf){
        encontrado = false;
        for (ItemDeBiblioteca item : itens) {
            if (item.getBaixavel()==true) {
                item.mostrarDetalhes();
                System.out.println("------");
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum item encontrado com o título informado.");
        }
    }
    @Option(6)
    public void ferramentaPesquisa() {
        int esc = Integer.parseInt(l.entDados("Digite qual tipo de pesquisa voce deseja: (1 para titulo por pesquisa, 2 para pesquisa dos itens baixaveis)"));

        switch (esc){
            case 1:
                tituloPesquisa = l.entDados("Digite o título a ser pesquisado: ");
                pesquisa(tituloPesquisa);
                break;
            case 2:
                pesquisa(true);
                break;
            case 0:
                break;

        }
    }

}