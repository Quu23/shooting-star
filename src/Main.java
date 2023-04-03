import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JFrame implements KeyListener{

    static GameMode gameMode = GameMode.BOSS;

    JPanel canvas;
    /**
     * <p>0 : space</p>
     * <p>1 : left</p>
     * <p>2 : up</p>
     * <p>3 : right</p>
     * <p>4 : down</p>
     */
    static boolean[] isKeyPressed={false,false,false,false,false};

    static Player player;

    static Boss boss;

    static List<Enemy> enemies=new ArrayList<>();

    static int score=0;

    public static void main(String[] args) {

        Main window = new Main("test");
        player=new Player(200, 600, 5 , 5);
        boss=new Boss(190,100);
        for (int index = 0; index < 15; index++) {
           player.levelUp();
        }

        Timer timer=new Timer(10, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (gameMode) {
                    case START:
                        // space ボタン押したらPLAYに移動
                        if(isKeyPressed[0]){
                            gameMode=GameMode.PLAY;
                        }
                        break;
                    case PLAY:
                        window.gameLoop();
                        if(player.lv==12){
                            gameMode=GameMode.BOSS;
                            enemies.clear();
                            player.bullets.clear();
                            player.x=200;
                            player.y=600;
                        }
                        break;
                    case BOSS:
                        boss_loop();
                        if(boss.hp<=0){
                            gameMode=GameMode.CLEAR;
                        }
                        break;
                    case CLEAR:
                    case GAME_OVER:
                        // SPACE ボタン押したらゲームを終了する
                        if(isKeyPressed[0]){
                            System.exit(0);
                        }
                        break;
                    default:
                        break;
                }
                window.canvas.repaint();
            }
            
        });
        timer.start();
    }

    public Main(String title){
        this.setTitle(title);
        this.setSize(400, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(this);
        canvas = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };
        canvas.setBackground(Color.WHITE);
        this.add(canvas);
        this.setVisible(true);
    }


    private void gameLoop(){

        // ゲームループ。ゲームの内容はここに書いてね

        if(score>=250*player.lv*player.lv+250*player.lv){
            player.levelUp();
        }

        player.action();
        
        if(enemies.size()<5){
            int x = new java.util.Random().nextInt(400);
            switch (new java.util.Random().nextInt(10)) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    enemies.add(new StraightEnemy(x, 0, 5, 5));
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                    enemies.add(new RandomEnemy(x, 0, 7, 7));
                    break;

                case 9:
                    enemies.add(new RadialEnemy(x, 0, 10, 10));
                    break;
            
                default:
                    break;
            }
        }
        List<Enemy> enemiesDied=new ArrayList<>();
        List<Bullet> playerBulletDied = new ArrayList<>(); 
        List<Bullet> enemyBulletDied = new ArrayList<>(); 
        for (int i=0;i<enemies.size();i++) {

            Enemy enemy=enemies.get(i);

            enemy.action();
            if(player.isHit(enemy)){
                //自分が相手に当たってるか
                player.hp=0;
            }
            for (Bullet bullet : enemy.bullets) {
                if(player.isHit(bullet)){
                    //相手の球が自分に当たってるか
                    player.hp-=enemy.power;
                    enemyBulletDied.add(bullet);
                }
            }
            for (Bullet bullet : enemyBulletDied) {
                enemy.bullets.remove(bullet);
            }
            for (Bullet bullet : player.bullets) {
                if(bullet.isHit(enemy)){
                    // 自分の球が相手に当たってるか
                    enemy.hp -= player.power;
                    if(enemy.hp<=0){
                        enemiesDied.add(enemy);
                        score += enemy.SCORE;
                    }
                    playerBulletDied.add(bullet);
                }
                if(bullet.y<0)playerBulletDied.add(bullet);
            }
            for (Bullet bullet : playerBulletDied) {
                player.bullets.remove(bullet);
            }

            if(enemy.y>=700){
                enemiesDied.add(enemy);
            }

        }
        for (Enemy enemy : enemiesDied) {
            enemies.remove(enemy);
        }

        if(player.hp<=0){
            gameMode=GameMode.GAME_OVER;
            enemies.clear();
            enemiesDied.clear();
            playerBulletDied.clear();
            enemyBulletDied.clear();
        }

    }

    private static void boss_loop(){
        List<Bullet> playerBulletDied = new ArrayList<>(); 
        List<Bullet> bossBulletDied = new ArrayList<>();
        List<Bullet> cannonBulletDied = new ArrayList<>();

        List<Cannon> cannonDied = new ArrayList<>();

        player.action();

        boss.action();

        if(boss.isHit(player))player.hp=0;

        for (Bullet bullet : player.bullets) {
            //自分の弾がぼすにあたったときとキャノンに当たった時。
            if(boss.isHit(bullet)){
                boss.hp-=player.power;
                playerBulletDied.add(bullet);
            }
            for (Cannon cannon : boss.cannons) {
                if(bullet.isHit(cannon)){
                    cannon.hp-=player.power;
                    playerBulletDied.add(bullet);
                }
            }
            if(bullet.y<0)playerBulletDied.add(bullet);
        }

        for (Bullet bullet : boss.bullets) {
            if(player.isHit(bullet)){
                player.hp-=boss.power;
                bossBulletDied.add(bullet);
            }else if(bullet.y>700){
                bossBulletDied.add(bullet);
            }
        }

        for (Cannon cannon : boss.cannons) {    
            for (Bullet bullet : cannon.bullets) {
                //敵のキャノンの弾が自分に当たった時
                if(bullet.isHit(player)){
                    player.hp-=cannon.power;
                    cannonBulletDied.add(bullet);
                }else if(bullet.y>700){
                    cannonBulletDied.add(bullet);
                }
            }
            for (Bullet bullet : cannonBulletDied) {
                cannon.bullets.remove(bullet);
            }
            if(cannon.hp<0)cannonDied.add(cannon);
        }



        for (Bullet bullet : bossBulletDied) {
            boss.bullets.remove(bullet);
        }

        for (Bullet bullet : playerBulletDied) {
            player.bullets.remove(bullet);
        }
        for (Cannon c : cannonDied) {
            boss.cannons.remove(c);
        }

        if(player.hp<=0){
            gameMode=GameMode.GAME_OVER;
            enemies.clear();
        }
    }

    private void draw(Graphics g) {

        switch (gameMode) {
            case START:
                g.setFont(new Font("ＭＳ Ｐゴシック",Font.PLAIN,50));
                g.setColor(Color.BLUE);
                g.drawString("SHOOTING", 67, 200);
                g.drawString("STAR", 125, 270);
                g.setFont(new Font("游明朝",Font.PLAIN,30));
                g.setColor(Color.RED);
                g.drawString("PUSH SPACE TO START", 12, 500);
                break;
            case PLAY:
                g.setColor(Color.BLACK);
                g.drawString("score:"+Main.score+"/"+(250*player.lv*player.lv+250*player.lv),5,10);
                g.drawString("Lv:"+player.lv,5,655);
                g.drawString("hp", 5, 640);
                g.setColor(Color.GRAY);
                g.fillRect(20, 630, 100, 10);
                if(player.hp>=0){
                    g.setColor(Color.RED);
                    g.fillRect(20, 630, 100*player.hp/player.MAX_HP, 10);
                }
                g.setColor(Color.BLUE);
                g.fillRect((int)player.x-player.width, (int)player.y-player.height, player.width*2, player.height*2);
                for (Bullet bullet : player.bullets) {
                    g.fillOval((int)bullet.x-bullet.width, (int)bullet.y-bullet.height, bullet.width*2, bullet.height*2);;
                }
        
                for (Enemy enemy : enemies) {
                    g.setColor(enemy.color);
                    g.fillRect((int)enemy.x-enemy.width, (int)enemy.y-enemy.height, enemy.width*2, enemy.height*2);
                    for (Bullet bullet : enemy.bullets) {
                        g.fillOval((int)bullet.x-bullet.width, (int)bullet.y-bullet.height, bullet.width*2, bullet.height*2);;
                    }
                }
                break;
            case BOSS:
                g.setColor(Color.BLACK);
                g.drawString("score:"+Main.score+"/"+(250*player.lv*player.lv+250*player.lv),5,10);
                g.drawString("Lv:"+player.lv,5,655);
                g.drawString("hp", 5, 640);
                g.setColor(Color.GRAY);
                g.fillRect(20, 630, 100, 10);
                if(player.hp>=0){
                    g.setColor(Color.RED);
                    g.fillRect(20, 630, 100*player.hp/player.MAX_HP, 10);
                }
                   
                g.setColor(Color.BLACK);
                g.drawString("BOSS hp", 205, 10);
                g.setColor(Color.GRAY);
                g.fillRect(260, 0, 100, 10);
                if(boss.hp>=0){
                    g.setColor(Color.RED);
                    g.fillRect(260, 0, 100*boss.hp/boss.MAX_HP, 10);
                }
                g.setColor(Color.BLACK);
                g.fillRect((int)boss.x-165, 50, 330, 100);
                g.fillRect((int)boss.x-75, 20, 150, 160);
                g.setColor(Color.WHITE);
                g.fillRect((int)boss.x-50, 60, 100, 120);
                g.setColor(boss.color);
                g.fillOval((int)boss.x-boss.width, (int)boss.y-boss.height, boss.width*2, boss.height*2);
                for (Cannon c : boss.cannons) {
                    g.setColor(c.color);
                    g.fillRect((int)c.x-c.width, (int)c.y-c.height, c.width*2, c.height*2);
                    g.setColor(Color.BLACK);
                    for (Bullet bullet : c.bullets) {
                        g.fillOval((int)bullet.x-bullet.width, (int)bullet.y-bullet.height, bullet.width*2, bullet.height*2);
                    }
                }
                g.setColor(Color.BLACK);
                for (Bullet bullet : boss.bullets) {
                    g.fillOval((int)bullet.x-bullet.width, (int)bullet.y-bullet.height, bullet.width*2, bullet.height*2);
                }

                g.setColor(Color.BLUE);
                g.fillRect((int)player.x-player.width, (int)player.y-player.height, player.width*2, player.height*2);
                for (Bullet bullet : player.bullets) {
                    g.fillOval((int)bullet.x-bullet.width, (int)bullet.y-bullet.height, bullet.width*2, bullet.height*2);
                }
                break;
            case GAME_OVER:
                g.setFont(new Font("ＭＳ Ｐゴシック",Font.PLAIN,50));
                g.setColor(Color.RED);
                g.drawString("GAME OVER", 53, 200);
                g.setFont(new Font("ＭＳ Ｐゴシック",Font.PLAIN,25));
                g.drawString("score:"+Main.score,50,300);
                g.drawString("Lv:"+player.lv,50,360);
                g.setFont(new Font("游明朝",Font.PLAIN,30));
                g.setColor(Color.BLACK);
                g.drawString("PUSH SPACE TO EXIT", 27, 500);
                break;
            case CLEAR:
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                isKeyPressed[0]=true;
                break;
            case KeyEvent.VK_LEFT:
                isKeyPressed[1]=true;
                break;
            case KeyEvent.VK_UP:
                isKeyPressed[2]=true;
                break;
            case KeyEvent.VK_RIGHT:
                isKeyPressed[3]=true;
                break;
            case KeyEvent.VK_DOWN:
                isKeyPressed[4]=true;
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                isKeyPressed[0]=false;
                break;
            case KeyEvent.VK_LEFT:
                isKeyPressed[1]=false;
                break;
            case KeyEvent.VK_UP:
                isKeyPressed[2]=false;
                break;
            case KeyEvent.VK_RIGHT:
                isKeyPressed[3]=false;
                break;
            case KeyEvent.VK_DOWN:
                isKeyPressed[4]=false;
                break;
            default:
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {}

}