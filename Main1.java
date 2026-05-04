import java.util.Scanner;

public class Main1 {  
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        MetodosImplement metodo = new MetodosImplement();
        Matriz matriz1 = new Matriz();
        Cor cor = new Cor();
        
        int posXAli = 0, posYAli = 0;
        String corRobo;        
               
        Robo robo = null;
               
        //ignorar case sensitive
        while(robo == null){
            System.out.println("Escolha uma cor pro robo"); 
            cor.mostrarCores();
            System.out.println();
            corRobo = teclado.nextLine();
            
            if(cor.selecionarCor(corRobo))
                robo = new Robo(corRobo);

            else 
                System.out.println("\nCor indisponível!");
        }
        
        System.out.println();

        while(true){
            System.out.println("Indique a posição do alimento. A posição (0,0) é inválida");
            
            System.out.println("Coordenada do eixo y (de 0 a 3): "); 
            posYAli = teclado.nextInt();
            teclado.nextLine();        
            
            System.out.println("Coordenada do eixo x (de 0 a 3): ");
            posXAli = teclado.nextInt();
            teclado.nextLine();    
            
            if(matriz1.posicionarAlimento(posYAli,posXAli)){
                System.out.println("Alimento posicionado com sucesso!");
                break;   
            }
            else
                System.out.println("Coordenada inválida!");
        }
        
    
        matriz1.construirMatriz(posYAli, posXAli, robo.getCoodY(),robo.getCoodX(), robo.getCor());
    
        matriz1.imprimirMatriz();
        teclado.nextLine(); 
        
        metodo.limparTela(); 
            do{
                System.out.println("------------------------------------");
                
                matriz1.imprimirMatriz();
                                    
                System.out.println("Indique o sentido do robô:");
                System.out.println("1 - up\n2 - down\n3 - right\n4 - left");
                String entrada = teclado.nextLine();

                int coodX = robo.getCoodX();
                int coodY = robo.getCoodY();

                try {
                    matriz1.antigaPosicaoRobo(robo.getCoodY(), robo.getCoodX());
                    try {
                        int direcao = Integer.parseInt(entrada);
                        robo.mover(direcao); 
                    } catch (NumberFormatException e) {
                        robo.mover(entrada);
                    } catch (NumeroSentidoInvalidoException e) {
                        System.out.println(e.getMessage());
                    }                  
                    matriz1.novaPosicaoRobo(robo.getCoodY(), robo.getCoodX(), robo.getCor());
                    
                } catch (NomeSentidoInvalidoException | MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                    matriz1.novaPosicaoRobo(coodY,coodX,robo.getCor());
                    teclado.nextLine();
                }    
            }while(!robo.alimentoEncontrado(posYAli,posXAli)); 
                 
        System.out.println();

        matriz1.imprimirMatriz();

        System.out.println("\nAlimento encontrado!");
       
        System.out.println("------------------------------------");
        System.out.println("ESTATÍSTICAS");
        robo.mostrarEstatisticas();
    }
}