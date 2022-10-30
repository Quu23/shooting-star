import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JFrame implements KeyListener{

    JPanel canvas;
    /**
     * <p>0 : space</p>
     * <p>1 : left</p>
     * <p>2 : up</p>
     * <p>3 : right</p>
     * <p>4 : down</p>
     */
    static boolean[] isKeyPressed={false,false,false,false,false};

    static int score=0;

    public static void main(String[] args) {
        Main window = new Main("test");
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
        System.out.println("てすとだよ");
        System.out.println(Arrays.toString(isKeyPressed));
    }

    private void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(10, 10, 10, 10);
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