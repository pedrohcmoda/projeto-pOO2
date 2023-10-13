package com.velas.velashome.model.dao.implementation;


import com.velas.velashome.db.DB;
import com.velas.velashome.db.DbException;
import com.velas.velashome.model.dao.TransportadoraDao;
import com.velas.velashome.model.entities.Transportadora;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportadoraDaoJDBC implements TransportadoraDao {

    private Connection conn;

    public TransportadoraDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Transportadora obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO transportadora (nome, telefone, cnpj, endereco) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getTelefone());
            st.setString(3, obj.getCnpj());
            st.setString(4, obj.getEndereco());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha afetada!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM transportadora WHERE id = ?");
            st.setInt(1, id);

            int rows = st.executeUpdate();

            if (rows == 0) {
                throw new DbException("ID não existe!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Transportadora> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM transportadora ORDER BY id");

            rs = st.executeQuery();
            List<Transportadora> list = new ArrayList<>();

            while (rs.next()) {
                Transportadora tr = new Transportadora();
                tr.setId(rs.getInt("id"));
                tr.setNome(rs.getString("nome"));
                tr.setTelefone(rs.getString("telefone"));
                tr.setCnpj(rs.getString("cnpj"));
                tr.setEndereco(rs.getString("endereco"));
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

    @Override
    public void update(Transportadora obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE transportadora SET nome = ?, telefone = ?, cnpj = ?, endereco = ? WHERE id = ?");
            st.setString(1, obj.getNome());
            st.setString(2, obj.getTelefone());
            st.setString(3, obj.getCnpj());
            st.setString(4, obj.getEndereco());
            st.setInt(5, obj.getId());
    
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
    
    @Override
    public Transportadora findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM transportadora WHERE id = ?");
            st.setInt(1, id);
    
            rs = st.executeQuery();
    
            if (rs.next()) {
                Transportadora tr = instantiateTransportadora(rs);
                return tr;
            }
            return null;  // Se não encontrar o ID, retornar null ou lançar uma exceção
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
    // Método auxiliar para criar uma instância de Transportadora a partir do ResultSet
    private Transportadora instantiateTransportadora(ResultSet rs) throws SQLException {
        Transportadora tr = new Transportadora();
        tr.setId(rs.getInt("id"));
        tr.setNome(rs.getString("nome"));
        tr.setTelefone(rs.getString("telefone"));
        tr.setCnpj(rs.getString("cnpj"));
        tr.setEndereco(rs.getString("endereco"));
        return tr;
    }
    
}
