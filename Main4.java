import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Main4 {
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);        
        Random random = new Random();

        Matriz matriz1 = new Matriz();
        Cor cor = new Cor();


        String corRobo;
        ArrayList<String> coresDisponiveis = new ArrayList<>();
        coresDisponiveis.add("azul");
        coresDisponiveis.add("vermelho");
        coresDisponiveis.add("preto");
        coresDisponiveis.add("branco");

        int posicaoYAli = 0;
        int posicaoXAli = 0;
        int posYObstaculo;
        int posXObstaculo;
        int coodX, coodY;
        
        boolean posicaoValida = false;
        String[][] matriz = new String[4][4];

        Robo roboNormal = null;
        RoboInteligente roboInteligente = null;
        Robo roboVencedor = null;
        ArrayList<Robo> robos = new ArrayList<>();
        String tipo;
        ArrayList<Obstaculo> obstaculos = new ArrayList<>();
    
        int i,j;
        
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                matriz[i][j] = ".";
            }
        }
        
        do{   
            if(roboNormal == null){
                System.out.println("Escolha uma cor para o robô normal");
                cor.mostrarCores();
                corRobo = teclado.nextLine();    
                
                if(cor.verificarCor(corRobo)){
                    System.out.println("Cor selecionada com sucesso!");
                    roboNormal = new Robo(corRobo);
                }
                else
                    System.out.println("Cor indisponível!");
            }
                
            if(roboInteligente == null){
                System.out.println("Escolha uma cor para o robô inteligente"); 
                cor.mostrarCores();
                corRobo = teclado.nextLine();    
                
                if(cor.verificarCor(corRobo)){

                    if(cor.selecionarCor(corRobo)){
                        System.out.println("Cor selecionada com sucesso!");
                        roboInteligente = new RoboInteligente(corRobo);
                    }
                }
                else
                    System.out.println("Cor indisponível!");
            }
        }while(roboNormal == null || roboInteligente == null);
        robos.add(roboNormal);
        robos.add(roboInteligente);
        
        matriz1.novaPosicaoRobo(roboNormal.getCoodY(),roboNormal.getCoodX(),roboNormal.getCor());

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
      
        int qntBomba = 0;
        int qntMaxBomba;
        int qntMaxRocha;
        int qntRocha = 0;
        
        System.out.println("\n\nPosicionando obstáculos");
        
        do { 
            System.out.println("\nEscolha a quantidade de bombas (min = 1) (max = 3)");
            qntMaxBomba = teclado.nextInt();
            teclado.nextLine();
        } while (qntMaxBomba < 1 || qntMaxBomba > 3);

        do { 
            System.out.println("\nEscolha a quantidade de rochas (min = 2) (max = 5)");
            qntMaxRocha = teclado.nextInt();
            teclado.nextLine();
        } while (qntMaxRocha < 2 || qntMaxRocha > 5);
        
        matriz1.construirMatriz(posicaoYAli, posicaoXAli, roboNormal.getCoodY(),roboNormal.getCoodX(),roboNormal.getCor());

        while(qntBomba < qntMaxBomba){
            System.out.println("\nPosicione a bomba.");
            matriz1.imprimirMatriz();

            System.out.println("Coordenada no eixo y (de 0 a 3)");
            posYObstaculo = teclado.nextInt();
            teclado.nextLine();

            System.out.println("Coordenada no eixo x (de 0 a 3)");
            posXObstaculo = teclado.nextInt();
            teclado.nextLine();

            if(matriz1.posicionarObstaculo(posYObstaculo, posXObstaculo,"Bomba")){
                System.out.println("Bomba posicionada com sucesso!");
                Obstaculo bomba = new Bomba();
                bomba.setCobY(posYObstaculo);
                bomba.setCobX(posXObstaculo);
                qntBomba++;
            }
            else
                System.out.println("Coordenada inválida!");
        }

        while(qntRocha < qntMaxRocha){
            System.out.println("\nPosicione a rocha.");
            matriz1.imprimirMatriz();

            System.out.println("Coordenada no eixo y (de 0 a 3)");
            posYObstaculo = teclado.nextInt();
            teclado.nextLine();
            
            System.out.println("Coordenada no eixo x (de 0 a 3)");
            posXObstaculo = teclado.nextInt();
            teclado.nextLine();
            
            if(matriz1.posicionarObstaculo(posYObstaculo, posXObstaculo,"Rocha")){
                System.out.println("Rocha posicionada com sucesso!");
                Obstaculo rocha = new Rocha();
                rocha.setCobY(posYObstaculo);
                rocha.setCobX(posXObstaculo);
                qntRocha++;
            }

            else
                System.out.println("Coordenada inválida!");
        }
        matriz1.imprimirMatriz();
        teclado.nextLine();
 
        limparTela();
        System.out.println();
               
        
        Iterator<Robo> roboExplodiu = robos.iterator();
        Iterator<Obstaculo> bombaExplodiu = obstaculos.iterator();
        do{
            for(Robo robo : robos){
                matriz1.novaPosicaoRobo(robo.getCoodY(),robo.getCoodX(),robo.getCor());

                if(robo instanceof RoboInteligente)
                    tipo = "inteligente";
                else
                    tipo = "normal";

                try {
                    Thread.sleep(2000); 
                } 
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                
                System.out.printf("Turno do robô %s \n", robo.retornarCor(robo.getCor()));

                matriz1.imprimirMatriz();

                coodX = robo.getCoodX();
                coodY = robo.getCoodY();

                try{
                    matriz1.antigaPosicaoRobo(robo.getCoodY(),robo.getCoodX());
                    robo.mover(random.nextInt(4) + 1);
                   
                    /*for(Obstaculo obs : obstaculos){
                        if(obs instanceof Bomba){
                            if(obs.bater(robo.getCoodY(),robo.getCoodX())){
                                System.out.printf("O robô %s explodiu",tipo);   
                                roboExplodiu.remove(); //não pode remover um elemento do 
                                bombaExplodiu.remove();
                            }*/
                        /*else{
                            if(obs.bater(robo.getCoodY(),robo.getCoodX())){
                                System.out.printf("O robô %s bateu na rocha",tipo);
                            
                            }
                        }*/    
                        }catch(MovimentoInvalidoException | NumeroSentidoInvalidoException e){
                            System.out.println(e.getMessage());
                            matriz1.novaPosicaoRobo(coodY, coodX,robo.getCor());
                        }

                try {
                    Thread.sleep(2000);
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println();

                matriz1.imprimirMatriz();

                System.out.println("------------------------------------");

                if(robo.alimentoEncontrado(posicaoYAli,posicaoXAli)){
                    roboVencedor = robo;
                    break;
                }
            }      
        }while(roboVencedor == null && !robos.isEmpty());
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
