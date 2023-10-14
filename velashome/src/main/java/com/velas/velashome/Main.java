/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.velas.velashome;

import com.velas.velashome.model.dao.DaoFactory;
import com.velas.velashome.model.dao.Produto_EstoqueDao;

/**
 *
 * @author pmoda
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Hello World!");
        Produto_EstoqueDao prod = DaoFactory.createProduto_EstoqueDao();
        prod.findAll();
    }
}
