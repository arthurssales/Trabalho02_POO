import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class MainDoisRobos { 
    public static void main(String[] args) {
        

        /*Metodos para adicionar
        limpar teclado
        imprimir tabuleiro
        mostrar o passo a passo do metodo mover*/
        
        Scanner teclado = new Scanner(System.in);
        boolean posicaoValida = false;
        
        int coodX, coodY;
        int posicaoXAli = 0, posicaoYAli = 0;
        String corRobo;        
        int i,j;
        int quantidadeRodadas = 0;
              
        Random random = new Random();
        
        /*ArrayList<RoboAleatorio> robos = new ArrayList<>();
        robos.add(robo1);
        robos.add(robo2);*/
        RoboAleatorio roboVencedor = null;
        String[][] matriz = new String[4][4];
        
        //construindo a matriz
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                matriz[i][j]= "0";
            }
        }
        
        //cores disponiveisp para evitar cores repetidas
        ArrayList<String> coresDisponiveis = new ArrayList<>();
        coresDisponiveis.add("azul");
        coresDisponiveis.add("vermelho");
        coresDisponiveis.add("preto");
        coresDisponiveis.add("branco");

        RoboAleatorio robo1 = null;
        RoboAleatorio robo2 = null;

        do{   
            if(robo1 == null){
                System.out.println("Escolha uma cor pro robo 1"); 
                for(Object cor : coresDisponiveis){
                    System.out.printf("%s - ",cor);
                }    
                corRobo = teclado.nextLine();    
                
                try{     
                    robo1 = new RoboAleatorio(corRobo);
                    coresDisponiveis.remove(corRobo);
                    
                }catch(CorInvalidaException e){ 
                    System.out.println(e.getMessage());
                }    
            }
                
            if(robo2 == null){
                System.out.println("Escolha uma cor pro robo 2"); 
                for(Object cor : coresDisponiveis){
                    System.out.printf("%s - ",cor);
                }    
                corRobo = teclado.nextLine();    
                
                try{     
                    robo2 = new RoboAleatorio(corRobo);
                    coresDisponiveis.remove(corRobo);
                    
                }catch(CorInvalidaException e){ 
                    System.out.println(e.getMessage());
                }    
            }
        }while(robo1 == null || robo2 == null);
    

        while(!posicaoValida || (posicaoXAli == 0 && posicaoYAli == 0)){
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
         

        //1.fazer duas matrizes diferentes em que o alimento estará na mesma posição (beta)
        //2.sobrepor um robo sobre o outro quando ambos ocuparem a mesma posição (chad) 
        
        /////////////////////////////////////////////////////////////////////        
        //mudar o codigo para um for each
        do{
            //criar um metodo que para deixar o codigo mais limpo
            try {
                
                if (System.getProperty("os.name").contains("Windows")) {
                    
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } 
                
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
            ///////////////////////////////////////////////////////////////////////
            System.out.printf("robo %s rodada %d\n",robo1.getCor() ,(quantidadeRodadas +1));
            
            matriz[robo1.getCoodY()][robo1.getCoodX()] = robo1.getCor();
            //criar um metodo para evitar repetição de impressão de matriz e limpeza do terminal
            for(i=0;i<4;i++){
                for(j=0;j<4;j++){
                    if(matriz[i][j].equals(robo1.getCor()))
                        System.out.print(matriz[i][j] + " ");
                    
                    else if(matriz[i][j].equals(robo2.getCor()))
                        System.out.print(matriz[i][j] + " ");
                    
                    else
                        System.out.print("?" + " ");
                }
                System.out.println();
            }
            
            coodX = robo1.getCoodX();
            coodY = robo1.getCoodY();
            try{    
                matriz[robo1.getCoodY()][robo1.getCoodX()] = "0";
                robo1.mover(random.nextInt(4) + 1);
                //incluir função que realiza movimentos em uma determinada frequencia
                teclado.nextLine(); 
                matriz[robo1.getCoodY()][robo1.getCoodX()] = robo1.getCor();
                
            }
            catch(MovimentoInvalidoException e){
                System.out.println(e.getMessage());
                matriz[coodY][coodX] = robo1.getCor();
                teclado.nextLine();
            
            }

            if(robo1.alimentoEncontrado(posicaoYAli,posicaoXAli)){
                roboVencedor = robo1;
                quantidadeRodadas++;
                break;
            }

            System.out.printf("robo %s rodada %d \n",robo2.getCor(),(quantidadeRodadas +1));
            matriz[robo2.getCoodY()][robo2.getCoodX()] = robo2.getCor();
            //criar metodo para evitar repetição de impressao de matriz e limpeza do terminal
            for(i=0;i<4;i++){
                for(j=0;j<4;j++){
                    if(matriz[i][j].equals(robo1.getCor()))
                        System.out.print(matriz[i][j] + " ");

                    else if(matriz[i][j].equals(robo2.getCor()))
                        System.out.print(matriz[i][j] + " ");
                    
                    else
                        System.out.print("?" + " ");
                }
                System.out.println();
            }
            
            coodX = robo2.getCoodX();
            coodY = robo2.getCoodY();
            try{
                matriz[robo2.getCoodY()][robo2.getCoodX()] = "0";
                robo2.mover(random.nextInt(4) + 1);
                teclado.nextLine();
                matriz[robo2.getCoodY()][robo2.getCoodX()] = robo2.getCor();
            
            }catch(MovimentoInvalidoException e){
                System.out.println(e.getMessage());
                matriz[coodY][coodX] = robo2.getCor();
                teclado.nextLine();
            }
            
            if(robo2.alimentoEncontrado(posicaoYAli,posicaoXAli))                
                roboVencedor = robo2;
            
            quantidadeRodadas++;
        }while(roboVencedor == null);
        
        System.out.println("ESTATÍSTICAS DA PARTIDA");
        System.out.printf("Robô %s",robo1.retornarNome(robo1.getCor()));
        System.out.print(" - Movimentos inválidos: " + robo1.getMovimentoInvalido());
        System.out.println(" - Movimentos válidos: " + robo1.getMovimentoValido());
        
        System.out.printf("Robô %s",robo2.retornarNome(robo2.getCor()));
        System.out.print(" - Movimentos inválidos: " + robo2.getMovimentoInvalido());
        System.out.print(" - Movimentos válidos: " + robo2.getMovimentoValido());
               
        System.out.println("\nQuantidade de rodadas: " + (quantidadeRodadas));
        System.out.println("Robô vencedor: " + roboVencedor.retornarNome(roboVencedor.getCor()));

    }    
}
