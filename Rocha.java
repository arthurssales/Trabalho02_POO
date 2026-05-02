public class Rocha extends Obstaculo{
    
   @Override
    public boolean bater(int coodY, int coodX){
        return (coodY == cobY && coodX == cobX);
    }

    @Override
    public boolean qntdObstaculo(int qntd){
        if(qntd < 2 || qntd > 5)
            return false;

        return true;
    }

}
