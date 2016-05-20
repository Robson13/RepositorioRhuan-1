/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja;

import java.sql.Connection;
import javax.swing.JOptionPane;
import loja.acesso.DAOFornecedorImpl;
import loja.erros.ConexaoException;
import loja.erros.RepositorioException;
import loja.negocio.basica.Fornecedor;
import loja.util.GerenciadorConexao;
import loja.util.GerenciadorConexaoMysql;

/**
 *
 * @author Programador
 */
public class Loja {

    private static final GerenciadorConexaoMysql conn =  GerenciadorConexaoMysql.getInstancia();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ConexaoException,RepositorioException {
        // TODO code application logic here
        try{
        
            Connection g =  conn.conectar();
            DAOFornecedorImpl dao = new DAOFornecedorImpl();
           
            Fornecedor f = new Fornecedor();
            f.setCnpj("123123123178");
            f.setNome("Rhuan");
            f.setDataCadastro("2016-05-18");
            dao.inserir(f);
            
            JOptionPane.showMessageDialog(null, "Funfou");
            
        }catch(ConexaoException c){
        
            JOptionPane.showMessageDialog(null, "pau na conexao");
        }catch(RepositorioException r){
            JOptionPane.showMessageDialog(null, "pau no repositorio");
        
        }
        
        
        
        
    }
    
}
