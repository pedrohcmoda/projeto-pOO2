package com.velas.velashome.model.dao.implementation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.velas.velashome.db.DB;
import com.velas.velashome.db.DbException;
import com.velas.velashome.model.dao.Estoque_ProdutoDao;
import com.velas.velashome.model.entities.Estoque;
import com.velas.velashome.model.entities.Estoque_Produto;

public class Estoque_ProdutoDaoJDBC implements Estoque_ProdutoDao{

    private Connection conn;
    
    public Estoque_ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    };

    @Override
    public void insert(Estoque_Produto obj, int id){
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
            "CALL add_produto_estoque(?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getProNome());
            st.setFloat(2, obj.getProPreco());
            st.setInt(3, obj.getProCategoria());
            st.setInt(4, obj.getForId());
            st.setInt(5, obj.getEstQuantidade());
            st.setString(6, obj.getEstLocal());
            st.setDate(7,(Date) obj.getEstDataEntrada());
            st.setDate(8,(Date) obj.getEstDataValidade());
            st.setInt(9, id);
            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    id = rs.getInt(1);
                    obj.setProId(id);
                }
            }else{
                throw new DbException("Erro inesperado!, Nenhuma linha afetada");
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }
    @Override
    public void update(Estoque_Produto obj, int id){
        PreparedStatement st =null;
        try{
            st = conn.prepareStatement(
            "CALL upd_produto_estoque(?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getProNome());
            st.setFloat(2, obj.getProPreco());
            st.setInt(3, obj.getProCategoria());
            st.setInt(4, obj.getForId());
            st.setInt(5, obj.getEstQuantidade());
            st.setString(6, obj.getEstLocal());
            st.setDate(7,(Date) obj.getEstDataEntrada());
            st.setDate(8,(Date) obj.getEstDataValidade());
            st.setInt(9, id);
            st.executeUpdate();
        } catch(SQLException e){
            throw new DbException(e.getMessage());
        } finally{
            DB.closeStatement(st);
        }
    };
    
    @Override
    public void delete(Estoque_Produto obj, int id){
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("CALL del_produto_estoque(?, ?)");
            st.setInt(1, obj.getProId());
            st.setInt(2, id);
            st.executeUpdate();
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    };

    @Override
    public List<Estoque_Produto>findAll(){
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT * FROM ver_produto_estoque");
            rs = st.executeQuery();
            List<Estoque_Produto> result = new ArrayList<>();

            while(rs.next()){
                Estoque_Produto estoque = pegaInfo(rs);
                result.add(estoque);
            }
            return result;  
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    };

    public Estoque_Produto pegaInfo(ResultSet rs) throws SQLException {
        Estoque_Produto estoque = new Estoque_Produto();
        estoque.setEstId(rs.getInt("estId"));
        estoque.setProNome(rs.getString("proNome"));
        estoque.setProPreco(rs.getFloat("proPreco"));
        estoque.setProCategoria(rs.getInt("proCategoria"));
        estoque.setForId(rs.getInt("forId"));
        estoque.setEstQuantidade(rs.getInt("estQuantidade"));
        estoque.setEstLocal(rs.getString("estLocal"));
        estoque.setEstDataEntrada(rs.getDate("estDataEntrada"));
        estoque.setEstDataValidade(rs.getDate("estDataValidade"));
        estoque.setProId(rs.getInt("proId"));
        return estoque;
    }
}
