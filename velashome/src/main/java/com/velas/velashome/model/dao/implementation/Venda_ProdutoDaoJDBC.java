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
                Venda_Produto vendaProduto = new Venda_Produto();
                vendaProduto.setId(rs.getInt("id"));
                vendaProduto.setCliente_id(rs.getInt("cliente_id"));
                vendaProduto.setValorTotal(rs.getFloat("valorTotal"));
                vendaProduto.setProduto_id(rs.getInt("produto_id"));
                vendaProduto.setProduto_nome(rs.getString("produto_nome"));
                vendaProduto.setData_hora(rs.getDate("data"));
                vendaProduto.setQuantidade_produto(rs.getInt("quantidade_produto"));
                vendaProduto.setTransportadora_id(rs.getInt("transportadora_id"));
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
}
