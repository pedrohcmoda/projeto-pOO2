package com.velas.velashome.model.dao.implementation;

import java.sql.Connection;

import com.velas.velashome.model.dao.AuditoriaDao;

public class AuditoriaDaoJDBC implements AuditoriaDao{
    
    private Connection conn;
    public AuditoriaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

}
