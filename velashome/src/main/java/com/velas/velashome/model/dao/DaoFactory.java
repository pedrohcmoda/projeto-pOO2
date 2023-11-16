package com.velas.velashome.model.dao;

import com.velas.velashome.db.DB;
import com.velas.velashome.model.dao.implementation.AuditoriaDaoJDBC;
import com.velas.velashome.model.dao.implementation.FuncionarioDaoJDBC;
import com.velas.velashome.model.dao.implementation.ProdutoDaoJDBC;
import com.velas.velashome.model.dao.implementation.Estoque_ProdutoDaoJDBC;
import com.velas.velashome.model.dao.implementation.EstoqueDaoJDBC;
import com.velas.velashome.model.dao.implementation.TransportadoraDaoJDBC;
import com.velas.velashome.model.dao.implementation.FornecedoraDaoJDBC;

public class DaoFactory {

    public static AuditoriaDao createAuditoriaDao() throws ClassNotFoundException {
        return new AuditoriaDaoJDBC(DB.getConnection());
    }

    public static FuncionarioDao createFuncionarioDao() throws ClassNotFoundException {
        return new FuncionarioDaoJDBC(DB.getConnection());
    }

    public static ProdutoDao createProdutoDao() throws ClassNotFoundException {
        return new ProdutoDaoJDBC(DB.getConnection());
    }

    public static Estoque_ProdutoDao createEstoque_ProdutoDao() throws ClassNotFoundException {
        return new Estoque_ProdutoDaoJDBC(DB.getConnection());
    }

    public static EstoqueDao createEstoquueDao() throws ClassNotFoundException {
        return new EstoqueDaoJDBC(DB.getConnection());
    }

    public static TransportadoraDao createTransportadoraDao() throws ClassNotFoundException {
        return new TransportadoraDaoJDBC(DB.getConnection());
    }
    
    public static FornecedoraDao createFornecedoraDao() throws ClassNotFoundException {
        return new FornecedoraDaoJDBC(DB.getConnection());
    }
}
