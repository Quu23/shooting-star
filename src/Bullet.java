public class Bullet extends Entity{
    //ラジアン
    double angle;

    Bullet(int x,int y,int width,int height,double angle){
        super(x,y,width,height);
        this.angle=angle;
    }
}
