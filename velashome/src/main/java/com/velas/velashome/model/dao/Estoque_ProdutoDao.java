package com.velas.velashome.model.dao;

import java.util.List;

import com.velas.velashome.model.entities.Estoque_Produto;

public interface Estoque_ProdutoDao {

    void insert(Estoque_Produto obj, int id);
    void update(Estoque_Produto obj);
    void delete(Estoque_Produto obj);
    List<Estoque_Produto>findAll();
}
