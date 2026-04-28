import java.util.Random;

public class RoboInteligente extends Robo {
    Random random = new Random();
    public RoboInteligente(String cor)throws CorInvalidaException{
        super(cor);
    }
    
    @Override
    public void mover(int sentido)throws MovimentoInvalidoException{
        System.out.println("Sentido inicial: " + sentido);
        
        if(sentido == 1){
            //up
            if(coodY == 0){
                sentido = random.nextInt(4) + 1;
                this.mover(sentido);
                return;
            }
            movimentoValido++;
            coodY--;
        }
        
        if(sentido == 2){
            //down
            if(coodY == 3){
                sentido = random.nextInt(4) + 1;
                this.mover(sentido);
                return;            
            } 
            movimentoValido++;
            coodY++;
        }
        
        if(sentido == 3){
            //rigth
            if(coodX == 3){
                sentido = random.nextInt(4) + 1;
                this.mover(sentido);
                return;
            }
            movimentoValido++;
            coodX++;
        }
        
        if(sentido == 4){
            //left
            if(coodX == 0){
                sentido = random.nextInt(4) + 1;
                this.mover(sentido);
                return;         
            }
            movimentoValido++;
            coodX--;   
        }
    }
    
    
}





