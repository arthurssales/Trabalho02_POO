import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main3 {    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Random random = new Random();
          
        String corRobo;
        ArrayList<String> coresDisponiveis = new ArrayList<>();
        coresDisponiveis.add("azul");
        coresDisponiveis.add("vermelho");
        coresDisponiveis.add("preto");
        coresDisponiveis.add("branco");

        String tipo = null;
        int posicaoYAli = 0;
        int posicaoXAli = 0;
        int coodX, coodY;
        
        String[][] matriz = new String[4][4];
        
        boolean posicaoValida = false;
        
        Robo roboNormal = null;
        RoboInteligente roboInteligente = null;
        Robo roboVencedor = null;
        ArrayList<Robo> robos = new ArrayList<>();
        
        int i,j;

        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                matriz[i][j] = ".";
            }
        }

        do{   
            if(roboNormal == null){
                System.out.println("Escolha uma cor para o robô normal"); 
                for(Object cor : coresDisponiveis){
                    System.out.printf("%s - ",cor);
                }    
                corRobo = teclado.nextLine();    
                
                if(coresDisponiveis.contains(corRobo)){
                    roboNormal = new Robo(corRobo);
                    coresDisponiveis.remove(corRobo);
                }    
            }
                
            if(roboInteligente == null){
                System.out.println("Escolha uma cor para o robô inteligente"); 
                for(Object cor : coresDisponiveis){
                    System.out.printf("%s - ",cor);
                }    
                corRobo = teclado.nextLine();    
                
                if(coresDisponiveis.contains(corRobo)){
                    roboInteligente = new RoboInteligente(corRobo);
                    coresDisponiveis.remove(corRobo);
                }
            }
            
        }while(roboNormal == null || roboInteligente == null);
        
        robos.add(roboNormal);
        robos.add(roboInteligente);


        while(!posicaoValida || (posicaoYAli == 0 && posicaoXAli == 0)){
            System.out.println("Indique a posição do alimento. A posição (0,0) é invalida");
            
            System.out.println("Coordenada no eixo y (de 0 a 3): "); 
            posicaoYAli = teclado.nextInt();
            teclado.nextLine();        
            
            System.out.println("Coordenada no eixo x (de 0 a 3): ");
            posicaoXAli = teclado.nextInt();
            teclado.nextLine();    
            
            try{    
                matriz[posicaoYAli][posicaoXAli] = "^";
                posicaoValida = true;    
            }
            catch(Exception e){
                System.out.println("Coordenada inválida!");
            }                   
        }
        
        limparTela();
        System.out.println("COMEÇANDO ROBÔ NORMAL x ROBÔ INTELIGENTE");
    
        do{
            for(Robo robo : robos){
                
                if(robo instanceof RoboInteligente)
                    tipo = "inteligente";
                else
                    tipo = "inteligente";

                try {
                    Thread.sleep(2000); 
                } 
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                System.out.printf("Turno do robô %s\n", tipo);
                
                matriz[robo.getCoodY()][robo.getCoodX()] = robo.getCor();
                
                for(i=0;i<4;i++){
                    for(j=0;j<4;j++){
                        System.out.print(matriz[i][j] + " ");       
                    }
                    System.out.println();
                } 

                coodX = robo.getCoodX();
                coodY = robo.getCoodY();
                
                try {
                    matriz[robo.getCoodY()][robo.getCoodX()] = ".";
                        
                    robo.mover(random.nextInt(4) + 1);
                        
                    matriz[robo.getCoodY()][robo.getCoodX()] = robo.getCor();
                        
                    matriz[roboInteligente.getCoodY()][roboInteligente.getCoodX()] = roboInteligente.getCor();
                    matriz[roboNormal.getCoodY()][roboNormal.getCoodX()] = roboNormal.getCor();                     
                }
                catch (MovimentoInvalidoException | NumeroSentidoInvalidoException e) {
                    System.out.println(e.getMessage());
                    matriz[coodY][coodX] = robo.getCor();
                }

                try {
                    Thread.sleep(2000);
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
             
                System.out.println("------------------------------------");
                
                if(robo.alimentoEncontrado(posicaoYAli,posicaoXAli)){
                    roboVencedor = robo;
                    break;
                }
            }
        
        }while (roboVencedor == null);
    
        System.out.println("------------------------------------");
        System.out.println("ESTATISTICAS");
        System.out.printf("Robô vencedor: %s",roboVencedor.retornarCor(roboVencedor.getCor()));

        System.out.printf("\nRobô normal (%s) - Movimentos totais: %d",roboNormal.retornarCor(roboNormal.getCor()),
        (roboNormal.getMovimentoInvalido() + roboNormal.getMovimentoValido()));

        System.out.printf("\nRobô inteligente (%s) - Movimentos totais: %d",roboInteligente.retornarCor(roboInteligente.getCor()),
        roboInteligente.getMovimentoValido());
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
