/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.acesso;
import java.util.ArrayList;
import loja.erros.ConexaoException;
import loja.erros.RepositorioException;
import loja.negocio.basica.Vendedor;

/**
 *
 * @author Programador
 */
public interface DAOVendedor {
    
    
/**
 * Inserir um animal no banco de dados
 * @param Vendedor objeto contendo o Vendedor
 */
    public void inserir(Vendedor v) throws ConexaoException,RepositorioException;
    
    public void alterar(Vendedor v) throws ConexaoException,RepositorioException;
    
    public void excluir(Integer id) throws ConexaoException,RepositorioException;
    
    public ArrayList<Vendedor> listar() throws ConexaoException,RepositorioException;
    
    public Vendedor pesquisar(Integer id) throws ConexaoException,RepositorioException;
 
    
    
    
    
}
