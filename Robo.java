public class Robo{
    protected int cor;
    protected int eixoX = 0;
    protected int eixoY = 0;
    protected boolean encontrouAlimento;  


    public Robo(int cor)throws CoordenadaInvalidaException{ /*mudar exception*/
    if( !(cor.equals("azul") || cor.equals("vermelho") || cor.equals("preto") || cor.equals("branco")) )
throw new CoordenadaInvalidaException("Cor indisponível");

        this.eixoX = 0;
        this.eixoY = 0;

if(cor.equals("azul"))
this.cor = "A";
if(cor.equals ("vermelho"))
this.cor = "V";

if(cor.equals ("preto"))
this.cor = "P";

if(cor.equals("branco"))
this.cor = "B"
        

    }   

    Plano tabuleiro = new Plano();
    

    public void mover(String direcao) throws MovimentoInvalidoException,NomeSentidoInvalidaException{
        
        if(!(direcao.equals("up") || direcao.equals("down") || (direcao.equals("right")) || direcao.equals("left")))         
            throw new NomeSentidoInvalidaException("Direção inválida! Use: up, down, right ou left");
        
        if(direcao.equals("down")){
            if(eixoY == 5)
                throw new MovimentoInvalidoException("Erro! Limite inferior atingido!");    
            
            eixoY++;
            Plano posicaoRobo = new Plano();
            posicaoRobo.posicionarRobo(eixoY, eixoX);
            
            tabuleiro.construirTabuleiro(eixoY,eixoX);
            tabuleiro.ImprimirTabuleiro();
    
        }
        
        
        if(direcao.equals("up")){
            if(eixoY == 0)
                throw new MovimentoInvalidoException("Erro! Limite superior atingido!");
            
            eixoY--;
            Plano posicaoRobo = new Plano();
            posicaoRobo.posicionarRobo(eixoY, eixoX);
            
            tabuleiro.construirTabuleiro(eixoY,eixoX);
            tabuleiro.ImprimirTabuleiro();
        }
        
        if(direcao.equals("right")){
            if(eixoX == 5)
                throw new MovimentoInvalidoException("Erro! Limite direito atingido!");
            
            eixoX++;
            Plano posicaoRobo = new Plano();
            posicaoRobo.posicionarRobo(eixoY, eixoX);
            tabuleiro.construirTabuleiro(eixoY,eixoX);
            tabuleiro.ImprimirTabuleiro();
            
        }
        
        if(direcao.equals("left")){
            if(eixoX == 0)
                throw new MovimentoInvalidoException("Erro! Limite esquerdo atingido!");
            
            eixoX--;
            
            Plano posicaoRobo = new Plano();
            posicaoRobo.posicionarRobo(eixoY, eixoX);
            tabuleiro.construirTabuleiro(eixoY,eixoX);
            tabuleiro.ImprimirTabuleiro();
            
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
            Plano posicaoRobo = new Plano();
            posicaoRobo.posicionarRobo(eixoY, eixoX);
            tabuleiro.construirTabuleiro(eixoY,eixoX);
            tabuleiro.ImprimirTabuleiro();
        }

        if(sentido == 2){
            //down
            if(eixoY == 5)
                throw new MovimentoInvalidoException("Erro! Limite inferior atingido!");    
            
            eixoY++;
            Plano posicaoRobo = new Plano();
            posicaoRobo.posicionarRobo(eixoY, eixoX);
            tabuleiro.construirTabuleiro(eixoY,eixoX);
            tabuleiro.ImprimirTabuleiro();
        }
        
        if(sentido == 3){
            //rigth
            if(eixoX == 5)
                throw new MovimentoInvalidoException("Erro! Limite direito atingido!");
            
            eixoX++;
            Plano posicaoRobo = new Plano();
            posicaoRobo.posicionarRobo(eixoY, eixoX);
            tabuleiro.construirTabuleiro(eixoY,eixoX);
            tabuleiro.ImprimirTabuleiro();
        }
        
        if(sentido == 4){
            //left
            if(eixoX == 0)
                throw new MovimentoInvalidoException("Erro! Limite esquerdo atingido!");
            
            eixoX--;
            Plano posicaoRobo = new Plano();
            posicaoRobo.posicionarRobo(eixoY, eixoX);
            tabuleiro.construirTabuleiro(eixoY, eixoX);
            tabuleiro.ImprimirTabuleiro();


        }
    }

    public void alimentoEncontrado(int eixoY,int eixoX){
        //devo acessar o tabuleiro e informar se o valor da coordenada é "^";" 
        int i,j;
        for (i = 0; i < 6; i++) {
            for (j = 0 ; j < 6 ; j++){
                if(tabuleiro.retornaElemento(i,j).equals("^"));
                    encontrouAlimento = true;
            }   
        }
    }



    public int getCor() {
        return cor;
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

    public Plano getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Plano tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    
}
