package loja.erros;

public class ValidacaoException extends Exception {

    public ValidacaoException(){
        super();
    }
        
    public ValidacaoException(String x){
        super(x);
    }
    
    public ValidacaoException(Exception e){
        super(e);
    }
    
}
