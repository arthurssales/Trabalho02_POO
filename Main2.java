import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main2 { 
    public static void main(String[] args) {
        ArrayList<Robo> robos = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        Random random = new Random();
        
        Matriz matriz1 = new Matriz();
        Cor cor = new Cor();
        Robo roboVencedor = null;
        Robo robo1 = null;
        Robo robo2 = null;
        
        int coodX, coodY;
        int posicaoXAli = 0, posicaoYAli = 0;
        String corRobo;        
            
        do{   
            if(robo1 == null){
                System.out.println("\nEscolha uma cor pro robo 1"); 
                cor.mostrarCores();
                corRobo = teclado.nextLine();    
                if(cor.verificarCor(corRobo)){
                    
                    if(cor.selecionarCor(corRobo)){
                        System.out.println("Cor selecionada com sucesso!");
                        robo1 = new Robo(corRobo);    
                    } 
                   
                }
                else
                    System.out.println("Cor indisponível!");
            }
                
            if(robo2 == null){
                System.out.println("\nEscolha uma cor pro robo 2"); 
                cor.mostrarCores();
                corRobo = teclado.nextLine();    
                
                if(cor.verificarCor(corRobo)){
                    
                    if(cor.selecionarCor(corRobo)){
                        System.out.println("Cor selecionada com sucesso!");
                        robo2 = new Robo(corRobo);
                    }
                  
                }
                else
                    System.out.println("Cor indisponível!");
            }
        }while(robo1 == null || robo2 == null);
        
        robos.add(robo1);
        robos.add(robo2);
       
        while(true){
            System.out.println("\n\nIndique a posição do alimento. A posição (0,0) é invalida");
            
            System.out.println("Coordenada no eixo y (de 0 a 3): "); 
            posicaoYAli = teclado.nextInt();
            teclado.nextLine();        
            
            System.out.println("Coordenada no eixo x (de 0 a 3): ");
            posicaoXAli = teclado.nextInt();
            teclado.nextLine();    
                     
            if(matriz1.posicionarAlimento(posicaoYAli, posicaoXAli)){
                System.out.println("\nAlimento posicionado com sucesso!");
                break;
            }
            else 
                System.out.println("\nCoordenada inválida!");
        }
        
        limparTela();
        matriz1.construirMatriz(posicaoYAli, posicaoXAli,robo1.getCoodY(),robo1.getCoodX(),robo1.getCor());
        System.out.println("COMEÇANDO RANDOM1 x RANDOM2");

        do{
            for(Robo robo : robos){
                System.out.printf("Turno do robô %s\n",robo.retornarCor(robo.getCor()));
            
                matriz1.novaPosicaoRobo(robo.getCoodY(),robo.getCoodX(),robo.getCor());
                
                try {
                    Thread.sleep(1000); 
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                matriz1.imprimirMatriz();
                     
                coodX = robo.getCoodX();
                coodY = robo.getCoodY();

                try{
                    matriz1.antigaPosicaoRobo(robo.getCoodY(),robo.getCoodX());
                    robo.mover(random.nextInt(4) + 1);

                    matriz1.novaPosicaoRobo(robo1.getCoodY(),robo1.getCoodX(),robo1.getCor());
                    matriz1.novaPosicaoRobo(robo2.getCoodY(),robo2.getCoodX(),robo2.getCor());

                }
                catch(MovimentoInvalidoException | NumeroSentidoInvalidoException e){
                    System.out.println(e.getMessage());
                    matriz1.novaPosicaoRobo(coodY, coodX, robo.getCor());
                }

                try {
                    Thread.sleep(1000); 
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println();
                
                matriz1.imprimirMatriz();

                System.out.println("------------------------------------------");
                if(robo.alimentoEncontrado(posicaoYAli,posicaoXAli)){
                    roboVencedor = robo;
                    break;
                }
            }
        }while(roboVencedor == null);

        System.out.println("-----------------------------------------------------");

        for(Robo robo : robos){
            robo.mostrarEstatisticas(); 
        }
        
        System.out.println("\nRobô vencedor: " + roboVencedor.retornarCor(roboVencedor.getCor()));
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