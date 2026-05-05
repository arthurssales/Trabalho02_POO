public class Matriz {
    private final String[][] matriz = new String[4][4];
    private int i,j;

    public void construirMatriz(){
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                matriz[i][j] = ".";
            }
        }
    }
      
    public boolean posicionarAlimento(int posYAli,int posXAli){
        try{
            if(posYAli == 0 && posXAli == 0)
                return false;
            else{
                matriz[posYAli][posXAli] = "^";
                return true;
            }        
        }
        catch(Exception e){      
            return false;
        }
    }

    public boolean posicionarObstaculo(int posYObs, int posXObs,String tipo){
        try{ 
            if(!matriz[posYObs][posXObs].equals("."))
                return false;
            
            else{
                if(tipo.equals("Bomba"))
                    matriz[posYObs][posXObs] = "*";
                else
                    matriz[posYObs][posXObs] = "O";
            } 
                return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public void imprimirMatriz(){
        for(i=3;i>=0;i--){
            for(j=0;j<4;j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        
    }

    public void antigaPosicaoRobo(int posYRobo,int posXRobo){
        matriz[posYRobo][posXRobo] = ".";
    }

    public void antigaPosicaoRocha(int posYRobo,int posXRobo){
        matriz[posYRobo][posXRobo] = "O";
    }

    public void novaPosicaoRobo(int posYRobo,int posXRobo,String corRobo){
        matriz[posYRobo][posXRobo] = corRobo;
    }   
}
