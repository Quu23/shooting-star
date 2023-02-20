import java.util.ArrayList;
import java.util.List;

public abstract class Plane extends Entity{

    List<Bullet> bullets=new ArrayList<Bullet>();
    int power;

    Plane(int x, int y, int width, int height,int power) {
        super(x, y, width, height);
        this.power=power;
    }

    public abstract void addBullet();
    public abstract void moveBullet();
    
}
