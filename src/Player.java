public class Player extends Plane{

    final int MAX_HP  = 10;
    
    int bulletNum;
    int hp;
    int lv;
    int exp;

    //コンストラクタ
    Player(int x,int y,int width,int height){
        super(x,y,width,height,1);
        this.bulletNum=1;
        this.hp = 10;
        this.lv = 1;
        this.exp=0;
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