package com.velas.velashome.model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.velas.velashome.db.DB;
import com.velas.velashome.db.DbException;
import com.velas.velashome.model.dao.FuncionarioDao;
import com.velas.velashome.model.entities.Funcionario;

public class FuncionarioDaoJDBC implements FuncionarioDao {

    private Connection conn;

    public FuncionarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public List<Funcionario> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM funcionario ORDER BY funId");

            rs = st.executeQuery();
            List<Funcionario> list = new ArrayList<>();

            while (rs.next()) {
                Funcionario funcionario = pegaInfo(rs);
                list.add(funcionario);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Funcionario pegaInfo(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setFunId(rs.getInt("funId"));
        funcionario.setFunNome(rs.getString("funNome"));
        funcionario.setFunSobrenome(rs.getString("funSobrenome"));
        funcionario.setFunCpf(rs.getString("funCpf"));
        funcionario.setFunTelefone(rs.getString("funTelefone"));
        funcionario.setFunDepartamento(rs.getString("funDepartamento"));
        funcionario.setFunSalario(rs.getFloat("funSalario"));
        return funcionario;
    }
}