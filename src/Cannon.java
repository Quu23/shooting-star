import java.awt.Color;

public class Cannon extends StraightEnemy{

    Cannon(double x, double y) {
        super((int)x,(int)y, 5, 5);
        this.color=Color.GRAY;
        this.hp=100;
    }

    @Override
    public void action() {
        this.bulletCoolTime--;
        if(this.bulletCoolTime<=0){
            this.addBullet();
            this.bulletCoolTime=this.BULLET_COOL_TIME;
        }
        this.moveBullet();
    }
    
}
