public abstract class Obstaculo {
    protected int cobX;
    protected int cobY;

    

    public abstract boolean bater(int coodY,int coodX);

    public abstract boolean qntdObstaculo(int qntd);

    public void setCobX(int cobX){
        this.cobX = cobX;
    }

    public void setCobY(int cobY) {
        this.cobY = cobY;
    }
    
    
}
