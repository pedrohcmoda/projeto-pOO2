package com.velas.velashome.model.dao.implementation;

import com.velas.velashome.db.DB;
import com.velas.velashome.db.DbException;
import com.velas.velashome.model.dao.Venda_ProdutoDao;
import com.velas.velashome.model.entities.Venda_Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Venda_ProdutoDaoJDBC implements Venda_ProdutoDao {

    private Connection conn;

    public Venda_ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Venda_Produto> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM venda_produto");
            rs = st.executeQuery();

            List<Venda_Produto> list = new ArrayList<>();

            while (rs.next()) {
                Venda_Produto vendaProduto = pegaInfo(rs);
                list.add(vendaProduto);
            }

            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
    private Venda_Produto pegaInfo(ResultSet rs) throws SQLException {
        Venda_Produto tr = new Venda_Produto();
        tr.setId(rs.getInt("id"));
        tr.setCliente_id(rs.getInt("cliente_id"));
        tr.setValorTotal(rs.getFloat("valorTotal"));
        tr.setProduto_id(rs.getInt("produto_id"));
        tr.setProduto_nome(rs.getString("produto_nome"));
        tr.setData_hora(rs.getDate("data_hora"));
        tr.setQuantidade_produto(rs.getInt("quantidade_produto"));
        tr.setTransportadora_id(rs.getInt("transportadora_id"));
        return tr;
    }
}                
