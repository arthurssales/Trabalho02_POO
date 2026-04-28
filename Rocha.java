public class Rocha extends Obstaculo{
    
   @Override
    public boolean bater(int coodY, int coodX){
        return (coodY == cobY && coodX == cobX);
    }
}
