public class RandomEnemy extends Enemy {
    RandomEnemy(int x,int y,int width,int height){
        super(x,y,width,height,3);
    }

    int r = new java.util.Random().nextInt(179)+181;//179°から359°の間のつもり
    @Override
    public void addBullet() {
        this.bullets.add(new Bullet(this.x,this.y+5,3,3,Math.toRadians(270)));
    }

    @Override
    public void moveBullet() {
        for (Bullet bullet : bullets) {
            bullet.x+=Math.cos(Math.toRadians(r));
            bullet.y+=Math.sin(Math.toRadians(r));
        }
    }
}
