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
import loja.negocio.basica.Fornecedor;
import loja.util.GerenciadorConexao;
import loja.util.GerenciadorConexaoMysql;

/**
 *
 * @author Programador
 */
public class DAOFornecedorImpl  implements DAOFornecedor{

    private static final GerenciadorConexao g = GerenciadorConexaoMysql.getInstancia();
    
    @Override
    public void inserir(Fornecedor f) throws ConexaoException,RepositorioException {
        
        Connection c = g.conectar();
        String sql  = "INSERT INTO fornecedor (nome,cnpj,data_cadastro) VALUES (?,?,?)";
     
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1,f.getNome());
            pstm.setString(2,f.getCnpj());
            pstm.setString(3,f.getDataCadastro());
            pstm.execute();
        } catch (SQLException ex) {
            throw new RepositorioException();
        } finally{
            g.desconectar(c);
        }
        
    }
    
    

    @Override
    public void alterar(Fornecedor f) throws ConexaoException,RepositorioException {
       
        Connection c = g.conectar();
        String sql  = "UPDATE fornecedor SET nome=?,cnpj=? WHERE id=?";
     
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1,f.getNome());
            pstm.setString(2,f.getCnpj());
            pstm.setInt(3,f.getId());
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
        String sql  = "DELETE fornecedor  WHERE id=?";
     
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
    public ArrayList<Fornecedor> listar() throws ConexaoException,RepositorioException {
    
    Connection c = g.conectar();
        String sql  = "SELECT id,nome,cnpj,data_cadastro FROM fornecedor ";
     
        ArrayList<Fornecedor> af =  new ArrayList<Fornecedor>();
        try {
            Statement stm = c.createStatement();
            ResultSet rs =  stm.executeQuery(sql);
            while(rs.next()){
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setDataCadastro(rs.getString("data_cadastro"));
                af.add(f);
            }
            
            return af;
        } catch (SQLException ex) {
            throw new RepositorioException();
        } finally{
            g.desconectar(c);
        }
    
    
    }

    @Override
    public Fornecedor pesquisar(Integer id) throws ConexaoException,RepositorioException {
            
        Connection c = g.conectar();
        String sql  = "SELECT id,nome,cnpj,data_cadastro FROM fornecedor  WHERE id=?";
     
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setInt(1,id);
            ResultSet rs =  pstm.executeQuery();
            Fornecedor f =  null;
            if(rs.next()){
                f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setDataCadastro(rs.getString("data_cadastro"));
          
            }else{
                throw new RepositorioException("Pesquisa nao encontrada");
            }
            
            return f;
        } catch (SQLException ex) {
            throw new RepositorioException();
        } finally{
            g.desconectar(c);
        }
  
    }
    
}
