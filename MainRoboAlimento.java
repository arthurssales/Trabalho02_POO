import java.util.Scanner;

public class MainRoboAlimento {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcao;
        int numeroSentido;
        String nomeSentido;
        int posicaoXAlimento,posicaoYAlimento;
        int corRobo;        
        boolean posicaoValida = false;
        //colocar cor no robo
        
 

       while(true){
       System.out.println("Escolha uma cor pro robo: "); System.out.println("Azul\nVermelho\nPreto\nBranco: ");
       corRobo = teclado.nextLine();      
       try{     
           Robo robo = new Robo(corRobo);   
           break;
       }catch(CoordenadaInvalidaException  e){ 
   System.out.println(e.getMessage()
}
   }   

        while(!posicaoValida){
           
            System.out.println("Indique a posição do alimento");
           
            System.out.println("Coordenada do eixo x: "); 
            posicaoXAlimento = teclado.nextInt();
            teclado.nextLine();        
                
            System.out.println("Coordenada do eixo y: ");
            posicaoYAlimento = teclado.nextInt();
            teclado.nextLine();    
        
            
            Plano posicaoAlimento = new Plano();
            
            try{    
                posicaoAlimento.posicionarAlimento(posicaoXAlimento,posicaoYAlimento);
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
                    } 
                    catch (NomeSentidoInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
                catch(MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                }
        
                }while(!robo.isEncontrouAlimento()); //!condicaoDeVitoria;
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
                               
            }while(robo.isEncontrouAlimento()); //!condicaoDeVitoria;
        }
   }
}

