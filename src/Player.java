public class Player{
    int hp = 10;
    int lv = 1;
    int maxHp = 10;

    //プレイヤーの座標
    int x;
    int y;

    //コンストラクタ
    Player(int x,int y){
        this.x=x;
        this.y=y;
    }
    
    public void levelUp(){
        if(Main.score%100==0){
            this.lv++;
            this.maxHp+=5;
            //ここでhpを回復させる処理を行うかは要相談
        }

    }

    public void attack(){

    }

    //自分のhpを回復させるメゾット？
    public void heal(boolean isGetHealOrb){
        if(isGetHealOrb){
            this.hp++;
        }
    }
}