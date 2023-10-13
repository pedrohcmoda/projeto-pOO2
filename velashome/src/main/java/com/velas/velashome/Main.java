/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.velas.velashome;

/**
 *
 * @author pmoda
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Transportadora transportadora = new Transportadora();
               transportadora.setNome("TransTeste");
               transportadora.setCnpj("1231231231");
               transportadora.setEndereco("gasdasdsadsad");
               transportadora.setTelefone("80028922");
        TransportadoraDaoJDBC controladora = new TransportadoraDaoJDBC();
        controladora.insert(transportadora);

    }
}
