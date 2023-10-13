package com.velas.velashome.model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.velas.velashome.db.DB;
import com.velas.velashome.db.DbException;
import com.velas.velashome.model.dao.ClienteDao;
import com.velas.velashome.model.entities.Cliente;

public class ClienteDaoJDBC implements ClienteDao {
    
    private Connection conn;
    public ClienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public List<Cliente> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM cliente ORDER BY id");

            rs = st.executeQuery();
            List<Cliente> list = new ArrayList<>();

            while (rs.next()) {
                Cliente tr = pegaInfo(rs);
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

    private Cliente pegaInfo(ResultSet rs) throws SQLException {
        Cliente tr = new Cliente();
        tr.setId(rs.getInt("id"));
        tr.setNome(rs.getString("nome"));
        tr.setTelefone(rs.getString("telefone"));
        return tr;
    }
}
