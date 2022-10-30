import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{

    public static void main(String[] args) {
        Main window = new Main("test");
    }

    public Main(String title){
        this.setTitle(title);
        this.setSize(400, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel canvas = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };
        this.add(canvas);
        this.setVisible(true);
    }

    private void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(10, 10, 10, 10);
    }

}
