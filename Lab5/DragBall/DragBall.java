import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DragBall extends Applet implements  MouseListener, MouseMotionListener {
    double xPos = 0;
    double yPos = 0;
    int radius = 40;
    boolean isClicked = false;

    public void init() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }


    public void paint(Graphics g) {
        //draw elipse
        g.setColor(Color.RED);
        g.fillOval((int) xPos, (int) yPos, radius * 2, radius * 2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        isClicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isClicked) {
            xPos = e.getX() - radius;
            yPos = e.getY() - radius;
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}