import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main1 {
     
    public static void main(String[] args) {
        //1. adicionar sleeper
        Scanner teclado = new Scanner(System.in);
        boolean posicaoValida = false;
        
        int posXAli = 0, posYAli = 0;
        String corRobo;        
        int i,j;
        
        ArrayList<String> coresDisponiveis = new ArrayList<>();
        coresDisponiveis.add("azul");
        coresDisponiveis.add("vermelho");
        coresDisponiveis.add("preto");
        coresDisponiveis.add("branco");

        String[][] matriz = new String[4][4];
        Robo robo = null;
        
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                matriz[i][j]= ".";
            }
        }
        
        //ignorar case sensitive
        while(robo == null){
            System.out.println("Escolha uma cor pro robo"); 
            System.out.println("azul - vermelho - preto - branco");
            corRobo = teclado.nextLine();
            
            if(coresDisponiveis.contains(corRobo))      
                robo = new Robo(corRobo);   

            else
                System.out.println("Cor inválida!");       
        }

        while(!posicaoValida){
            System.out.println("Indique a posição do alimento. A posição (0,0) é inválida");
            
            System.out.println("Coordenada do eixo y (de 0 a 3): "); 
            posYAli = teclado.nextInt();
            teclado.nextLine();        
            
            System.out.println("Coordenada do eixo x (de 0 a 3): ");
            posXAli = teclado.nextInt();
            teclado.nextLine();    
            
            if(posYAli == 0 && posXAli == 0)
                System.out.println("Coordenada inválida!");
            
            else{
                try{    
                    matriz [posYAli][posXAli] = "^";
                    posicaoValida = true;    
                }
                catch(Exception e){
                    System.out.println("Coordenada inválida!");
                }                   
            }
        }
         
        matriz[robo.getCoodY()][robo.getCoodX()] = robo.getCor();
        limparTela();       
            do{
                System.out.println("------------------------------------");
                
                for(i=0;i<4;i++){
                    for(j=0;j<4;j++){    
                          System.out.print(matriz[i][j] + " ");
                    }
                    System.out.println();
                }
                                    
                System.out.println("Indique o sentido do robô:");
                System.out.println("1 - up\n2 - down\n3 - right\n4 - left");
                String entrada = teclado.nextLine();

                int coodX = robo.getCoodX();
                int coodY = robo.getCoodY();

                try {
                    matriz[robo.getCoodY()][robo.getCoodX()] = ".";

                    try {
                        int direcao = Integer.parseInt(entrada);
                        robo.mover(direcao); 
                    } catch (NumberFormatException e) {
                        robo.mover(entrada);
                    } catch (NumeroSentidoInvalidoException e) {
                        System.out.println(e.getMessage());
                    }

                    matriz[robo.getCoodY()][robo.getCoodX()] = robo.getCor();

                } catch (NomeSentidoInvalidoException | MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                    matriz[coodY][coodX] = robo.getCor();
                    teclado.nextLine();
                }    
            }while(!robo.alimentoEncontrado(posYAli,posXAli)); 
                 
        System.out.println();

        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                System.out.print(matriz[i][j] + " ");
                
            }
            System.out.println();
        }

        System.out.println("\nAlimento encontrado!");
       
        System.out.println("------------------------------------");
        System.out.println("ESTATISTICAS");
        System.out.printf("Movimentos válidos: %d - Movimentos inválidos: %d",robo.getMovimentoValido(),robo.getMovimentoInvalido());
    }
    
    private static void limparTela(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {    
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}