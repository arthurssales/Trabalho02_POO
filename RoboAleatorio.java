public class RoboAleatorio extends Robo{
    private int movimentoInvalido = 0;
    private int movimentoValido = 0;

    
    public RoboAleatorio(String cor) throws CorInvalidaException{
        super(cor);
    }
    
    @Override
    public void mover(int sentido) throws MovimentoInvalidoException{
        if(sentido == 1){
            //up
            if(coodY == 0){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Movimento inválido! Limite superior atingido!");
            }
            
            movimentoValido++;
            coodY--;
        }

        if(sentido == 2){
            //down
            if(coodY == 3){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Movimento inválido! Limite inferior atingido!");    
            }
            
            movimentoValido++;
            coodY++;
        }
        
        if(sentido == 3){
            //rigth
            if(coodX == 3){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Movimento inválido! Limite direito atingido!");
            }
            
            movimentoValido++;
            coodX++;
        }
        
        if(sentido == 4){
            //left
            if(coodX == 0){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Movimento inválido! Limite esquerdo atingido!");
            }
            
            movimentoValido++;
            coodX--;
        }
    }

    public String retornarNome(String cor){
        if(cor.equals("A"))
            return "azul"; 
        
        if(cor.equals("V"))
            return "vermelho"; 
        
        if(cor.equals("P"))
            return "preto"; 

        if(cor.equals("B"))
            return "branco";

        return null;
    }

    public int getMovimentoInvalido() {
        return movimentoInvalido;
    }

    public int getMovimentoValido() {
        return movimentoValido;
    }

    


}
