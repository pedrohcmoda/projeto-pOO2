package com.velas.velashome.model.dao;

import java.util.List;

import com.velas.velashome.model.entities.Produto;

public interface ProdutoDao {
    
    void insert(Produto obj);
    void update(Produto obj);
    void deleteById(Produto obj);
    List<Produto> findAll();
}
