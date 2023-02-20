import java.util.ArrayList;
import java.util.List;

public class Player extends Entity{

    List<Bullet> bullets=new ArrayList<Bullet>();
    int bulletNum=1;
    int hp = 10;
    int lv = 1;
    int power=1;
    final int MAX_HP  = 10;

    //コンストラクタ
    Player(int x,int y,int width,int height){
        super(x,y,width,height);
    }
    
    public void levelUp(){
        this.lv++;
        if(this.lv%2==0){
            this.power++;
        }
        if(this.lv%3==0){
            this.bulletNum+=2;
        }
    }

    public void attack(Enemy e){
        e.hp--;//todo:ダメージ量は仮
    }

    //自分のhpを回復させるメゾット？
    public void heal(boolean isGetHealOrb){
        if(isGetHealOrb){
            this.hp++;
        }
    }
}