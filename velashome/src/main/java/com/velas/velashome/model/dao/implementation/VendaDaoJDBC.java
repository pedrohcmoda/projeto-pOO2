package com.velas.velashome.model.dao.implementation;

import java.sql.Connection;

import com.velas.velashome.model.dao.VendaDao;

public class VendaDaoJDBC implements VendaDao {
    
    private Connection conn;
    public VendaDaoJDBC(Connection conn) {
        this.conn = conn;
    }
}
