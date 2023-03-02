import java.awt.Color;
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

    static GameMode gameMode = GameMode.START;

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

    static List<Enemy> enemies=new ArrayList<>();

    static int score=0;

    public static void main(String[] args) {
        Main window = new Main("test");
        player=new Player(200, 600, 5 , 5);
        player.hp-=7;
        Timer timer=new Timer(10, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                window.gameLoop();
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
        this.add(canvas);
        this.setVisible(true);
    }


    private void gameLoop(){

        // ゲームループ。ゲームの内容はここに書いてね

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
                    player.hp--;
                }
            }
            for (Bullet bullet : player.bullets) {
                if(bullet.isHit(enemy)){
                    // 自分の球が相手に当たってるか
                    enemiesDied.add(enemy);
                    playerBulletDied.add(bullet);
                }
            }
            for (Bullet bullet : playerBulletDied) {
                player.bullets.remove(bullet);
            }

        }
        for (Enemy enemy : enemiesDied) {
            enemies.remove(enemy);
        }

    }

    private void draw(Graphics g) {

        switch (gameMode) {
            case START:
                
                break;
            case PLAY:
                g.setColor(Color.BLACK);
                g.drawString("hp", 5, 640);
                g.setColor(Color.GRAY);
                g.fillRect(20, 630, 100, 10);
                if(player.hp<=0){
                    g.setColor(Color.RED);
                    g.fillRect(20, 630, 100*player.hp/player.MAX_HP, 10);
                }
                g.setColor(Color.BLUE);
                g.fillRect(player.x-player.width, player.y-player.height, player.width*2, player.height*2);
                for (Bullet bullet : player.bullets) {
                    g.fillOval(bullet.x-bullet.width, bullet.y-bullet.height, bullet.width*2, bullet.height*2);;
                }
        
                g.setColor(Color.RED);
                for (Enemy enemy : enemies) {
                    g.fillRect(enemy.x-enemy.width, enemy.y-enemy.height, enemy.width*2, enemy.height*2);
                    for (Bullet bullet : enemy.bullets) {
                        g.fillOval(bullet.x-bullet.width, bullet.y-bullet.height, bullet.width*2, bullet.height*2);;
                    }
                }
                break;
            case GAME_OVER:
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