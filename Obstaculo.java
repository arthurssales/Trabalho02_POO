public abstract class Obstaculo {
    protected int cobX;
    protected int cobY;

    

    public abstract boolean bater(int coodY,int coodX);

    //pode criar getters e setters em uma classe abstrata?

    public void setCobX(int cobX){
        this.cobX = cobX;
    }

    public void setCobY(int cobY) {
        this.cobY = cobY;
    }
    
    
}
