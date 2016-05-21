package loja.acesso;

import java.util.ArrayList;
import loja.erros.ConexaoException;
import loja.erros.RepositorioException;
import loja.negocio.basica.Cliente;

/**
 *
 * @author Rhuan
 */
public interface DAOCliente {

    /**
     * Grava um cliente no BD.
     * @param c Objeto contendo todos os dados tratados e validados do Animal 
     * @throws loja.erros.ConexaoException 
     * @throws loja.erros.RepositorioException 
     */
    public void inserir(Cliente c)throws ConexaoException, RepositorioException;
    
    /**
     * Altera um cliente do BD.
     * @param c Objeto contendo todos os dados tratados e validados do Animal
     * @throws ConexaoException
     * @throws RepositorioException 
     */
    public void alterar(Cliente c)throws ConexaoException, RepositorioException;
    
    /**
     * Exclui um cliente do BD.
     * @param c Objeto contendo todos os dados tratados e validados do Animal
     * @throws ConexaoException
     * @throws RepositorioException 
     */
    public void excluir(Cliente c) throws ConexaoException, RepositorioException;
    
    /**
     * Exclui um cliente do BD.
     * @param id atributo com o id do cliente
     * @throws ConexaoException
     * @throws RepositorioException 
     */
    public void excluir(Integer id)throws ConexaoException, RepositorioException;
    
    /**
     * Lista todos os cliente do BD.
     * @return uma lista de clientes
     * @throws ConexaoException
     * @throws RepositorioException 
     */
    public ArrayList<Cliente> listar()throws ConexaoException, RepositorioException;
    
    /**
     * Pesquisa um cliente no banco
     * @param id atributo com o id do cliente
     * @return clientes num Array
     * @throws ConexaoException
     * @throws RepositorioException 
     */
    public Cliente pesquisar(Integer id)throws ConexaoException, RepositorioException;
}
