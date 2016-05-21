/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.acesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import loja.erros.ConexaoException;
import loja.erros.RepositorioException;
import loja.negocio.basica.Produto;
import loja.util.GerenciadorConexao;
import loja.util.GerenciadorConexaoMysql;

/**
 *
 * @author robso
 */
public class DAOProdutoImpl implements DAOProduto{
public static final GerenciadorConexao g = GerenciadorConexaoMysql.getInstancia();
    
    @Override
    public void inserir(Produto a) throws ConexaoException, RepositorioException {
       
      Connection c = g.conectar(); 
        String sql  = "INSERT INTO Produto (nome,descricao,preco,fornecedor_id) VALUES (?,?,?,?)";
        
        try{
        PreparedStatement pstm = c.prepareStatement(sql);
        pstm.setString(1,a.getNome());
        pstm.setString(2,a.getDescricao());
        pstm.setDouble(3,a.getPreco());
        
        pstm.setInt(4,a.getFornecedor().getId());
        
        pstm.execute();
        }catch(SQLException e) {
            throw new RepositorioException();
        }finally {
            g.desconectar(c);
        }
        
    }

    @Override
    public void alterar(Produto a) throws ConexaoException, RepositorioException {
        Connection c = g.conectar();
        String sql = "UPDATE Produto set nome =?,descricao=?,id=?,preco=?";
        
    try{
    PreparedStatement pstm = c.prepareStatement(sql);
    pstm.setString(1,a.getNome());
    pstm.setString(2,a.getDescricao());
    pstm.setInt(3,a.getId());
    pstm.setDouble(5,a.getPreco());
    pstm.execute();
}catch(SQLException e){
    throw new RepositorioException();
}finally{
        g.desconectar(c);
    }
    }
    @Override
    public void excluir(Produto a) throws ConexaoException, RepositorioException {
        Connection c = g.conectar();
        String sql = "DELETE Produto WHERE id=?"; 
   try{
       PreparedStatement pstm = c.prepareStatement(sql);
       pstm.setInt(1,a.getId());
       pstm.execute();
   }catch(SQLException e){
       throw new RepositorioException();
       
   }finally{
       g.desconectar(c);
   }
    
   }

    @Override
    public ArrayList<Produto> Listar() throws ConexaoException, RepositorioException {
        Connection c = g.conectar();
        String sql = "SELECT nome =?,descricao =?,id =?,preco =? FROM Produto";
         ArrayList<Produto> ap = new ArrayList<Produto>();
         try{
             Statement stm = c.createStatement();
             ResultSet rs = stm.executeQuery(sql);
             while (rs.next()){
                 Produto a = new Produto();
                 a.setNome(rs.getString("nome"));
                 a.setDescricao(rs.getString("descricao"));
                 a.setId(rs.getInt("id"));
                 a.setPreco(rs.getDouble("preco"));  
                 ap.add(a);
             }
             return ap;
         }catch(SQLException e){
       throw new RepositorioException(); 
         }  finally{
       g.desconectar(c);
        
            
        }
    }
    @Override
    public Produto pesquisar(int id) throws ConexaoException, RepositorioException {
        Connection c = g.conectar();
        String sql = "SELECT nome,descricao,id,preco FROM Produto WHERE id=?";
        try{
        PreparedStatement pstm = c.prepareStatement(sql);
         pstm.setInt(1,id);
         ResultSet rs = pstm.executeQuery(sql);
         Produto a = null;
         if(rs.next()){
             a= new Produto();
             a.setNome(rs.getString("nome"));
                 a.setDescricao(rs.getString("descricao"));
                 a.setId(rs.getInt("id"));
                 a.setPreco(rs.getDouble("preco")); 
             
         }else{
             throw new RepositorioException("Pesquisa não encontrada");
         }
             return a;    
         } catch(SQLException e){
        throw new RepositorioException();  
      }finally {
    g.desconectar(c);
     }   
    }    
}
