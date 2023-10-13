package com.velas.velashome.model.dao;

import java.util.List;
import com.velas.velashome.model.entities.Transportadora;

public interface TransportadoraDao {

    void insert(Transportadora obj);
    void update(Transportadora obj);
    void deleteById(Transportadora obj);
    List<Transportadora> findAll();
}
