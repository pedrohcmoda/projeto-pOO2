package com.velas.velashome.model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.velas.velashome.db.DB;
import com.velas.velashome.db.DbException;
import com.velas.velashome.model.dao.GerenteDao;
import com.velas.velashome.model.entities.Gerente;

public class GerenteDaoJDBC implements GerenteDao {

    private Connection conn;
    public GerenteDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Gerente autenticar(Gerente obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM gerente WHERE email = ?");
            st.setString(1, obj.getEmail());

            rs = st.executeQuery();

            if (rs.next()) {
                String senhaArmazenada = rs.getString("senha");
                if (senhaArmazenada.equals(obj.getSenha())) {
                    Gerente gerente = new Gerente();
                    gerente.setId(rs.getInt("id"));
                    gerente.setNome(rs.getString("nome"));
                    gerente.setEmail(rs.getString("email"));
                    gerente.setSenha(rs.getString("senha"));
                    System.out.println("GERENTE AUTENTICADO!!!------- Nome: " + gerente.getNome());
                    return gerente;
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
        return null; // nao sei oq fazer quando falhar ainda
    }
    
}
