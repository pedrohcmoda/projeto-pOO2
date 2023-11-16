package com.velas.velashome.model.dao;

import java.util.List;

import com.velas.velashome.model.entities.Estoque;

public interface EstoqueDao {
    void insert(Estoque obj);
    void update(Estoque obj);
    void delete(Estoque obj);
    List<Estoque>findAll();
}
