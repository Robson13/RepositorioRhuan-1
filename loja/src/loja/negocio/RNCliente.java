package loja.negocio;

import java.util.ArrayList;
import loja.acesso.DAOCliente;
import loja.acesso.DAOClienteImpl;
import loja.erros.ConexaoException;
import loja.erros.RepositorioException;
import loja.erros.ValidacaoException;
import loja.negocio.basica.Cliente;

/**
 *
 * @author Rhuan
 */
public class RNCliente {

    private final DAOCliente dao;
    //validarPreenchimento
    //verificarDuplicidade
    //inserir

    public RNCliente() {
        dao = new DAOClienteImpl();
    }
    
    /**
     * Guarda um Cliente no BD após ter os dados validados.
     * @param c Objeto com os dados preenchidos corretamente.
     * @throws ValidacaoException caso os dados não sejam preenchidos corretamente.
     * @throws loja.erros.ConexaoException  
     * @throws loja.erros.RepositorioException 
     */
    public void salvar(Cliente c) throws ValidacaoException,ConexaoException,RepositorioException{
        validar(c);
        duplicidade(c);
        gravar(c);
    }
    
    /**
     * Altera um registro no BD.
     * @param c Objeto com os dados preenchidos corretamente.
     * @throws ValidacaoException caso os dados não sejam preenchidos corretamente; o cliente pode não existir no banco
     * @throws ConexaoException
     * @throws RepositorioException 
     */
    public void alterar(Cliente c) throws ValidacaoException,ConexaoException,RepositorioException {
        validar(c);
        validaId(c);
        duplicidade(c);
        atualizar(c);
    }

    /**
     * Exclui um cliente do BD.
     * @param c Objeto com os dados preenchidos corretamente.
     * @throws ValidacaoException caso o cliente não exista no BD.
     * @throws ConexaoException
     * @throws RepositorioException 
     */
    public void excluir(Cliente c)throws ValidacaoException,ConexaoException,RepositorioException{
        validaId(c);
        existe(c);
        apagar(c);
    }
    
    /**
     *  Retorna um vetor com todos os clientes.
     * @return vetor com todos os clientes.
     * @throws ValidacaoException 
     */
    public ArrayList<Cliente> listarTodos()throws ValidacaoException{
        return listar();
    }
    
    /**
     * Pesquisa um cliente no BD através do ID.
     * @param c Objeto com os dados do cliente.
     * @return dados do cliente.
     * @throws ValidacaoException 
     */
    public Cliente pesquisarCodigo(Cliente c) throws ValidacaoException{
        return pesquisarId(c.getId());
    }
    
    /**
     * Pesquisa um cliente no BD através do CPF.
     * @param c Objeto com os dados do cliente.
     * @return dados do cliente.
     * @throws ValidacaoException 
     */
    public Cliente pesquisar(Cliente c) throws ValidacaoException{
        return pesquisarCpf(c.getCpf());
    }
    
//##############################################################################    
    /**
     * Verifica se os dados foram preenchidos corretamente.
     * @param c Objeto com os dados preenchidos.
     * @throws ValidacaoException caso os dados não estejam corretos.
     */
    private void validar(Cliente c) throws ValidacaoException {
        if (c == null) 
            throw new ValidacaoException("Objeto inválido");
        if ((c.getCpf() == null) || (c.getCpf().length() < 11) || (c.getCpf().isEmpty())) 
            throw new ValidacaoException("Cpf inválido");
        if ((c.getNome() == null) || (c.getNome().isEmpty()) || c.getNome().length() < 4) 
            throw new ValidacaoException("Nome inválido");
        if((c.getFone()==null)||(!c.getFone().startsWith("9"))||c.getFone().length()<9)
            throw new ValidacaoException("Fone inválido");
        if(c.getEmail().substring(0,c.getEmail().indexOf("@")).length()<4)
            throw new ValidacaoException("Email inválido");
    }
    
