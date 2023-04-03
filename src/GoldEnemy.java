import java.awt.Color;

public class GoldEnemy extends Enemy{

    GoldEnemy(int x, int y) {
        super(x, y, 5, 5, 0, 0, 1000, 1, Color.ORANGE);
    }
    @Override
    public void addBullet() {}
    @Override
    public void action() {
        this.y+=this.MOVE_SPEED*2;
    }
}
