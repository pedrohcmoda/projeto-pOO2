package com.velas.velashome.model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.velas.velashome.db.DB;
import com.velas.velashome.db.DbException;
import com.velas.velashome.model.dao.AuditoriaDao;
import com.velas.velashome.model.entities.Auditoria;

public class AuditoriaDaoJDBC implements AuditoriaDao{
    
    private Connection conn;
    public AuditoriaDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public List<Auditoria> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM auditoria ORDER BY id");

            rs = st.executeQuery();
            List<Auditoria> list = new ArrayList<>();

            while (rs.next()) {
                Auditoria tr = pegaInfo(rs);
                list.add(tr);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Auditoria pegaInfo(ResultSet rs) throws SQLException {
        Auditoria tr = new Auditoria();
        tr.setId(rs.getInt("id"));
        tr.setProdutoId(rs.getInt("produto_id"));
        tr.setProduto_nome(rs.getString("produto_nome"));
        tr.setData_hora(rs.getDate("data_hora"));
        tr.setAcao(rs.getBoolean("acao"));
        return tr;
    }

}
