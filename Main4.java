import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Main4 {
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);        
        Random random = new Random();

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
                System.out.println("Escolha uma cor para o robô normal"); //
                for(Object cor : coresDisponiveis){//
                    System.out.printf("%s - ",cor);//
                }    //
                corRobo = teclado.nextLine();    
                
                if(coresDisponiveis.contains(corRobo)){//
                    coresDisponiveis.remove(corRobo);//
                    roboNormal = new Robo(corRobo);
                }                    //
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
        matriz[0][0] = roboNormal.getCor();
        
        robos.add(roboNormal);
        robos.add(roboInteligente);

        while(!posicaoValida){
            System.out.println("\n\nPosicione o alimento. A posição (0,0) é invalida");
            
            System.out.println("Coordenada no eixo y (de 0 a 3): "); 
            posicaoYAli = teclado.nextInt();
            teclado.nextLine();        
            
            System.out.println("Coordenada no eixo x (de 0 a 3): ");
            posicaoXAli = teclado.nextInt();
            teclado.nextLine();    
            
            if(posicaoYAli == 0 && posicaoXAli == 0)
                System.out.println("Posição ocupada!");
            
            else{
                try{    
                    matriz[posicaoYAli][posicaoXAli] = "^";
                    posicaoValida = true;    
                }
                catch(Exception e){
                    System.out.println("Coordenada inválida!");
                }                   
            }
        }
              
        System.out.println("\nPosicionando obstáculos");
        System.out.println("Quantidade de bombas: 3");
        System.out.println("Quantidade de rochas: 4");
            
        int opcao;
        int qntBomba = 0;
        int qntRocha = 0;
        
        do{      
            System.out.println("Selecione o tipo do obstáculo");
            
            System.out.println("1 - Bomba\n2 - Rocha");
            opcao = teclado.nextInt();
            teclado.nextLine();
            switch(opcao){
                case 1: 
                if(qntBomba == 3)
                    System.out.println("Limite de bombas atingido!");
                
                else{

                    System.out.println("Posicione a bomba. A posição (0,0) é inválida");
                    System.out.println("Coordenada no eixo y (de 0 a 3)");
                    posYObstaculo = teclado.nextInt();
                    teclado.nextLine();
                    
                    System.out.println("Coordenada no eixo x (de 0 a 3)");
                    posXObstaculo = teclado.nextInt();
                    teclado.nextLine();
                    
                    try{
                        
                        if(!matriz[posYObstaculo][posXObstaculo].equals("."))
                            System.out.println("Posição ocupada!");

                        else{
                            matriz[posYObstaculo][posXObstaculo] = "*";
                            Obstaculo bomba = new Bomba();
                            bomba.setCobY(posYObstaculo);
                            bomba.setCobX(posXObstaculo);
                            obstaculos.add(bomba);
                            
                            for(i=0;i<4;i++){
                                for(j=0;j<4;j++){
                                    System.out.print(matriz[i][j] + " ");
                                }
                                System.out.println();
                            }

                            qntBomba++;
                        }
                    }
                    catch(Exception e){
                        System.out.println("Coordenada inválida");
                    }
                    break;
                }
                    
                case 2:
                    if(qntRocha == 4)
                        System.out.println("Limite de rochas atingido!");
                    
                    else{

                        System.out.println("Posicione a rocha. A posição (0,0) é inválida");
                        System.out.println("Coordenada no eixo y (de 0 a 3)");
                        posYObstaculo = teclado.nextInt();
                        teclado.nextLine();
                        
                        System.out.println("Coordenada no eixo x (de 0 a 3)");
                        posXObstaculo = teclado.nextInt();
                        teclado.nextLine();
                        
                        try{

                            if(!matriz[posYObstaculo][posXObstaculo].equals("."))
                                System.out.println("Posição ocupada!");
                        
                        else{
                            matriz[posYObstaculo][posXObstaculo] = "#";
                            Obstaculo rocha = new Rocha();
                            rocha.setCobY(posYObstaculo);
                            rocha.setCobX(posXObstaculo);
                            obstaculos.add(rocha);
                            
                            for(i=0;i<4;i++){
                                for(j=0;j<4;j++){
                                    System.out.print(matriz[i][j] + " ");
                                }
                                System.out.println();
                            }
                            
                            qntRocha++;
                        }
                    }
                    catch(Exception e){
                        System.out.println("Coordenada inválida");
                    }
                    break;
                }
            }
        }while(qntBomba < 3 || qntRocha < 4);
                     
        limparTela();
        System.out.println();
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
       
        Iterator<Robo> roboExplodiu = robos.iterator();
        Iterator<Obstaculo> bombaExplodiu = obstaculos.iterator();
        do{
            for(Robo robo : robos){
                
                if(robo instanceof RoboInteligente)
                    tipo = "inteligente";
                else
                    tipo = "normal";

                System.out.printf("Turno do robô %s \n", robo.retornarCor(robo.getCor()));

                matriz[robo.getCoodY()][robo.getCoodX()] = robo.getCor();

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
                   
                    for(Obstaculo obs : obstaculos){
                        if(obs instanceof Bomba){
                            if(obs.bater(robo.getCoodY(),robo.getCoodX())){
                                System.out.printf("O robô %s explodiu",tipo);   
                                roboExplodiu.remove(); //não pode remover um elemento do 
                                bombaExplodiu.remove();
                            }
                        /*else{
                            if(obs.bater(robo.getCoodY(),robo.getCoodX())){
                                System.out.printf("O robô %s bateu na rocha",tipo);
                            
                            }
                        }*/    
                        }

                    }


                    matriz[robo.getCoodY()][robo.getCoodX()] = robo.getCor();

                    matriz[roboInteligente.getCoodY()][roboInteligente.getCoodX()] = roboInteligente.getCor();
                    matriz[roboNormal.getCoodY()][roboNormal.getCoodX()] = roboNormal.getCor();

                    teclado.nextLine();
                }
                catch(MovimentoInvalidoException | NumeroSentidoInvalidoException e){
                    System.out.println(e.getMessage());
                    matriz[coodY][coodX] = robo.getCor();
                }

                for(i=0;i<4;i++){
                    for(j=0;j<4;j++){
                        System.out.print(matriz[i][j] + " ");
                    }
                    System.out.println();
                }

                if(robo.alimentoEncontrado(posicaoYAli,posicaoXAli)){
                    roboVencedor = robo;
                    break;
                }
            }   
        }while(roboVencedor == null && !robos.isEmpty());
        
        /*if(bomba.bater(robY,RobX){
                robos.remove(robo);
        }
                
        
            /*if(rocha.bater(robY,RobX){

                sysyout("Voltando para a posição anterior...");
            }
   
            //while(!robos.isEmpty() || roboVencedor == null)*/
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
