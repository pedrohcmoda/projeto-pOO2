package com.velas.velashome.model.dao.implementation;

import java.sql.Connection;

import com.velas.velashome.model.dao.ProdutoDao;

public class ProdutoDaoJDBC implements ProdutoDao {
    
    private Connection conn;
    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }
}
