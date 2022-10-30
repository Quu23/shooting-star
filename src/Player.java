public class Player{
    int hp = 10;
    int lv = 1;
    
    public void lv(int lv,int score,boolean breakEnemy){
        if(breakEnemy){
            score+=10;
            breakEnemy=false;
        }

        int tscore = score;
        if(score==(tscore+100)){
            lv++;
        }
    }

    public void hp(int lv,int maxHp){
        int tlv = lv;
        if(lv==(tlv+1)){
            maxHp+=5;
        }
    }

    public void attack(){

    }

    public void heal(int hp, boolean healOrb){
        if(healOrb){
            hp++;
        }
    }
}