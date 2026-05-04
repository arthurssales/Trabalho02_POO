import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Main4{

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Random random = new Random();
        Matriz matriz1 = new Matriz();
        MetodosImplement metodo = new MetodosImplement();

        Cor cor = new Cor();
        String corRobo;

        int posYAli = 0;
        int posXAli = 0;
        int posYObstaculo;
        int posXObstaculo;
        int coodX, coodY;

        Robo roboNormal = null;
        RoboInteligente roboInteligente = null;
        Robo roboVencedor = null;
        ArrayList<Robo> robos = new ArrayList<>();
        String tipo;
        ArrayList<Obstaculo> obstaculos = new ArrayList<>();
        int qntBomba = 0;
        int qntMaxBomba;
        int qntRocha = 0;
        int qntMaxRocha;

        do{
            if(roboNormal == null){
                System.out.println("Escolha uma cor para o robô normal");
                cor.mostrarCores();
                corRobo = teclado.nextLine();

                if(cor.selecionarCor(corRobo)){
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

                if(cor.selecionarCor(corRobo)){
                    System.out.println("Cor selecionada com sucesso!");
                    roboInteligente = new RoboInteligente(corRobo);
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
            posYAli = teclado.nextInt();
            teclado.nextLine();
            System.out.println("Coordenada no eixo x (de 0 a 3): ");
            posXAli = teclado.nextInt();
            teclado.nextLine();
            if(matriz1.posicionarAlimento(posYAli, posXAli)){
                System.out.println("\nAlimento posicionado com sucesso!");
                break;
            }
            else
                System.out.println("\nCoordenada inválida!");
        }


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

        matriz1.construirMatriz(posYAli, posXAli, roboNormal.getCoodY(),roboNormal.getCoodX(),roboNormal.getCor());

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
                obstaculos.add(bomba);
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
                obstaculos.add(rocha);
                rocha.setCobY(posYObstaculo);
                rocha.setCobX(posXObstaculo);
                qntRocha++;
            }

            else
                System.out.println("Coordenada inválida!");
        }
        matriz1.imprimirMatriz();
        teclado.nextLine();

        metodo.limparTela();
        System.out.println();
        int antX,antY;
        int roboExplodiu = 0;

        do{
            for(Robo robo : robos){
                matriz1.novaPosicaoRobo(robo.getCoodY(),robo.getCoodX(),robo.getCor());

                if(robo instanceof RoboInteligente)
                    tipo = "inteligente";
                else
                    tipo = "normal";

                metodo.delay();

                System.out.printf("Turno do robô %s \n", tipo);

                matriz1.imprimirMatriz();
                coodX = robo.getCoodX();
                coodY = robo.getCoodY();
                antX = robo.getCoodX();
                antY = robo.getCoodY();
                try{
                    matriz1.antigaPosicaoRobo(robo.getCoodY(),robo.getCoodX());
                    robo.mover(random.nextInt(4) + 1);

                    Iterator<Robo> robo0 = robos.iterator();
                   
                    while(robo0.hasNext()) {
                        Robo rob = robo0.next();
                        Iterator<Obstaculo> obs0 = obstaculos.iterator();
                        while(obs0.hasNext()) {
                            Obstaculo obs = obs0.next();
                            if (obs instanceof Bomba){
                                if (obs.bater(robo.getCoodY(), robo.getCoodX())){
                                    System.out.printf("\nO robô %s explodiu\n", tipo);
                                    robo0.remove();
                                    obs0.remove();
                                    roboExplodiu++;
                                    break;
                                }
                            }
                            else{
                                if(obs.bater(robo.getCoodY(),robo.getCoodX())){
                                    matriz1.novaPosicaoRobo(robo.getCoodY(),robo.getCoodX(),robo.getCor());
                                    matriz1.imprimirMatriz();
                                    System.out.printf("\nO robô %s bateu na rocha\nVoltando para a posição anterior...\n",tipo);
                                    
                                    matriz1.antigaPosicaoRocha(robo.getCoodY(),robo.getCoodX());
                                    metodo.delay();
                                    robo.setCoodX(antX);
                                    robo.setCoodY(antY);
                                    matriz1.novaPosicaoRobo(antY,antX,robo.getCor());
                                }
                            } 
                        }
                    }
                }catch(MovimentoInvalidoException | NumeroSentidoInvalidoException e){
                    System.out.println(e.getMessage());
                    matriz1.novaPosicaoRobo(coodY, coodX,robo.getCor());
                }

                matriz1.novaPosicaoRobo(roboNormal.getCoodY(),roboNormal.getCoodX(),roboNormal.getCor());
                matriz1.novaPosicaoRobo(roboInteligente.getCoodY(),roboInteligente.getCoodX(),roboInteligente.getCor());
                System.out.println();
                metodo.delay();
                matriz1.imprimirMatriz();

                System.out.println("------------------------------------");

                if(robo.alimentoEncontrado(posYAli,posXAli)){
                    roboVencedor = robo;
                    break;
                }
            }
        }while(roboVencedor == null && !robos.isEmpty());
    }
}
