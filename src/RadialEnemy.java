public class RadialEnemy extends RandomEnemy{
    
    RadialEnemy(int x,int y,int width,int height){
        super(x, y, width, height);
    }

    @Override
    public void addBullet() {
        int bulletNum=new java.util.Random().nextInt(3)+3;

        for (int i = 0; i < bulletNum; i++) {
            super.addBullet();
        }
    }
}
