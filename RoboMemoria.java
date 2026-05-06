import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RoboMemoria extends Robo {
    Random random = new Random();
    private Set<String> conhecidas = new HashSet<>();
    private Set<String> barradas = new HashSet<>();
    private int usada;
    private ArrayList<Integer> validoption = new ArrayList<>();
    private ArrayList<Integer> nonblockoption = new ArrayList<>();

    public RoboMemoria(String cor) {
        super(cor);
        conhecidas.add(coodX + "," + coodY);
        conhecidas.add(0 + "," + 0);
    }

    @Override
    public void mover(int sentido) throws MovimentoInvalidoException, NumeroSentidoInvalidoException {
        validoption.clear();
        nonblockoption.clear();
        for (int s = 1; s <= 4; s++) {

            int proxX = coodX;
            int proxY = coodY;
            // calcula
            if (s == 1)
                proxY++;
            else if (s == 2)
                proxY--;
            else if (s == 3)
                proxX++;
            else if (s == 4)
                proxX--;

            //valida
            if (proxX < 0 || proxX > 3 || proxY < 0 || proxY > 3) {
                continue; // ignora direção inválida
            }

            String pos = proxX + "," + proxY;

            // prioridade 1 não conhecidas e não bloqueada
            if (!conhecidas.contains(pos) && !barradas.contains(pos)) {
                validoption.add(s);
            }

            // prioridade 2 nao bloqueadas
            if (!barradas.contains(pos)) {
                nonblockoption.add(s);
            }
        }
        if(!validoption.isEmpty()){//uso prioridade 1
            usada = validoption.get(random.nextInt(validoption.size()));
        }
        else if(!nonblockoption.isEmpty()){//uso prioridade 2
            usada = nonblockoption.get(random.nextInt(nonblockoption.size()));
        }
        else{//nenhuma prioridade atendida
            usada = random.nextInt(4) + 1;
        }
        try {
            super.mover(usada);
        } catch (NumeroSentidoInvalidoException | MovimentoInvalidoException e) {
            //simplesmente ignora ou registra
            System.out.println(e.getMessage());
            return; // encerra o turno sem mover
        }
        conhecidas.add(coodX + "," + coodY);//salvando coordenada
    }

    public Set<String> getConhecidas() {
        return conhecidas;
    }

    public void setConhecidas(Set<String> conhecidas) {
        this.conhecidas = conhecidas;
    }

    public Set<String> getBarradas() {
        return barradas;
    }

    public void setBarradas(Set<String> barradas) {
        this.barradas = barradas;
    }
}