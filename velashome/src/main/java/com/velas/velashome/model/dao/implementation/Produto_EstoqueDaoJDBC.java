package com.velas.velashome.model.dao.implementation;

import com.velas.velashome.model.dao.Produto_EstoqueDao;
import com.velas.velashome.model.entities.Produto_Estoque;
import com.velas.velashome.db.DB;
import com.velas.velashome.db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Produto_EstoqueDaoJDBC implements Produto_EstoqueDao {

    private Connection conn;

    public Produto_EstoqueDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Produto_Estoque obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("CALL adicionar_produto(?, ?, ?, ?)");
            st.setString(1, obj.getNome());
            st.setInt(2, obj.getQuantidade());
            st.setFloat(3, obj.getPreco());
            st.setString(4, obj.getFabricante());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Produto_Estoque obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("CALL atualizar_produto(?, ?, ?, ?)");
            st.setString(1, obj.getNome());
            st.setInt(2, obj.getQuantidade());
            st.setFloat(3, obj.getPreco());
            st.setString(4, obj.getFabricante());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void delete(Produto_Estoque obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("CALL remover_produto(?)");
            st.setInt(1, obj.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Produto_Estoque> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM estoque_completo");
            rs = st.executeQuery();
            List<Produto_Estoque> list = new ArrayList<>();
            while (rs.next()) {
                Produto_Estoque obj = pegaInfo(rs);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Produto_Estoque pegaInfo(ResultSet rs) throws SQLException {
        Produto_Estoque obj = new Produto_Estoque();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setProduto_id(rs.getInt("produto_id"));
        obj.setQuantidade(rs.getInt("quantidade"));
        obj.setPreco(rs.getInt("preco"));
        obj.setFabricante(rs.getString("fabricante"));
        return obj;
    }
}
