import java.io.IOException;
import java.util.Scanner;

public class MainRoboAlimento {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcao;
        boolean posicaoValida = false;
        
        int numeroSentido;
        String nomeSentido;
        int posicaoXAli = 0, posicaoYAli = 0;
        String corRobo;        
        int i,j;
              
        String[][] matriz = new String[4][4];
        
        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                matriz[i][j]= "0";
            }
        }
        Robo robo = null;
        
        while(robo == null){
            System.out.println("Escolha uma cor pro robo"); 
            System.out.println("azul - vermelho - preto - branco");
            corRobo = teclado.nextLine();      
            try{     
                robo = new Robo(corRobo);   
            }catch(CorInvalidaException e){ 
                System.out.println(e.getMessage());
            }
        }

        while(!posicaoValida || (posicaoYAli == 0 && posicaoXAli == 0)){
            System.out.println("Indique a posição do alimento. A posição (0,0) é inválida");
            
            System.out.println("Coordenada do eixo x (de 0 a 3): "); 
            posicaoXAli = teclado.nextInt();
            teclado.nextLine();        
            
            System.out.println("Coordenada do eixo y (de 0 a 3): ");
            posicaoYAli = teclado.nextInt();
            teclado.nextLine();    
            
            try{    
                matriz [posicaoYAli][posicaoXAli] = "^";
                posicaoValida = true;    
            }
            catch(Exception e){
                System.out.println("Coordenada inválida!");
            }                   
        }
         
        matriz[0][0] = robo.getCor();

        System.out.println("1 - Comandos por escrita\n2 - Comandos por números");
        opcao = teclado.nextInt();
        teclado.nextLine();
            
        if (opcao == 1) {
            do{
                try {
                    if (System.getProperty("os.name").contains("Windows")) {
                        // Comando específico para Windows
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } else {
                        // Comando para Linux/MacOS (opcional, para tornar o código cross-platform)
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    }
                } catch (IOException | InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                //robo sumindo da matriz
                for(i=0;i<4;i++){
                    for(j=0;j<4;j++){
                        if(matriz[i][j].equals(robo.getCor()))
                            System.out.print(matriz[i][j] + " ");
                        else
                            System.out.print("?" + " ");
                    }
                    System.out.println();
                }

                System.out.println("Indique o sentido do robô: ");
                
                System.out.println("up\ndown\nright\nleft");
                nomeSentido = teclado.nextLine();

                int coodX = robo.getCoodX();
                int coodY = robo.getCoodY();
                
                try{                 
                
                    try {
                        matriz[robo.getCoodY()][robo.getCoodX()] = "0";
                        robo.mover(nomeSentido);                        
                        matriz[robo.getCoodY()][robo.getCoodX()] = robo.getCor();
                    } 
                    catch (NomeSentidoInvalidoException e) {
                        System.out.println(e.getMessage());
                        matriz[coodY][coodX] = robo.getCor();
                        teclado.nextLine();
                    }
                    
                }
                catch(MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                    matriz[coodY][coodX] = robo.getCor();
                    teclado.nextLine();
                }
                //quando trata uma excessao, o robo some da matriz
            }while(!robo.alimentoEncontrado(posicaoYAli,posicaoXAli)); 
        }
                 
        if (opcao == 2) {
            do{
                try {
                    if (System.getProperty("os.name").contains("Windows")) {
                        // Comando específico para Windows
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } else {
                        // Comando para Linux/MacOS (opcional, para tornar o código cross-platform)
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    }
                } catch (IOException | InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                
                //robo sumnido da matriz
                for(i=0;i<4;i++){
                    for(j=0;j<4;j++){
                        if(matriz[i][j].equals(robo.getCor()))
                            System.out.print(matriz[i][j] + " ");
                        else
                            System.out.print("?" + " ");
                    }
                    System.out.println();
                }
                
                System.out.println("Indique o sentido do robô: ");
                
                System.out.println("1 - up\n2 - down\n3 - right\n4 - left");
                numeroSentido = teclado.nextInt();
                teclado.nextLine();

                int coodX = robo.getCoodX();
                int coodY = robo.getCoodY();
                
                try{
                        
                    try {
                        matriz[robo.getCoodY()][robo.getCoodX()] = "0";
                        robo.mover(numeroSentido);
                        matriz[robo.getCoodY()][robo.getCoodX()] = robo.getCor();
                    } 
                    catch (NumeroSentidoInvalidoException e) {
                        System.out.println(e.getMessage());
                        matriz[coodY][coodX] = robo.getCor();
                        teclado.nextLine();
                    }
                    
                }
                catch(MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                    matriz[coodY][coodX] = robo.getCor();
                    teclado.nextLine();
                    
                }
                //quando trata uma excessão, o robo some da matriz
                    
            }while(!robo.alimentoEncontrado(posicaoYAli, posicaoXAli)); 
                
        }

        for(i=0;i<4;i++){
            for(j=0;j<4;j++){
                if(matriz[i][j].equals(robo.getCor()))
                    System.out.print(matriz[i][j] + " ");
                else
                    System.out.print("?" + " ");
            }
            System.out.println();
        }

        System.out.println("GAME OVER!!\nAlimento encontrado!");
   }
}