import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Main extends Applet {

    private List<Line> lines;
    private Line line;

    @Override
    public void init() {
        line = new Line();
        lines = new ArrayList<Line>();

        addMouseListener(line);
        addMouseMotionListener(line);
    }


    @Override
    public void paint(Graphics g) {

        g.drawLine((int) line.x1Pos, (int) line.y1Pos, (int) line.x2Pos, (int) line.y2Pos);

        for (Line line : lines) {
            g.drawLine((int) line.x1Pos, (int) line.y1Pos, (int) line.x2Pos, (int) line.y2Pos);
        }
    }

    private class Line extends MouseAdapter {
        double x1Pos;
        double y1Pos;
        double x2Pos;
        double y2Pos;

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


            lines.add(new Line(x1Pos, y1Pos, x2Pos, y2Pos));

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
