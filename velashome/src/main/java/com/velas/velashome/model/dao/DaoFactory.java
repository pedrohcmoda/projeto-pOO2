package com.velas.velashome.model.dao;

import com.velas.velashome.db.DB;

import com.velas.velashome.model.dao.implementation.ProdutoDaoJDBC;
import com.velas.velashome.model.dao.implementation.EstoqueDaoJDBC;
import com.velas.velashome.model.dao.implementation.GerenteDaoJDBC;
import com.velas.velashome.model.dao.implementation.AuditoriaDaoJDBC;
import com.velas.velashome.model.dao.implementation.ClienteDaoJDBC;
import com.velas.velashome.model.dao.implementation.TransportadoraDaoJDBC;
import com.velas.velashome.model.dao.implementation.VendaDaoJDBC;

public class DaoFactory {

    public static ProdutoDao createProdutoDao() throws ClassNotFoundException {
        return new ProdutoDaoJDBC(DB.getConnection());
    }

    public static EstoqueDao createEstoqueDao() throws ClassNotFoundException {
        return new EstoqueDaoJDBC(DB.getConnection());
    }

    public static GerenteDao createGerenteDao() throws ClassNotFoundException {
        return new GerenteDaoJDBC(DB.getConnection());
    }

    public static AuditoriaDao createAuditoriaDao() throws ClassNotFoundException {
        return new AuditoriaDaoJDBC(DB.getConnection());
    }

    public static ClienteDao createClienteDao() throws ClassNotFoundException {
        return new ClienteDaoJDBC(DB.getConnection());
    }

    public static TransportadoraDao createTransportadoraDao() throws ClassNotFoundException {
        return new TransportadoraDaoJDBC(DB.getConnection());
    }

    public static VendaDao createVendaDao() throws ClassNotFoundException {
        return new VendaDaoJDBC(DB.getConnection());
    }
}
