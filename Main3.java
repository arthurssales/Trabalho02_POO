import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main3 {    
    public static void main(String[] args) {
        ArrayList<Robo> robos = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        Random random = new Random();
        MetodosImplement metodo = new MetodosImplement();  

        Matriz matriz1 = new Matriz();
        Cor cor = new Cor();
        matriz1.construirMatriz();

        Robo roboNormal = null;
        RoboInteligente roboInteligente = null;
        int alimentoEncontrado = 0;

        int coodX, coodY;
        int posicaoYAli = 0;
        int posicaoXAli = 0;
        String corRobo;
        String tipo;
        
        while(true){
            System.out.println("Indique a posição do alimento. A posição (0,0) é invalida");
            
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
                System.out.println("\nCoordenda inválida!");   
        }

        do{   
            if(roboNormal == null){
                System.out.println("\n\nEscolha uma cor para o robô normal"); 
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
                System.out.println("\n\nEscolha uma cor para o robô inteligente"); 
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
        
        matriz1.novaPosicaoRobo(roboNormal.getCoodY(),roboNormal.getCoodX(),roboNormal.getCor());
        robos.add(roboNormal);
        robos.add(roboInteligente);

        
        metodo.limparTela();
        System.out.println("COMEÇANDO ROBÔ NORMAL x ROBÔ INTELIGENTE");
    
        do{
            for(Robo robo : robos){
                if(robo.isAlimentoEncontrado()){
                    continue;
                }

                matriz1.novaPosicaoRobo(robo.getCoodY(),robo.getCoodX(),robo.getCor());
                
                if(robo instanceof RoboInteligente)
                    tipo = "inteligente";
                else
                    tipo = "normal";

                metodo.delay();
                //teclado.nextLine();
                System.out.printf("Turno do robô %s\n", tipo);
                
                matriz1.imprimirMatriz();

                coodX = robo.getCoodX();
                coodY = robo.getCoodY();
                
                try {
                    matriz1.antigaPosicaoRobo(robo.getCoodY(),robo.getCoodX());  
                    robo.mover(random.nextInt(4) + 1);
                        
                    matriz1.novaPosicaoRobo(roboNormal.getCoodY(),roboNormal.getCoodX(),roboNormal.getCor());
                    matriz1.novaPosicaoRobo(roboInteligente.getCoodY(),roboInteligente.getCoodX(),roboInteligente.getCor());
                }
                catch (MovimentoInvalidoException | NumeroSentidoInvalidoException e) {
                    System.out.println(e.getMessage());
                    matriz1.novaPosicaoRobo(coodY, coodX,robo.getCor());
                }

                metodo.delay();
                //teclado.nextLine();
                System.out.println();
                matriz1.imprimirMatriz();

                System.out.println("------------------------------------");
                
                if(robo.alimentoEncontrado(posicaoYAli,posicaoXAli)){
                    System.out.printf("\nO robô %s encontrou o alimento!\n",tipo);
                    alimentoEncontrado++;    
                }
            }      
        }while (alimentoEncontrado <2 );
    
        System.out.println("------------------------------------");
        System.out.println("ESTATISTICAS");
        
        System.out.printf("\nRobô normal (%s) - Movimentos totais: %d",roboNormal.retornarCor(roboNormal.getCor()),
        (roboNormal.getMovimentoInvalido() + roboNormal.getMovimentoValido()));

        System.out.printf("\nRobô inteligente (%s) - Movimentos totais: %d",roboInteligente.retornarCor(roboInteligente.getCor()),
        roboInteligente.getMovimentoValido());
    }
}