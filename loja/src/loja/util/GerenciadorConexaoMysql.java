package loja.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import loja.erros.ConexaoException;

public class GerenciadorConexaoMysql implements GerenciadorConexao{

	private static GerenciadorConexaoMysql conn;
	private final String URL;
	private final String USUARIO;
	private final String SENHA;
	
	public static GerenciadorConexaoMysql getInstancia(){
		if(conn ==null)
			conn = new GerenciadorConexaoMysql();
		return conn;
	}
	
	private  GerenciadorConexaoMysql (){
		
		ResourceBundle rb = ResourceBundle.getBundle("loja.util.banco"); 
		URL = rb.getString("url");
		USUARIO = rb.getString("usuario");
		SENHA = rb.getString("senha");
		
	}
	
	
	@Override
	public Connection conectar() throws ConexaoException{
		try{
			Connection c;
			c = DriverManager.getConnection(URL,USUARIO,SENHA);
                        return c;
                }catch(SQLException e){
			throw new ConexaoException();
		}
	}
        
	@Override
	public void desconectar(Connection c) throws ConexaoException{
            try{
                c.close();
            }catch(SQLException e){
                throw new ConexaoException();
            }
		
	}	
	
}
