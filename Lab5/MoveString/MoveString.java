import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveString extends Applet implements KeyListener{


    int xPos = 100;
    int yPos = 100;

    public void init() {
	addKeyListener(this);
    }

    public void paint(Graphics g) {
        g.drawString("HelloWorld!!!!", xPos, yPos);
    }

    public void up() {
        yPos -= 10;
    }

    public void down() {
        yPos += 10;
    }

    public void left() {
        xPos -= 10;

    }

    public void right() {
        xPos += 10;
    }


    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            up();
            repaint();
        }

        if (code == KeyEvent.VK_DOWN) {
            down();
             repaint();
        }

        if (code == KeyEvent.VK_LEFT) {
            left();
             repaint();
        }

        if (code == KeyEvent.VK_RIGHT) {
            right();
            repaint();
        }
    }


    public void keyReleased(KeyEvent e) {
 	int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            up();
            repaint();
        }

        if (code == KeyEvent.VK_DOWN) {
            down();
            repaint();
        }

        if (code == KeyEvent.VK_LEFT) {
            left();
            repaint();
        }

        if (code == KeyEvent.VK_RIGHT) {
            right();
            repaint();
        }
    }


    public void keyTyped(KeyEvent e) {
         int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            up();
            repaint();
        }

        if (code == KeyEvent.VK_DOWN) {
            down();
            repaint();
        }

        if (code == KeyEvent.VK_LEFT) {
            left();
            repaint();
        }

        if (code == KeyEvent.VK_RIGHT) {
            right();
            repaint();
        }
    }


}
