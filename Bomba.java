public class Bomba extends Obstaculo{
    
    @Override
    public boolean bater(int coodY, int coodX){
        return (coodY == cobY && coodX == cobX);    
    }

    @Override
    public boolean qntdObstaculo(int qntd) {
        if(qntd < 1 || qntd > 3)
            return false;
            
        return true;
    }
}
