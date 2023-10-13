package com.velas.velashome.model.dao;

import java.util.List;

import com.velas.velashome.model.entities.Produto_Estoque;

public interface Produto_EstoqueDao {

    void insert(Produto_Estoque obj);
    void update(Produto_Estoque obj);
    void delete(Produto_Estoque obj);
    List<Produto_Estoque>findAll();
    
}
