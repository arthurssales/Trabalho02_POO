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
            System.out.println("Escolha uma cor pro robo: "); System.out.println("azul - vermelho - preto - branco");
            corRobo = teclado.nextLine();      
            try{     
                robo = new Robo(corRobo);   
                break;
            }catch(CorInvalidaException e){ 
                System.out.println(e.getMessage());
            }
        }

        while(!posicaoValida){
            
            System.out.println("Indique a posição do alimento");
            //não permitir que a coordenada selecionada seja (0,0)

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
        
        
        System.out.println("1 - Mover por escrita\n2 - Mover por números");
        opcao = teclado.nextInt();
        teclado.nextLine();
            
        if (opcao == 1) {
            do{
                System.out.println("Indique o sentido do robô: ");
                //retirar a opcao de sair
                System.out.println("up\ndown\nright\nleft\nsair");
                nomeSentido = teclado.nextLine();
                
                try{                 
                
                    try {
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
            
                for(i=0;i<6;i++){
                    for(j=0;j<6;j++){
                        System.out.print(matriz[i][j] + " ");
                
                    }
                    System.out.println();
                }
                
            }while(!robo.alimentoEncontrado(posicaoYAli,posicaoXAli)); //!condicaoDeVitoria;
        }
                 
        if (opcao == 2) {
            do{
                System.out.println("Indique o sentido do robô: ");
                //retirar a opcao de sair
                System.out.println("1 - up\n2 - down\n3 - right\n4 - left\n5 - sair ");
                numeroSentido = teclado.nextInt();
                teclado.nextLine();
                
                try{
                        
                    try {
                        robo.mover(numeroSentido);
                    } 
                    catch (NumeroSentidoInvalidoException e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
                catch(MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                    
                }
                               
            }while(robo.alimentoEncontrado(posicaoYAli, posicaoXAli)); //!condicaoDeVitoria;
            
            for(i=0;i<6;i++){
                for(j=0;j<6;j++){
                    System.out.print(matriz[i][j] + " ");
            
                }
                System.out.println();
            }
        }

        System.out.println("Robo encontrou o alimento!");
   }
}