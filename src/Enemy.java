public abstract class Enemy extends Plane{
    final int MOVE_SPEED=2;
    int hp;
    

    Enemy(int x,int y,int width,int height,int power){
        super(x,y,width,height,power);
    }

    @Override
    public void moveBullet() {
        for (Bullet bullet : this.bullets) {
            bullet.y-=this.MOVE_SPEED*Math.sin(bullet.angle);
            bullet.x-=this.MOVE_SPEED*Math.cos(bullet.angle);
        }
    }

}
