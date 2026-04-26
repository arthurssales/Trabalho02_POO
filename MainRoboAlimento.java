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
              
        String[][] matriz = new String[6][6];
        
        for(i=0;i<6;i++){
            for(j=0;j<6;j++){
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
            
            System.out.println("Coordenada do eixo x (de 0 a 5): "); 
            posicaoXAli = teclado.nextInt();
            teclado.nextLine();        
            
            System.out.println("Coordenada do eixo y (de 0 a 5): ");
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
         
        System.out.println("1 - Comandos por escrita\n2 - Comandos por números");
        opcao = teclado.nextInt();
        teclado.nextLine();
            
        if (opcao == 1) {
            do{
                
                for(i=0;i<6;i++){
                    for(j=0;j<6;j++){
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
                
                try{                 
                
                    try {
                        matriz[robo.getEixoY()][robo.getEixoX()] = "0";
                        robo.mover(nomeSentido);                        
                        matriz[robo.getEixoY()][robo.getEixoX()] = robo.getCor();
                    } 
                    catch (NomeSentidoInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
                catch(MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                }
                //quando trata uma excessao, o robo some da matriz
            }while(!robo.alimentoEncontrado(posicaoYAli,posicaoXAli)); 
        }
                 
        if (opcao == 2) {
            do{
                
                for(i=0;i<6;i++){
                    for(j=0;j<6;j++){
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
                
                try{
                        
                    try {
                        matriz[robo.getEixoY()][robo.getEixoX()] = "0";
                        robo.mover(numeroSentido);
                        matriz[robo.getEixoY()][robo.getEixoX()] = robo.getCor();
                    } 
                    catch (NumeroSentidoInvalidoException e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
                catch(MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                    
                }
                //quando trata uma excessão, o robo some da matriz
                    
            }while(!robo.alimentoEncontrado(posicaoYAli, posicaoXAli)); 
                
        }

        for(i=0;i<6;i++){
            for(j=0;j<6;j++){
                if(matriz[i][j].equals(robo.getCor()))
                    System.out.print(matriz[i][j] + " ");
                else
                    System.out.print("?" + " ");
            }
            System.out.println();
        }
        
        System.out.println("O robô encontrou o alimento!");
   }
}