import java.awt.Color;

public abstract class Enemy extends Plane{
    final int MOVE_SPEED=1;
    final int BULLET_COOL_TIME;
    int hp;
    boolean isDied = false;
    int bulletCoolTime=0;
    final int SCORE;
    final Color color;
    

    Enemy(int x,int y,int width,int height,int power,int bullet_cool_time,int score,int hp,Color color){
        super(x,y,width,height,power);
        this.BULLET_COOL_TIME=bullet_cool_time;
        this.SCORE = score;
        this.hp = hp;
        this.color = color;
    }

    @Override
    public void action() {
        bulletCoolTime--;
   
        int r = new java.util.Random().nextInt(5);
        if(r==0){
            if(bulletCoolTime<=0){
                this.addBullet();
                bulletCoolTime=this.BULLET_COOL_TIME;
            }
        }else{
            this.y+=this.MOVE_SPEED;
        }
        this.moveBullet();
    }
    
    @Override
    public void moveBullet() {
        for (Bullet bullet : this.bullets) {
            bullet.y-=bullet.moveSpeed*Math.sin(bullet.angle);
            bullet.x-=bullet.moveSpeed*Math.cos(bullet.angle);
        }
    }

}
