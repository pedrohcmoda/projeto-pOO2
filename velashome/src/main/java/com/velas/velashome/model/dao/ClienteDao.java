package com.velas.velashome.model.dao;

import java.util.List;

import com.velas.velashome.model.entities.Cliente;

public interface ClienteDao {
    List<Cliente> findAll();
}
