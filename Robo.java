
public class Robo{
    protected String cor;
    protected int coodX = 0;
    protected int coodY = 0;
    protected int movimentoInvalido = 0;
    protected int movimentoValido = 0;
    
    public Robo(String cor){
        this.coodX = 0;
        this.coodY = 0;

        if(cor.equals("azul"))
            this.cor = "A";
        
        if(cor.equals ("vermelho"))
            this.cor = "V";

        if(cor.equals ("preto"))
            this.cor = "P";

        if(cor.equals("branco"))
            this.cor = "B";       
    }   
    
//mudar o tipo para int o retorno deve ser a nova coordenada
    public void mover(String direcao) throws MovimentoInvalidoException,NomeSentidoInvalidoException{
        
        if(!(direcao.equals("up") || direcao.equals("down") || (direcao.equals("right")) || direcao.equals("left")))         
            throw new NomeSentidoInvalidoException("Direção inválida! Use: up, down, right ou left");
        
        if(direcao.equals("up")){
            if(coodY == 0){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Erro! Limite superior atingido!");
            }
        
            movimentoValido ++;
            coodY--;       
        }

        if(direcao.equals("down")){
            if(coodY == 3){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Erro! Limite inferior atingido!");    
            }
        
            movimentoValido++;
            coodY++;
        }
        
        if(direcao.equals("right")){
            if(coodX == 3){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Erro! Limite direito atingido!");
            }
            
            movimentoValido++;
            coodX++;          
        }
        
        if(direcao.equals("left")){
            if(coodX == 0){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Erro! Limite esquerdo atingido!");
            }
            movimentoValido++;
            coodX--;
        }
    }
            
            
    
    public void mover(int sentido) throws NumeroSentidoInvalidoException, MovimentoInvalidoException{
        if(!( (sentido == 1) || (sentido == 2) || (sentido == 3) || (sentido == 4) ) )
            throw new NumeroSentidoInvalidoException("Direção inválida! Use: 1 (up), 2 (down), 3 (right), 4 (left)");
        
        if(sentido == 1){
            //up
            if(coodY == 0){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Erro! Limite superior atingido!");
            }
            
            movimentoValido++;
            coodY--;
        }

        if(sentido == 2){
            //down
            if(coodY == 3){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Erro! Limite inferior atingido!");    
            }
            
            movimentoValido++;
            coodY++;
        }
        
        if(sentido == 3){
            //rigth
            if(coodX == 3){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Erro! Limite direito atingido!");
            }
            
            movimentoValido++;
            coodX++;
        }
        
        if(sentido == 4){
            //left
            if(coodX == 0){
                movimentoInvalido++;
                throw new MovimentoInvalidoException("Erro! Limite esquerdo atingido!");
            }
            
            movimentoValido++;
            coodX--;
        }
    }

    public boolean alimentoEncontrado(int eixoY,int eixoX){
        return (eixoY == this.coodY && eixoX == this.coodX);
    }   
    
    public String retornarCor(String cor){
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

    public String getCor() {
        return cor;
    }
   
    public int getCoodX() {
        return coodX;
    }   

    public int getCoodY(){
        return coodY;
    }

    public int getMovimentoInvalido() {
        return movimentoInvalido;
    }

    public int getMovimentoValido() {
        return movimentoValido;
    }
}
