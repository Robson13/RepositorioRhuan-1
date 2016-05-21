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
import java.util.logging.Level;
import java.util.logging.Logger;
import loja.erros.ConexaoException;
import loja.erros.RepositorioException;
import loja.negocio.basica.Vendedor;
import loja.util.GerenciadorConexao;
import loja.util.GerenciadorConexaoMysql;

/**
 *
 * @author Programador
 */
public class DAOVendedorImpl  implements DAOVendedor{

    private static final GerenciadorConexao g = GerenciadorConexaoMysql.getInstancia();
    
    @Override
    public void inserir(Vendedor v) throws ConexaoException,RepositorioException {
        
        Connection c = g.conectar();
        String sql  = "INSERT INTO vendedor (nome,data_cadastro) VALUES (?,?)";
     
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1,v.getNome());
            pstm.setString(2,v.getDataCadastro());
            pstm.execute();
        } catch (SQLException ex) {
            throw new RepositorioException();
        } finally{
            g.desconectar(c);
        }
        
    }
    
    

    @Override
    public void alterar(Vendedor v) throws ConexaoException,RepositorioException {
       
        Connection c = g.conectar();
        String sql  = "UPDATE vendedor SET nome=? WHERE id=?";
     
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1,v.getNome());
            pstm.setInt(3,v.getId());
            pstm.execute();
        } catch (SQLException ex) {
        
            throw new RepositorioException();
        
        } finally{
            g.desconectar(c);
        }
        
        
    }

    @Override
    public void excluir(Integer id) throws ConexaoException,RepositorioException {
        Connection c = g.conectar();
        String sql  = "DELETE vendedor  WHERE id=?";
     
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setInt(1,id);
            pstm.execute();
        } catch (SQLException ex) {
            throw new RepositorioException();
        } finally{
            g.desconectar(c);
        }
    }

    @Override
    public ArrayList<Vendedor> listar() throws ConexaoException,RepositorioException {
    
    Connection c = g.conectar();
        String sql  = "SELECT id,nome,data_cadastro FROM vendedor ";
     
        ArrayList<Vendedor> av =  new ArrayList<Vendedor>();
        try {
            Statement stm = c.createStatement();
            ResultSet rs =  stm.executeQuery(sql);
            while(rs.next()){
                Vendedor v = new Vendedor();
                v.setId(rs.getInt("id"));
                v.setNome(rs.getString("nome"));
                v.setDataCadastro(rs.getString("data_cadastro"));
                av.add(v);
            }
            
            return av;
        } catch (SQLException ex) {
            throw new RepositorioException();
        } finally{
            g.desconectar(c);
        }
    
    
    }

    @Override
    public Vendedor pesquisar(Integer id) throws ConexaoException,RepositorioException {
            
        Connection c = g.conectar();
        String sql  = "SELECT id,nome,data_cadastro FROM vendedor  WHERE id=?";
     
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setInt(1,id);
            ResultSet rs =  pstm.executeQuery();
            Vendedor v =  null;
            if(rs.next()){
                v = new Vendedor();
                v.setId(rs.getInt("id"));
                v.setNome(rs.getString("nome"));
                v.setDataCadastro(rs.getString("data_cadastro"));
          
            }else{
                throw new RepositorioException("Pesquisa nao encontrada");
            }
            
            return v;
        } catch (SQLException ex) {
            throw new RepositorioException();
        } finally{
            g.desconectar(c);
        }
  
    }
    
}
