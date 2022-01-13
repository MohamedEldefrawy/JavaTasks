import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends Applet {

    private Line line;

    @Override
    public void init() {
        super.init();
        line = new Line();
        addMouseListener(line);
        addMouseMotionListener(line);
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.drawLine((int) line.x1Pos, (int) line.y1Pos, (int) line.x2Pos, (int) line.y2Pos);
    }

    private class Line extends MouseAdapter {
        double x1Pos;
        double y1Pos;
        double x2Pos;
        double y2Pos;


        @Override
        public void mousePressed(MouseEvent e) {
            x1Pos = e.getX();
            y1Pos = e.getY();

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            x2Pos = e.getX();
            y2Pos = e.getY();
            repaint();

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            x2Pos = e.getX();
            y2Pos = e.getY();
            repaint();
        }
    }
}
