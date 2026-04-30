import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main2 { 
    public static void main(String[] args) {
        
        
        /*Metodos para adicionar
        imprimir tabuleiro*/
        
        Scanner teclado = new Scanner(System.in);
        String[][] matriz = new String[4][4];
        
        int i,j;        
        
        Random random = new Random();
        ArrayList<Robo> robos = new ArrayList<>();
        
        boolean posicaoValida = false;
        Robo roboVencedor = null;
        
        int coodX, coodY;
        int posicaoXAli = 0, posicaoYAli = 0;
        
        String corRobo;        
        
        ArrayList<String> coresDisponiveis = new ArrayList<>();
        coresDisponiveis.add("azul");
        coresDisponiveis.add("vermelho");
        coresDisponiveis.add("preto");
        coresDisponiveis.add("branco");
        
        Robo robo1 = null;
        Robo robo2 = null;
    
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                matriz[i][j]= ".";
            }
        }
              
        do{   
            if(robo1 == null){
                System.out.println("Escolha uma cor pro robo 1"); 
                for(Object cor : coresDisponiveis){
                    System.out.printf("%s - ",cor);
                }    
                corRobo = teclado.nextLine();    
                
                if(coresDisponiveis.contains(corRobo)){
                    robo1 = new Robo(corRobo);
                    coresDisponiveis.remove(corRobo);    
                }
            }
                
            if(robo2 == null){
                System.out.println("Escolha uma cor pro robo 2"); 
                for(Object cor : coresDisponiveis){
                    System.out.printf("%s - ",cor);
                }    
                corRobo = teclado.nextLine();    
                if(coresDisponiveis.contains(corRobo)){    
                    robo2 = new Robo(corRobo);
                    coresDisponiveis.remove(corRobo);        
                }   
            }
        }while(robo1 == null || robo2 == null);
        
        robos.add(robo1);
        robos.add(robo2);
       
        while(!posicaoValida){
            System.out.println("Indique a posição do alimento. A posição (0,0) é invalida");
            
            System.out.println("Coordenada no eixo y (de 0 a 3): "); 
            posicaoYAli = teclado.nextInt();
            teclado.nextLine();        
            
            System.out.println("Coordenada no eixo x (de 0 a 3): ");
            posicaoXAli = teclado.nextInt();
            teclado.nextLine();    
            
            if(posicaoYAli == 0 && posicaoXAli == 0)
                System.out.println("Coordenada inválida!");
                
            else{
                try{    
                    matriz[posicaoYAli][posicaoXAli] = "^";
                    posicaoValida = true;    
                }
                catch(IndexOutOfBoundsException e){
                    System.out.println("Coordenada inválida!");
                }                   
            }
        }      
        limparTela();
        
        do{
            for(Robo robo : robos){
                System.out.printf("Turno do robô %s\n",robo.retornarCor(robo.getCor()));

                matriz[robo.getCoodY()][robo.getCoodX()] = robo.getCor();
                
                try {
                    Thread.sleep(1000); // pausa por 2 segundos
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for(i=0;i<4;i++){
                    for(j=0;j<4;j++){
                        System.out.print(matriz[i][j] + " ");       
                    }
                    System.out.println();
                } 
                      
                coodX = robo.getCoodX();
                coodY = robo.getCoodY();

                try{
                    matriz[robo.getCoodY()][robo.getCoodX()] = ".";
                    
                    robo.mover(random.nextInt(4) + 1);
                    matriz[robo.getCoodY()][robo.getCoodX()] = robo.getCor();
                    
                    matriz[robo1.getCoodY()][robo1.getCoodX()] = robo1.getCor();
                    matriz[robo2.getCoodY()][robo2.getCoodX()] = robo2.getCor();
                }
                catch(MovimentoInvalidoException | NumeroSentidoInvalidoException e){
                    System.out.println(e.getMessage());
                    matriz[coodY][coodX] = robo.getCor();   
                }

                try {
                    Thread.sleep(1000); 
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println();
                   
                for(i=0;i<4;i++){
                    for(j=0;j<4;j++){
                        System.out.print(matriz[i][j] + " ");       
                    }
                    System.out.println();
                } 
                  
                System.out.println("------------------------------------------");
                if(robo.alimentoEncontrado(posicaoYAli,posicaoXAli)){
                    roboVencedor = robo;
                    break;
                }
            }
        }while(roboVencedor == null);

        System.out.println("-----------------------------------------------------");

        System.out.println("ESTATÍSTICAS DA PARTIDA");

        System.out.printf("Robô %s",robo1.retornarCor(robo1.getCor()));
        System.out.print(" - Movimentos inválidos: " + robo1.getMovimentoInvalido());
        System.out.println(" - Movimentos válidos: " + robo1.getMovimentoValido());
        
        System.out.printf("Robô %s",robo2.retornarCor(robo2.getCor()));
        System.out.print(" - Movimentos inválidos: " + robo2.getMovimentoInvalido());
        System.out.println(" - Movimentos válidos: " + robo2.getMovimentoValido());
               
        System.out.println("Robô vencedor: " + roboVencedor.retornarCor(roboVencedor.getCor()));
    }

    private static void limparTela(){
        try {   
            if (System.getProperty("os.name").contains("Windows")) {    
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } 
                
        }catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}