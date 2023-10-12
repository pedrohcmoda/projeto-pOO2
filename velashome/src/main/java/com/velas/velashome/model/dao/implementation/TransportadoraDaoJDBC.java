package com.velas.velashome.model.dao.implementation;

import java.sql.Connection;

import com.velas.velashome.model.dao.TransportadoraDao;

public class TransportadoraDaoJDBC implements TransportadoraDao {
    
    private Connection conn;
    public TransportadoraDaoJDBC(Connection conn) {
        this.conn = conn;
    }
}
