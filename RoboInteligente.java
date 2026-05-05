import java.util.Random;
/*
1. O novo valor do sentido deve receber um valor entre 1 a 4 exceto o valor atual do sentido
 */
public class RoboInteligente extends Robo {
    Random random = new Random();
    
    public RoboInteligente(String cor){
        super(cor);
    }
    
    @Override
    public void mover(int sentido)throws MovimentoInvalidoException{
        
        //up
        if(sentido == 1){
            if(coodY == 3){
                System.out.println("Sentido invalido: " + sentido);
                sentido = random.nextInt(4) + 1; //selecionar numeros (2,3,4) para escolher
                System.out.println("Novo sentido:" + sentido);//apenas para mostrar
                this.mover(sentido);
                return;
            }
            movimentoValido++;
            coodY++;
        }
        
        //down
        if(sentido == 2){
            if(coodY == 0){
                System.out.println("Sentido invalido: " + sentido);
                sentido = random.nextInt(4) + 1; //selecionar numeros (1,3,4) para escolher
                System.out.println("Novo sentido:" + sentido);
                this.mover(sentido);
                return;            
            } 
            movimentoValido++;
            coodY--;
        }
        
        //rigth
        if(sentido == 3){
            if(coodX == 3){
                System.out.println("Sentido invalido: " + sentido);
                sentido = random.nextInt(4) + 1; //selecionar numeros (1,2,4) para escolher
                this.mover(sentido);
                System.out.println("Novo sentido:" + sentido);
                return;
            }
            movimentoValido++;
            coodX++;
        }
        
        //left
        if(sentido == 4){
            if(coodX == 0){
                System.out.println("Sentido invalido: " + sentido);
                sentido = random.nextInt(4) + 1; //selecionar numeros (1,2,3) para escolher
                System.out.println("Novo sentido:" + sentido);
                this.mover(sentido);
                return;         
            }
            movimentoValido++;
            coodX--;   
        }
    }
}