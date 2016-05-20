/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.acesso;
import java.util.ArrayList;
import loja.erros.ConexaoException;
import loja.erros.RepositorioException;
import loja.negocio.basica.Fornecedor;

/**
 *
 * @author Programador
 */
public interface DAOFornecedor {
    
    
/**
 * Inserir um animal no banco de dados
 * @param f objeto contendo o fornecedor
 */
    public void inserir(Fornecedor f) throws ConexaoException,RepositorioException;
    
    public void alterar(Fornecedor f) throws ConexaoException,RepositorioException;
    
    public void excluir(Integer id) throws ConexaoException,RepositorioException;
    
    public ArrayList<Fornecedor> listar() throws ConexaoException,RepositorioException;
    
    public Fornecedor pesquisar(Integer id) throws ConexaoException,RepositorioException;
 
    
    
    
    
}