    /**
     * Verifica se já existe um cliente no BD através do CPF.
     * @param c Objeto com os dados preenchidos.
     * @throws ValidacaoException caso o cliente já exista no BD.
     */
    private void duplicidade(Cliente c) throws ValidacaoException {
        if (pesquisarCpf(c.getCpf()) != null)
            throw new ValidacaoException("Cliente já existe");    
    }
    
    /**
     * Grava um cliente no BD.
     * @param c Objeto com os dados preenchidos.
     * @throws ValidacaoException caso gere outras exceções(ConexãoException,RepositórioException).
     */
    private void gravar(Cliente c) throws ValidacaoException {
        try {
            dao.inserir(c);
        } catch (ConexaoException ex) {
            throw new ValidacaoException("Entre em contato com o ADM");
        } catch(RepositorioException e){
            throw new ValidacaoException("Entre em contato com o Suporte");
        }
    }
    
    /**
     * Atualiza os dados de um cliente no BD.
     * @param c Objeto com os dados preenchidos.
     * @throws ValidacaoException caso gere outras exceções(ConexãoException,RepositórioException).
     */
    private void atualizar(Cliente c) throws ValidacaoException {
        try {
            dao.alterar(c);
        } catch (ConexaoException ex) {
            throw new ValidacaoException("Entre em contato com o ADM");
        } catch(RepositorioException e){
            throw new ValidacaoException("Entre em contato com o Suporte");
        }
    }
    
    /**
     * Verifica se o cliente já existe no BD através do ID.
     * @param c Objeto com os dados preenchidos.
     * @throws ValidacaoException caso o cliente não exista no BD.
     */
    private void existe(Cliente c)throws ValidacaoException{
        if(pesquisarId(c.getId())==null)
            throw new ValidacaoException("Cliente inválido");
    }
    
    /**
     * Pesquisa um cliente no BD através do ID.
     * @param id atributo com o identificador do cliente.
     * @return Onjeto com os dados do cliente.
     * @throws ValidacaoException caso gere outras exceções(ConexãoException,RepositórioException).
     */
    public Cliente pesquisarId(Integer id) throws ValidacaoException {
        try {
            return dao.pesquisar(id);
        } catch (ConexaoException ex) {
            throw new ValidacaoException("Entre em contato com o ADM");
        } catch(RepositorioException e){
            throw new ValidacaoException("Entre em contato com o Suporte");
        }
    }
    
    /**
     * Pesquisa um cliente no BD através do CPF.
     * @param cpf atributo com o cpf do cliente.
     * @return Objeto com os dados do
     * @throws ValidacaoException caso gere outras exceções(ConexãoException,RepositórioException).
     */
    public Cliente pesquisarCpf(String cpf) throws ValidacaoException {
        try {
            return dao.pesquisar(cpf);
        } catch (ConexaoException ex) {
            throw new ValidacaoException("Entre em contato com o ADM");
        } catch(RepositorioException e){
            throw new ValidacaoException("Entre em contato com o Suporte");
        }
    }
    
    /**
     * Verifica se o id passado é válido.
     * @param c Objeto com os dados do cliente.
     * @throws ValidacaoException caso o id seja inválido.
     */
    private void validaId(Cliente c)throws ValidacaoException {
        if((c.getId()==null)||(c.getId()==0))
            throw new ValidacaoException("Codigo invalido");
    }
    
    /**
     * Deleta um cliente do BD.
     * @param c Objeto com os dados do cliente.
     * @throws ValidacaoException caso gere outras exceções(ConexãoException,RepositórioException).
     */
    private void apagar(Cliente c) throws ValidacaoException {
        try{
            dao.excluir(c);
        } catch(ConexaoException e){
            throw new ValidacaoException("Entre em contato com o ADM");
        } catch(RepositorioException e){
            throw new ValidacaoException("Entre em contato com o Suporte");
        }
    }

    private ArrayList<Cliente> listar() throws ValidacaoException {
        try {
            return dao.listar();
        } catch (ConexaoException ex) {
            throw new ValidacaoException("Entre em contato com o ADM");
        } catch(RepositorioException e){
            throw new ValidacaoException("Entre em contato com o Suporte");
        }
    }
}