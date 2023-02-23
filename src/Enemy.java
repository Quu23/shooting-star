public abstract class Enemy extends Plane{
    final int MOVE_SPEED=2;
    int hp;
    boolean died = false;
    

    Enemy(int x,int y,int width,int height,int power){
        super(x,y,width,height,power);
    }

    @Override
    public void action() {
        this.y++;
        int r = new java.util.Random().nextInt(5);
        if(r<2){
            this.addBullet();
        }
        this.moveBullet();
    }
    
    @Override
    public void moveBullet() {
        for (Bullet bullet : this.bullets) {
            bullet.y-=this.MOVE_SPEED*Math.sin(bullet.angle);
            bullet.x-=this.MOVE_SPEED*Math.cos(bullet.angle);
        }
    }

}
