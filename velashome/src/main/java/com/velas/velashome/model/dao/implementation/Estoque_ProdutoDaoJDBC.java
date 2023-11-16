package com.velas.velashome.model.dao.implementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.velas.velashome.model.dao.Estoque_ProdutoDao;
import com.velas.velashome.model.entities.Estoque_Produto;

public class Estoque_ProdutoDaoJDBC implements Estoque_ProdutoDao{

    private Connection conn;
    
    public void Estoque_ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    };

    @Override
    public void insert(Estoque_Produto obj, int id){

    }
    @Override
    public void update(Estoque_Produto obj){

    };
    
    @Override
    public void delete(Estoque_Produto obj){
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("");
            st.setString(1, obj.getEstId()
        }
    };

    @Override
    public List<Estoque_Produto>findAll(){
        List<Estoque_Produto> List= (java.util.List<Estoque_Produto>) new Estoque_Produto();
        return List;
    };

    public Estoque_Produto pegaInfo(ResultSet rs) throws SQLException {
        return null;
        
    }
}
