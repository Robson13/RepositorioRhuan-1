/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.acesso;

import java.util.ArrayList;
import loja.negocio.basica.Produto;
import loja.erros.ConexaoException;
import loja.erros.RepositorioException;
/**
 *
 * @author robso
 */
public interface DAOProduto {
    
    public void inserir (Produto a) throws ConexaoException,RepositorioException; 
    public void alterar (Produto a) throws ConexaoException,RepositorioException;
    public void excluir (Produto a) throws ConexaoException,RepositorioException;
    public ArrayList<Produto>Listar() throws ConexaoException,RepositorioException;
    public Produto pesquisar(int id) throws ConexaoException,RepositorioException;
     
}
