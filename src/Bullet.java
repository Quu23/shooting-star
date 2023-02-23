import java.util.Objects;

public class Bullet extends Entity{
    //ラジアン
    double angle;

    Bullet(int x,int y,int width,int height,double angle){
        super(x,y,width,height);
        this.angle=angle;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)return true;
        if(obj==null)return false;
        if(!(obj instanceof Bullet))return false;
        Bullet b=(Bullet)obj;
        if(b.x==this.x&&b.y==this.y&&b.width==this.width&&b.height==this.height&&b.angle==this.angle){
           return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x,this.y,this.width,this.height,this.angle);
    }
}
