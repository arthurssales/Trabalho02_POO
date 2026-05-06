import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainExtra {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Random random = new Random();
        Matriz matriz1 = new Matriz();
        matriz1.construirMatriz();

        MetodosImplement metodo = new MetodosImplement();

        Cor cor = new Cor();
        String corRobo;

        int posYAli = 0;
        int posXAli = 0;
        int posYObstaculo;
        int posXObstaculo;
        int coodX, coodY;

        RoboMemoria roboMemoria = null;
        RoboEstrategico roboEstrategico = null;

        Robo roboVencedor = null;
        ArrayList<Robo> robos = new ArrayList<>();
        String tipo;
        int roboExplodiu = 0;
        int antX, antY;

        ArrayList<Obstaculo> obstaculos = new ArrayList<>();
        int qntBomba = 0;
        int qntMaxBomba;
        int qntRocha = 0;
        
        while (true) {
            System.out.println("Indique a posição do alimento. A posição (0,0) é invalida");

            System.out.println("Coordenada no eixo y (de 0 a 3): ");
            posYAli = teclado.nextInt();
            teclado.nextLine();

            System.out.println("Coordenada no eixo x (de 0 a 3): ");
            posXAli = teclado.nextInt();
            teclado.nextLine();

            if (matriz1.posicionarAlimento(posYAli, posXAli)) {
                System.out.println("Alimento posicionado com sucesso!");
                break;
            } else
                System.out.println("\nCoordenada inválida!");
        }
        
        do {
            if (roboMemoria == null) {
                System.out.println("\n\nEscolha uma cor para o robô memoria");
                cor.mostrarCores();
                corRobo = teclado.nextLine();

                if (cor.selecionarCor(corRobo)) {
                    System.out.println("Cor selecionada com sucesso!");
                    roboMemoria  = new RoboMemoria(corRobo);
                } else
                    System.out.println("Cor indisponível!");
            }
            if(roboEstrategico == null){
                System.out.println("\n\nEscolha uma cor para o robô estrategico");
                cor.mostrarCores();
                corRobo = teclado.nextLine();

                if(cor.selecionarCor(corRobo)){
                    System.out.println("Cor selecionada com sucesso!");
                    roboEstrategico = new RoboEstrategico(corRobo, posXAli, posYAli);
                }
                else
                    System.out.println("Cor indisponível!");
            }
        }while(roboMemoria == null || roboEstrategico == null);
        matriz1.novaPosicaoRobo(roboMemoria.getCoodY(),roboMemoria.getCoodX(),roboMemoria.getCor());
        robos.add(roboMemoria);
        robos.add(roboEstrategico);
        int qntMaxRocha;


        System.out.println("\n\nPosicionando obstáculos");

        do {
            System.out.println("Escolha a quantidade de bombas (min = 1) (max = 3)");
            qntMaxBomba = teclado.nextInt();
            teclado.nextLine();
        } while (qntMaxBomba < 1 || qntMaxBomba > 3);

        do {
            System.out.println("Escolha a quantidade de rochas (min = 2) (max = 5)");
            qntMaxRocha = teclado.nextInt();
            teclado.nextLine();
        } while (qntMaxRocha < 2 || qntMaxRocha > 5);

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
        System.out.println("COMEÇANDO ROBÔ MEMORIA x ROBÔ ESTRATÉGICO");
        do{
            for(Robo robo : robos){
                if(robo.isExplodiu()){
                    continue;
                }

                matriz1.novaPosicaoRobo(robo.getCoodY(),robo.getCoodX(),robo.getCor());

                if(robo instanceof RoboEstrategico)
                    tipo = "estrategico";
                else
                    tipo = "memoria";

                metodo.delay();
                //teclado.nextLine();
                System.out.printf("Turno do robô %s \n", tipo);

                matriz1.imprimirMatriz();
                coodX = robo.getCoodX();
                coodY = robo.getCoodY();
                antX = robo.getCoodX();
                antY = robo.getCoodY();

                try{
                    matriz1.antigaPosicaoRobo(robo.getCoodY(),robo.getCoodX());
                    robo.mover(random.nextInt(4) + 1);

                    matriz1.novaPosicaoRobo(robo.getCoodY(), robo.getCoodX(), robo.getCor());

                    for(Obstaculo obs : obstaculos) {

                        if(obs instanceof Bomba && obs.bater(robo.getCoodY(),robo.getCoodX())){

                            System.out.printf("\nO robô %s explodiu\n", tipo);
                            robo.setExplodiu(true);
                            obs.setCobY(10);
                            obs.setCobX(10);
                            roboExplodiu++;
                            break;
                        }

                        else if (obs.bater(robo.getCoodY(), robo.getCoodX())) {
                            // registra posição bloqueada (ANTES de voltar o robô)
                            if (robo instanceof RoboMemoria) {
                                ((RoboMemoria) robo).getBarradas().add(robo.getCoodX() + "," + robo.getCoodY());
                            }
                            System.out.println();
                            matriz1.novaPosicaoRobo(robo.getCoodY(), robo.getCoodX(), robo.getCor());
                            matriz1.imprimirMatriz();

                            System.out.printf("\nO robô %s bateu na rocha\nVoltando para a posição anterior...\n", tipo);

                            matriz1.antigaPosicaoRocha(robo.getCoodY(), robo.getCoodX());
                            //metodo.delay();
                            teclado.nextLine();
                            robo.setCoodX(antX);
                            robo.setCoodY(antY);
                            matriz1.novaPosicaoRobo(antY, antX, robo.getCor());
                        }
                    }

                }catch(MovimentoInvalidoException | NumeroSentidoInvalidoException e){
                    System.out.println(e.getMessage());
                    matriz1.novaPosicaoRobo(coodY, coodX,robo.getCor());
                }

                System.out.println();
                metodo.delay();
                //teclado.nextLine();
                matriz1.imprimirMatriz();

                System.out.println("------------------------------------");

                if(robo.alimentoEncontrado(posYAli,posXAli)){
                    roboVencedor = robo;
                    break;
                }
            }
        }while(roboVencedor == null && roboExplodiu < 2);

        for(Robo robo : robos){
            robo.mostrarEstatisticas();
        }

        if(roboVencedor != null)
            System.out.printf("Robô vencedor: %s",roboVencedor.retornarCor(roboVencedor.getCor()));
        else
            System.out.println("Os dois robôs explodiram!");
    }
}