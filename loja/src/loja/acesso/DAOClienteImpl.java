package loja.acesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import loja.erros.ConexaoException;
import loja.erros.RepositorioException;
import loja.negocio.basica.Cliente;
import loja.util.GerenciadorConexao;
import loja.util.GerenciadorConexaoMysql;

/**
 *
 * @author Rhuan
 */
public class DAOClienteImpl implements DAOCliente{
    
    private final GerenciadorConexao GC = GerenciadorConexaoMysql.getInstancia();
    
    @Override
    public void inserir(Cliente c) throws ConexaoException, RepositorioException {
        Connection cc = GC.conectar();
        String sql = "INSERT INTO cliente (nome,cpf,fone,email,endereco,data_cadastro) VALUES (?,?,?,?,?,?)";
        try{
            PreparedStatement pstm = cc.prepareStatement(sql);
            pstm.setString(1, c.getNome());
            pstm.setString(2, c.getCpf());
            pstm.setString(3, c.getFone());
            pstm.setString(4, c.getEmail());
            pstm.setString(5, c.getEndereco());
            pstm.setString(6, c.getData_cadastro());
            pstm.execute();
        }catch(SQLException e){
            throw new RepositorioException();
        }finally{
            GC.desconectar(cc);
        }
    }

    @Override
    public void alterar(Cliente c) throws ConexaoException, RepositorioException {
        Connection cc = GC.conectar();
        String sql = "UPDATE cliente SET nome = ?,cpf = ?,fone = ?, email = ?, endereco = ?, data_cadastro =? WHERE id = ? ";
        try{
            PreparedStatement pstm = cc.prepareStatement(sql);
            pstm.setString(1, c.getNome());
            pstm.setString(2, c.getCpf());
            pstm.setString(3, c.getFone());
            pstm.setString(4, c.getEmail());
            pstm.setString(5, c.getEndereco());
            pstm.setString(6, c.getData_cadastro());
            pstm.setInt(7, c.getId());
            pstm.execute();
        }catch(SQLException e){
            throw new RepositorioException();
        }finally{
            GC.desconectar(cc);
        }
    }

    @Override
    public void excluir(Cliente c) throws ConexaoException, RepositorioException {
        Connection cc = GC.conectar();
        String sql = "DELETE FROM cliente WHERE id = ?";
        try{
        PreparedStatement pstm = cc.prepareStatement(sql);
        pstm.setInt(1, c.getId());
        pstm.execute();
        }catch(SQLException e){
            throw new RepositorioException();
        }finally{
            GC.desconectar(cc);
        }
    }

    @Override
    public void excluir(Integer id) throws ConexaoException, RepositorioException {
        Connection cc = GC.conectar();
        String sql = "DELETE FROM cliente WHERE id = ?";
        try{
        PreparedStatement pstm = cc.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.execute();
        }catch(SQLException e){
            throw new RepositorioException();
        }finally{
            GC.desconectar(cc);
        }
    }

    @Override
    public ArrayList<Cliente> listar() throws ConexaoException, RepositorioException {
        Connection cc = GC.conectar();
        String sql = "SELECT id,nome,cpf,fone,email,endereco,data_cadastro FROM cliente";
        ArrayList lista = new ArrayList();
        try{
            Statement stm = cc.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setFone(rs.getString("fone"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                c.setData_cadastro(rs.getString("data_cadastro"));
                lista.add(c);
            }
        }catch(SQLException e){
            throw new RepositorioException();
        }
        return lista;
    }

    @Override
    public Cliente pesquisar(Integer id) throws ConexaoException, RepositorioException {
        Connection cc = GC.conectar();
        String sql = "SELECT id,nome,cpf,fone,email,endereco,data_cadastro FROM cliente WHERE id = ?";
        try{
            PreparedStatement pstm = cc.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            Cliente c = null;
            if(rs.next()){
                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setFone(rs.getString("fone"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                c.setData_cadastro(rs.getString("data_cadastro"));
                }
            return c;
            
        }catch(SQLException e){
            throw new RepositorioException();
        }finally{
            GC.desconectar(cc);
        }
    }
    
}
