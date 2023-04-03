package pac;
public abstract class Entity {

    // x,yはエンティティの中心の座標　w,hは円で言うところの半径
    double x,y;
    int width,height;

    Entity(double x,double y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    // 対象のエンティティと接しているかどうか。
    public boolean isHit(Entity target) {

        double distance = 
        Math.sqrt((this.x-target.x)*(this.x-target.x)+(this.y-target.y)*(this.y-target.y));

        if(distance<10){
            return true;
        }
        return false;
    }

}
