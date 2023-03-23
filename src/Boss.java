import java.awt.Color;

public class Boss extends Enemy{

    int degreeCount=0;

    Cannon cannons[] = {new Cannon(this.x-50,this.y+10),new Cannon(this.x-25,this.y+10),new Cannon(this.x+25,this.y+10),new Cannon(this.x+50,this.y+10)};

    Boss(int x,int y) {
        super(x, y, 5, 5, 3,5, 1000000, 1000, Color.black);
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
