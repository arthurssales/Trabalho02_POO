public class Robo{
    protected String cor;
    protected int eixoX = 0;
    protected int eixoY = 0;
    protected boolean encontrouAlimento;  

    
    public Robo(String cor)throws CorInvalidaException{
        if(!(cor.equals("azul") || cor.equals("vermelho") || cor.equals("preto") || cor.equals("branco")))
            throw new CorInvalidaException("Cor inválida!");
        
        this.eixoX = 0;
        this.eixoY = 0;

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
    public void mover(String direcao) throws MovimentoInvalidoException,NomeSentidoInvalidaException{
        
        if(!(direcao.equals("up") || direcao.equals("down") || (direcao.equals("right")) || direcao.equals("left")))         
            throw new NomeSentidoInvalidaException("Direção inválida! Use: up, down, right ou left");
        
        if(direcao.equals("up")){
            if(eixoY == 0)
                throw new MovimentoInvalidoException("Erro! Limite superior atingido!");
            
            eixoY--;
            
        }

        if(direcao.equals("down")){
            if(eixoY == 5)
                throw new MovimentoInvalidoException("Erro! Limite inferior atingido!");    
            
            eixoY++;
            
           
        }
        
        if(direcao.equals("right")){
            if(eixoX == 5)
                throw new MovimentoInvalidoException("Erro! Limite direito atingido!");
            
            eixoX++;
            
        }
        
        if(direcao.equals("left")){
            if(eixoX == 0)
                throw new MovimentoInvalidoException("Erro! Limite esquerdo atingido!");
            
            eixoX--;
            
           
        }
        
    }
    
    public void mover(int sentido) throws NumeroSentidoInvalidoException, MovimentoInvalidoException{
        if(!( (sentido == 1) || (sentido == 2) || (sentido == 3) || (sentido == 4) ) )
            throw new NumeroSentidoInvalidoException("Direção inválida! Use: 1 (up), 2 (down), 3 (right), 4 (left)");
        
        if(sentido == 1){
            //up
            if(eixoY == 0)
                throw new MovimentoInvalidoException("Erro! Limite superior atingido!");
            
            eixoY--;
            

        }

        if(sentido == 2){
            //down
            if(eixoY == 5)
                throw new MovimentoInvalidoException("Erro! Limite inferior atingido!");    
            
            eixoY++;
            

        }
        
        if(sentido == 3){
            //rigth
            if(eixoX == 5)
                throw new MovimentoInvalidoException("Erro! Limite direito atingido!");
            
            eixoX++;
            

        }
        
        if(sentido == 4){
            //left
            if(eixoX == 0)
                throw new MovimentoInvalidoException("Erro! Limite esquerdo atingido!");
            
            eixoX--;
            

        }
    }

    public boolean alimentoEncontrado(int eixoY,int eixoX){
        return (eixoY == this.eixoY && eixoX == this.eixoX);
    }   
    
    
  
    public String getCor() {
        return cor;
    }
   
    public void setCor(String cor) {
        this.cor = cor;
    }

   
    public int getEixoX() {
        return eixoX;
    }

    public void setEixoX(int eixoX) {
        this.eixoX = eixoX;
    }

    public int getEixoY() {
        return eixoY;
    }

    public void setEixoY(int eixoY) {
        this.eixoY = eixoY;
    }

    public boolean isEncontrouAlimento() {
        return encontrouAlimento;
    }

}
