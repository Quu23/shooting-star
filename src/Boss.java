import java.awt.Color;

public class Boss extends Enemy{

    final int MAX_HP  = 1000;

    int degreeCount=0;

    Cannon cannons[] = {new Cannon(this.x-140,this.y+50),new Cannon(this.x-105,this.y+50),new Cannon(this.x+105,this.y+50),new Cannon(this.x+140,this.y+50)};

    Boss(int x,int y) {
        super(x, y, 5, 5, 3,5, 1000000, 1000, Color.black);
    }

    @Override
    public void action() {
        if(bulletCoolTime<=0){
            this.addBullet();
            bulletCoolTime=this.BULLET_COOL_TIME;
        }
        for (Cannon cannon : cannons) {
            cannon.action();
        }
        this.moveBullet();
    }

    @Override
    public boolean isHit(Entity target) {
        return super.isHit(target)||(/*playerが本体に当たっているか。*/false);
    }

    @Override
    public void addBullet() {
        int intervalDegree=20;

        if(this.degreeCount<7){
            new Bullet(this.x,this.y+10,3,3,Math.toRadians(210+intervalDegree*this.degreeCount),this.MOVE_SPEED+2);
        }else{
            new Bullet(this.x,this.y+10,3,3,Math.toRadians(210+intervalDegree*(12-this.degreeCount)),this.MOVE_SPEED+2);
        }
        this.degreeCount%=12;
    }
    
}
