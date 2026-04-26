public class NomeSentidoInvalidoException extends Exception {
    
    public NomeSentidoInvalidoException(String mensagem){
        super(mensagem);
    }
    
    
    //fazer desta forma
    @Override
    public String toString(){
        return "Nome inválido!";
    }


    
}
