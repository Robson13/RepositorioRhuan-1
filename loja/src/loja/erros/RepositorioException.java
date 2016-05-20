package loja.erros;

public class RepositorioException extends Exception {

    public RepositorioException(){
        super();
    }
        
    public RepositorioException(String x){
        super(x);
    }
    
    
    public RepositorioException(Exception e){
        super(e);
    }
    
}
