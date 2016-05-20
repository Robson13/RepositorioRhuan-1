package loja.util;
import java.sql.Connection;
import loja.erros.ConexaoException;

public interface GerenciadorConexao {

	
	public Connection conectar() throws ConexaoException;
	
	public void desconectar(Connection c) throws ConexaoException;
	
	
	
	
}
