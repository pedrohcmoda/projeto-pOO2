/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.velas.velashome;

import java.util.List;

import com.velas.velashome.model.dao.AuditoriaDao;
import com.velas.velashome.model.dao.ClienteDao;
import com.velas.velashome.model.dao.DaoFactory;
import com.velas.velashome.model.dao.GerenteDao;
import com.velas.velashome.model.dao.Produto_EstoqueDao;
import com.velas.velashome.model.dao.TransportadoraDao;
import com.velas.velashome.model.dao.Venda_ProdutoDao;
import com.velas.velashome.model.entities.Auditoria;
import com.velas.velashome.model.entities.Cliente;
import com.velas.velashome.model.entities.Gerente;
import com.velas.velashome.model.entities.Produto_Estoque;
import com.velas.velashome.model.entities.Transportadora;
import com.velas.velashome.model.entities.Venda_Produto;

/**
 *
 * @author pmoda
 */
public class pcp {

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        System.out.println("Hello World!");
        Gerente gerenteP = new Gerente(0, null, "pedro@pedro.com", "pedro");
        Produto_EstoqueDao prod = DaoFactory.createProduto_EstoqueDao();
        AuditoriaDao auditoria = DaoFactory.createAuditoriaDao();
        ClienteDao cliente = DaoFactory.createClienteDao();
        GerenteDao gerente = DaoFactory.createGerenteDao();
        TransportadoraDao transportadora = DaoFactory.createTransportadoraDao();
        Venda_ProdutoDao venda = DaoFactory.createVenda_ProdutoDao();

        //Testando senha do gerente
        gerente.autenticar(gerenteP);

        Thread.sleep(1);

        List<Produto_Estoque> listaProduto= prod.findAll();
        List<Auditoria> listAuditoria = auditoria.findAll(); 
        List<Cliente> listCliente = cliente.findAll();
        List<Transportadora> listTransportadora = transportadora.findAll();
        List<Venda_Produto> listVenda_Produtos = venda.findAll();

        System.out.println("Adicionando Produto_Estoque: ProdutoA");
        Produto_Estoque produtoAdicionado = new Produto_Estoque();
        produtoAdicionado.setNome("ProdutoA");
        produtoAdicionado.setPreco(2000);
        produtoAdicionado.setFabricante("EmpresaB");
        produtoAdicionado.setQuantidade(2);
        produtoAdicionado.setProduto_id(8);
        prod.insert(produtoAdicionado);
        System.out.println("Removendo Produto_Estoque: ProdutoA");
        prod.delete(produtoAdicionado);

        // Teste feito junto 
        System.out.println("\n----Lista de Produtosm Adicionados pelo Java----------\n");
        for (Produto_Estoque obj : listaProduto) {
            System.out.println(obj.toString());
        }
        System.out.println("\n----Lista de Auditoria, Adicionados automaticamente----------\n");
        for (Auditoria obj : listAuditoria) { 
            System.out.println(obj.toString());
        }


        // Adicionado manualmente
        System.out.println("\n----Lista de Cliente, Adicionados pelo banco----------\n");
        for (Cliente obj : listCliente) {
            System.out.println(obj.toString());
        }


        //Adicionado pelo Java
        Transportadora transportadoraAdicionado = new Transportadora(0, "TransportadoraXYZ", "43999999999", "0222022202/00001.00", "LugarTalk");
        transportadora.insert(transportadoraAdicionado);

        System.out.println("\n----Lista de Transportadoras, Adicionados pelo Java----------\n");
        for (Transportadora obj : listTransportadora) {
            System.out.println(obj.toString());
        }

        //Adicionado manualmente
        System.out.println("\n----Lista de Vendas, Adicionados pelo Java----------\n");
        for (Venda_Produto obj : listVenda_Produtos) {
            System.out.println(obj.toString());
        }
    }
}
