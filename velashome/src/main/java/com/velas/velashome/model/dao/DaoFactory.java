package com.velas.velashome.model.dao;

import com.velas.velashome.db.DB;
import com.velas.velashome.model.dao.implementation.GerenteDaoJDBC;
import com.velas.velashome.model.dao.implementation.Produto_EstoqueDaoJDBC;
import com.velas.velashome.model.dao.implementation.AuditoriaDaoJDBC;
import com.velas.velashome.model.dao.implementation.ClienteDaoJDBC;
import com.velas.velashome.model.dao.implementation.TransportadoraDaoJDBC;
import com.velas.velashome.model.dao.implementation.Venda_ProdutoDaoJDBC;

public class DaoFactory {

    public static Produto_EstoqueDao createProduto_EstoqueDao() throws ClassNotFoundException {
        return new Produto_EstoqueDaoJDBC(DB.getConnection());
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

    public static Venda_ProdutoDao createVenda_ProdutoDao() throws ClassNotFoundException {
        return new Venda_ProdutoDaoJDBC(DB.getConnection());
    }
}
