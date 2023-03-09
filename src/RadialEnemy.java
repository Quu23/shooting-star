import java.awt.Color;

public class RadialEnemy extends Enemy{
    
    RadialEnemy(int x,int y,int width,int height){
        super(x, y, width, height,1,50,300,20,Color.MAGENTA);
    }

    @Override
    public void addBullet() {
        int bulletNum=new java.util.Random().nextInt(3)+3;

        int intervalDegree=120/(bulletNum-1);

        for (int i = 0; i < bulletNum; i++) {
            this.bullets.add(new Bullet(this.x,this.y+10,3,3,Math.toRadians(210+intervalDegree*i),this.MOVE_SPEED+2));
        }
    }
}
