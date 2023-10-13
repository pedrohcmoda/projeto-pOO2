package com.velas.velashome.model.dao;

import java.util.List;
import com.velas.velashome.model.entities.Transportadora;

public interface TransportadoraDao {

    void insert(Transportadora transportadora);

    void update(Transportadora transportadora);

    void deleteById(Integer id);

    Transportadora findById(Integer id);

    List<Transportadora> findAll();
}
