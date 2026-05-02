public class Matriz {
    private final String[][] matriz = new String[4][4];
    private int i,j;

    //retirar o posicionamento do alimento
    public void construirMatriz(int posYAli,int posXAli,int coodY,int coodX,String corRobo){
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                matriz[i][j] = ".";
            }
        }
            matriz[posYAli][posXAli] = "^";
            matriz[coodY][coodX] = corRobo;
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
    
    //desnecessaria
    public void construirMatriz(int posYAli,int posXAli,int coodYR1,int coodXR1,String corRobo1,int coodYR2,int coodXR2, String corRobo2){
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                matriz[i][j] = ".";
            }
        }
        //talvez sejam desnecessários, ja que ja existem metodos que posicionam esses caracteres
            matriz[posYAli][posXAli] = "^";
            
            matriz[coodYR1][coodXR1] = corRobo1;
            matriz[coodYR2][coodXR2] = corRobo2;
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

    public void novaPosicaoRobo(int posYRobo,int posXRobo,String corRobo){
        matriz[posYRobo][posXRobo] = corRobo;
    }   
}
