public class NumeroSentidoInvalidoException extends Exception{

    public NumeroSentidoInvalidoException(String numeroInvalido){
        super(numeroInvalido);
    }


    //fazer desta forma
    @Override
    public String toString(){
        return "Nome inválido!";
    }

    
}