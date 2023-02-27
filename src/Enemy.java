public abstract class Enemy extends Plane{
    final int MOVE_SPEED=2;
    int hp;
    boolean died = false;
    int bulletCoolTime=0;
    

    Enemy(int x,int y,int width,int height,int power){
        super(x,y,width,height,power);
    }

    @Override
    public void action() {
        bulletCoolTime--;
   
        int r = new java.util.Random().nextInt(5);
        if(r==0&&bulletCoolTime<=0){
            this.addBullet();
            bulletCoolTime=20;
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
