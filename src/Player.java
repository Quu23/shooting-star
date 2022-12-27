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
    
    public void lvUp(int lv,int score,boolean breakEnemy){
        score+=10;
        breakEnemy=false;

        if(score%100==0){
            lv++;
            maxHp+=5;
        }

    }

    public void attack(){

    }

    public void healUp(int hp, boolean isGetHealOrb){
        if(isGetHealOrb){
            hp++;
        }
    }
}