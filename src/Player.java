public class Player{
    int hp = 10;
    int lv = 1;
    
    public void lv(int lv,int score,boolean breakenemy){
        if(breakenemy){
            score+=10;
            breakenemy=false;
        }

        int tscore = score;
        if(score==(tscore+100)){
            lv++;
        }
    }

    public static void hp(int lv,int maxhp){
        int tlv = lv;
        if(lv==(tlv+1)){
            maxhp+=5;
        }
    }

    public static void attack(){

    }

    public static void hell(int hp, boolean hellorb){
        if(hellorb){
            hp++;
        }
    }
}