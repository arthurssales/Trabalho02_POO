public class Matriz {
    private String[][] tabuleiro = new String[6][6];
    private int eixoX, eixoY;
    private int i, j;
    
    
    public Matriz(int eixoYAli,int eixoXAli){
        for(i = 0; i < 6; i++){
            for(j = 0; j < 6; j++){
                    tabuleiro[i][j] = "0";
            }
        }
        tabuleiro[eixoYAli][eixoXAli] = "^";
    }   

   public void trocarPosicoes(int eixoX,int eixoY){

   }


   /*public void construirTabuleiro(){
        for(i = 0; i < 6; i++){
            for(j = 0; j < 6; j++){
                tabuleiro[i][j] = "0";
            }
        }
    }*/
    
    public void ImprimirTabuleiro(){
        //criei outro metodo para a construção do tabuleiro
        /*for(i = 0; i < 6; i++){
            for(j = 0; j < 6; j++){
                tabuleiro[i][j] = "0";
                }
                }
                tabuleiro[eixoX][eixoY] = "1";
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
        tabuleiro[eixoY][eixoX] = "C";
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
        return eixoX;
    }

    public void setCoodx(int eixoX) {
        this.eixoX = eixoX;
    }

    public int getCoody() {
        return eixoY;
    }

    public void setCoody(int eixoY) {
        this.eixoY = eixoY;
    }
}

