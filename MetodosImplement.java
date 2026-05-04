import java.io.IOException;

public class MetodosImplement {
    
    public void limparTela(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {    
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delay(){
        try {
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
