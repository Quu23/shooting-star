public class Cannon extends StraightEnemy{

    Cannon(double x, double y) {
        super((int)x,(int)y, 5, 5);
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
