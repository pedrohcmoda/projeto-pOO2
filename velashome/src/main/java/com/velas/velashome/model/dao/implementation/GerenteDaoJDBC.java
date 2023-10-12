package com.velas.velashome.model.dao.implementation;

import java.sql.Connection;

import com.velas.velashome.model.dao.GerenteDao;

public class GerenteDaoJDBC implements GerenteDao {

    private Connection conn;
    public GerenteDaoJDBC(Connection conn) {
        this.conn = conn;
    }
}
