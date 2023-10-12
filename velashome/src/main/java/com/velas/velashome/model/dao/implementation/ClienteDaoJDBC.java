package com.velas.velashome.model.dao.implementation;

import java.sql.Connection;

import com.velas.velashome.model.dao.ClienteDao;

public class ClienteDaoJDBC implements ClienteDao {
    
    private Connection conn;
    public ClienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }
}
