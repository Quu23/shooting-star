import java.awt.Color;

public class Boss extends Enemy{

    Boss(int x,int y) {
        super(x, y, 5, 5, 3,5, 1000000, 1000, Color.black);
    }

    @Override
    public void addBullet() {
        // todo
    }
    
}
