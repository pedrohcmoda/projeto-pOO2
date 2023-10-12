package com.velas.velashome.model.dao.implementation;

import java.sql.Connection;

import com.velas.velashome.model.dao.EstoqueDao;

public class EstoqueDaoJDBC implements EstoqueDao {
    private Connection conn;
    public EstoqueDaoJDBC(Connection conn) {
        this.conn = conn;
    }
}
