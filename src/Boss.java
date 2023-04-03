import java.awt.Color;

public class Boss extends Enemy{

    final int MAX_HP  = 1000;

    int degreeCount=0;
    int direction=1;

    Cannon cannons[] = {new Cannon(this.x-140,this.y+50),new Cannon(this.x-105,this.y+50),new Cannon(this.x+105,this.y+50),new Cannon(this.x+140,this.y+50)};

    Boss(int x,int y) {
        super(x, y, 25, 25, 1,5, 1000000, 1000, Color.red);
    }

    @Override
    public void action() {
        this.move();
        if(bulletCoolTime<=0){
            this.addBullet();
            bulletCoolTime=this.BULLET_COOL_TIME;
        }
        for (Cannon cannon : cannons) {
            cannon.action();
        }
        this.moveBullet();
        bulletCoolTime--;
    }

    @Override
    public boolean isHit(Entity target) {
        if((this.x-target.x)*(this.x-target.x)+(this.y-target.y)*(this.y-target.y)<this.width*this.width)return true;
        return false;
    }

    public void move() {
        this.direction*=(this.x>260||this.x<140)?-1:1;
        this.x+=direction*2;
    }

    @Override
    public void addBullet() {
        int intervalDegree=20;

        if(this.degreeCount<7){
            this.bullets.add(new Bullet(this.x,this.y+10,3,3,Math.toRadians(210+intervalDegree*this.degreeCount),this.MOVE_SPEED+2));
        }else{
            this.bullets.add(new Bullet(this.x,this.y+10,3,3,Math.toRadians(210+intervalDegree*(12-this.degreeCount)),this.MOVE_SPEED+2));
        }
        this.degreeCount++;
        this.degreeCount%=12;
    }
    
}
