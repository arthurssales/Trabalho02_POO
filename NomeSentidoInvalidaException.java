public class NomeSentidoInvalidaException extends Exception {
    
    public NomeSentidoInvalidaException(String mensagem){
        super(mensagem);
    }
    
    
    //fazer desta forma
    @Override
    public String toString(){
        return "Nome inválido!";
    }


    
}
