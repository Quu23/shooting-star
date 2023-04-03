import java.awt.Color;

public class Boss extends Enemy{

    int degreeCount=0;

    Cannon cannons[] = {new Cannon(this.x-50,this.y+10),new Cannon(this.x-25,this.y+10),new Cannon(this.x+25,this.y+10),new Cannon(this.x+50,this.y+10)};

    Boss(int x,int y) {
        super(x, y, 5, 5, 3,5, 1000000, 1000, Color.red);
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
        bulletCoolTime--;
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
