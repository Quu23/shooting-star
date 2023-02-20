public class Bullet extends Entity{
    double angle;

    Bullet(int x,int y,int width,int height,int angle){
        super(x,y,width,height);
        this.angle=angle;
    }
}
