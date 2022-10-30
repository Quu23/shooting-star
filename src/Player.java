public class Player{
    int hp = 10;
    int lv = 1;
    int maxHp = 10;
    
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