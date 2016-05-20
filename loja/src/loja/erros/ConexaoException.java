package loja.erros;

public class ConexaoException extends Exception {

    public ConexaoException(){
        super();
    }
        
    public ConexaoException(String x){
        super(x);
    }
    
    
    public ConexaoException(Exception e){
        super(e);
    }
    
}
