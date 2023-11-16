package com.velas.velashome.model.dao;

import java.util.List;

import com.velas.velashome.model.entities.Funcionario;

public interface FuncionarioDao {

    List<Funcionario>findAll();
    
}
