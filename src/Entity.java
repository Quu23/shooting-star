public abstract class Entity {

    // x,yはエンティティの中心の座標　w,hは円で言うところの半径
    int x,y,width,height;

    Entity(int x,int y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    // 対象のエンティティと接しているかどうか。
    public boolean isHit(Entity target) {

        double angle = Math.atan2(this.y-target.y, this.x-target.x);

        double distance = 
        Math.sqrt((this.x-target.x)*(this.x-target.x)+(this.y-target.y)*(this.y-target.y))
        -((this.width/Math.cos(angle))+(target.width/Math.cos(angle)));

        if(distance<5){
            return true;
        }
        return false;
    }

}
