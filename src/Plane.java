import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Plane extends Entity{

    List<Bullet> bullets=new ArrayList<Bullet>();
    int power;

    Plane(int x, int y, int width, int height,int power) {
        super(x, y, width, height);
        this.power=power;
    }

    public abstract void addBullet();
    public abstract void moveBullet();
    public abstract void action();

     @Override
     public boolean equals(Object obj) {
         if(obj==this)return true;
         if(obj==null)return false;
         if(!(obj instanceof Plane))return false;
         Plane p=(Plane)obj;
         if(p.x==this.x&&p.y==this.y&&p.width==this.width&&p.height==this.height&&p.power==this.power&&p.bullets.size()==this.bullets.size()){
            return true;
         }
         return false;
     }

     @Override
     public int hashCode() {
         return Objects.hash(this.x,this.y,this.width,this.height,this.power,this.bullets);
     }
    
}
