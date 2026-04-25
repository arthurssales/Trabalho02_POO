public class Plano {
    private String[][] tabuleiro = new String[6][6];
    private int coodx, coody;
    private int i, j;
    
    /*public Plano(int coodx, int coody){        
        this.coodx = coodx;
        this.coody = coody;
    }*/
    
   public void trocarPosicoes(int eixoX,int eixoY){

   }


   public void construirTabuleiro(int eixoY,int eixoX){
        for(i = 0; i < 6; i++){
            for(j = 0; j < 6; j++){
                tabuleiro[i][j] = "0";
            }
        }
        tabuleiro[eixoY][eixoX] = "A";
    }
    
    public void ImprimirTabuleiro(){
        //criei outro metodo para a construção do tabuleiro
        /*for(i = 0; i < 6; i++){
            for(j = 0; j < 6; j++){
                tabuleiro[i][j] = "0";
                }
                }
                tabuleiro[coodx][coody] = "1";
                */
        
               //mostrar apenas o robo
               for(i = 0; i < 6; i++){
                   for(j = 0; j < 6; j++){
                       System.out.print(tabuleiro[i][j]+ " ");
            }
            System.out.print("\n");
        }
    }
    
    public String retornaElemento(int i, int j){
        return tabuleiro[i][j];
    }

    public void posicionarRobo(int eixoY,int eixoX){
        tabuleiro[eixoY][eixoX] = "1";
    }
    
    public void posicionarAlimento(int eixoY, int eixoX){
        tabuleiro[eixoY][eixoX] = "^";
    } 

    
    public String[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(String[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public int getCoodx() {
        return coodx;
    }

    public void setCoodx(int coodx) {
        this.coodx = coodx;
    }

    public int getCoody() {
        return coody;
    }

    public void setCoody(int coody) {
        this.coody = coody;
    }
}

