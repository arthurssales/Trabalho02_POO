public class Robo{
    protected String cor;
    protected int coodX = 0;
    protected int coodY = 0;
    protected int movimentoInvalido = 0;
    protected int movimentoValido = 0;
    
    public Robo(String cor)throws CorInvalidaException{
        if(!(cor.equals("azul") || cor.equals("vermelho") || cor.equals("preto") || cor.equals("branco")))
            throw new CorInvalidaException("Cor inválida!");
        
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
            if(coodY == 0)
                throw new MovimentoInvalidoException("Erro! Limite superior atingido!");
            
            coodY--;       
        }

        if(direcao.equals("down")){
            if(coodY == 3)
                throw new MovimentoInvalidoException("Erro! Limite inferior atingido!");    
            
            coodY++;
        }
        
        if(direcao.equals("right")){
            if(coodX == 3)
                throw new MovimentoInvalidoException("Erro! Limite direito atingido!");
            
            coodX++;          
        }
        
        if(direcao.equals("left")){
            if(coodX == 0)
                throw new MovimentoInvalidoException("Erro! Limite esquerdo atingido!");
            
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
    
    public String getCor() {
        return cor;
    }
   
    public void setCor(String cor) {
        this.cor = cor;
    }

   
    public int getCoodX() {
        return coodX;
    }

    public void setCoodX(int coodX)throws CoordenadaInvalidaException {
        if(coodX < 0 || coodX > 3)
            throw new CoordenadaInvalidaException("Coordenada inválida!");
    
        this.coodX = coodX;
    }

    public int getCoodY(){
        return coodY;
    }

    public void setCoodY(int eixoY)throws CoordenadaInvalidaException{
        if(coodY < 0 || coodY > 3)
            throw new CoordenadaInvalidaException("Coordenada inválida!");
        
        this.coodY = eixoY;
    }

    public int getMovimentoInvalido() {
        return movimentoInvalido;
    }

    public void setMovimentoInvalido(int movimentoInvalido) {
        this.movimentoInvalido = movimentoInvalido;
    }

    public int getMovimentoValido() {
        return movimentoValido;
    }

    public void setMovimentoValido(int movimentoValido) {
        this.movimentoValido = movimentoValido;
    }

   

}
