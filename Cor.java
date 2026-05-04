import java.util.ArrayList;
public class Cor {
    private ArrayList<String> coresDisponiveis = new ArrayList<>();

    public Cor(){
        coresDisponiveis.add("azul");
        coresDisponiveis.add("vermelho");   
        coresDisponiveis.add("preto");   
        coresDisponiveis.add("branco");   
    }
    
    public void mostrarCores(){
        for(String cor : coresDisponiveis){
            System.out.print(" | " + cor + " | ");
        }
    }

    /*public boolean verificarCor(String corRobo){
        if(coresDisponiveis.contains(corRobo))
            return true;

        return false;
    }*/

    public boolean selecionarCor(String corRobo){
        if(coresDisponiveis.contains(corRobo)){
            coresDisponiveis.remove(corRobo);
            return true;
        }
        
        return false;
    }
}
