public class RandomEnemy extends Enemy {
    
    RandomEnemy(int x,int y,int width,int height){
        super(x,y,width,height,2,20);
    }
    @Override
    public void addBullet() {
        int degree = new java.util.Random().nextInt(181)+179;//179°から359°の間
        this.bullets.add(new Bullet(this.x,this.y+5,3,3,Math.toRadians(degree),this.MOVE_SPEED+3));
    }
}
