import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends Applet {

    private Line[] lines;
    private Line line;
    private int index;

    @Override
    public void init() {
        line = new Line();
        lines = new Line[10];

        addMouseListener(line);
        addMouseMotionListener(line);
    }


    @Override
    public void paint(Graphics g) {
        if (index < 10)
            g.drawLine((int) line.x1Pos, (int) line.y1Pos, (int) line.x2Pos, (int) line.y2Pos);


        for (int i = 0; i < index; i++) {
            g.drawLine((int) lines[i].x1Pos, (int) lines[i].y1Pos, (int) lines[i].x2Pos, (int) lines[i].y2Pos);
        }
    }

    private class Line extends MouseAdapter {
        double x1Pos;
        double y1Pos;
        double x2Pos;
        double y2Pos;
        boolean isDragged = false;

        public Line(double x1Pos, double y1Pos, double x2Pos, double y2Pos) {
            this.x1Pos = x1Pos;
            this.y1Pos = y1Pos;
            this.x2Pos = x2Pos;
            this.y2Pos = y2Pos;
        }

        public Line() {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            x1Pos = e.getX();
            y1Pos = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            x2Pos = e.getX();
            y2Pos = e.getY();

            if (index < 10 && isDragged) {
                lines[index] = new Line(x1Pos, y1Pos, x2Pos, y2Pos);
                index++;
            }
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            isDragged = true;
            x2Pos = e.getX();
            y2Pos = e.getY();
            repaint();
        }


    }
}
