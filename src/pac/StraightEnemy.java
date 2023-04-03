package pac;
import java.awt.Color;

public class StraightEnemy extends Enemy{
    StraightEnemy(int x,int y,int width,int height){
        super(x,y,width,height,1,50,100,3,Color.RED);
    }
    
    @Override
    public void addBullet() {
        this.bullets.add(new Bullet(this.x,this.y+5,3,3,Math.toRadians(270),this.MOVE_SPEED+3));
    }
}