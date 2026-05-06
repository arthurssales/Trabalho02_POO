import java.util.Random;

public class RoboEstrategico extends Robo {
    private int aliX;
    private int aliY;
    Random random = new Random();
    public RoboEstrategico(String cor, int aliX, int aliY) {
        super(cor);
        this.aliX = aliX;
        this.aliY = aliY;
    }

    @Override
    public void mover(int sentido) throws MovimentoInvalidoException, NumeroSentidoInvalidoException {

        int difX = aliX - coodX;
        int difY = aliY - coodY;

        int primeira = 0;
        int segunda = 0;

        // define prioridades (estratégia)
        if (difX != 0 && difY != 0) {
            if (random.nextInt(2) == 0) {
                primeira = 3; // right
                segunda = 1;  // up
            } else {
                primeira = 1; // up
                segunda = 3;  // right
            }
        } else if (difX != 0) {
            primeira = 3;
        } else if (difY != 0) {
            primeira = 1;
        } else {
            return; //chegou no alimento
        }

        try {
            super.mover(primeira);
            return;
        } catch (MovimentoInvalidoException e) {
        }

        if (segunda != 0) {
            try {
                super.mover(segunda);
                return;
            } catch (MovimentoInvalidoException e) {
            }
        }
        // pra caso nenhum dos outros funcionem
        int[] opcoes = {1, 2, 3, 4};

        for (int i = 0; i < 4; i++) {
            int tentativa = opcoes[random.nextInt(4)];
            try {
                super.mover(tentativa);
                return;
            } catch (MovimentoInvalidoException e) {
            }
        }
    }
}