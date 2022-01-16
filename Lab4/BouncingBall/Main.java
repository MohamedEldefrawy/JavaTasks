import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Applet {
    private List<Ball> balls;
    Ball initBall;
    RepaintHandler repaintHandler;
    ScheduledExecutorService executorService;
    Random randomPosition;

    int MAX_BALLS = 100;

    public void init() {
        balls = new ArrayList<>();
        randomPosition = new Random(200);
        initBall = new Ball(randomPosition.nextInt(200), randomPosition.nextInt(200), 10, -10, 20);
        repaintHandler = new RepaintHandler();

        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void start() {

        executorService.scheduleWithFixedDelay(initBall, 0, 50, TimeUnit.MILLISECONDS);
        executorService.scheduleWithFixedDelay(repaintHandler, 50, 50, TimeUnit.MILLISECONDS);
    }

    public void paint(Graphics g) {

        g.fillOval(initBall.xPos, initBall.yPos, initBall.radius * 2, initBall.radius * 2);

        //draw elipse
        g.setColor(Color.RED);
        for (Ball ball : balls) {
            g.fillOval(ball.xPos, ball.yPos, ball.radius * 2, ball.radius * 2);
        }
    }

    private class RepaintHandler implements Runnable {

        @Override
        public void run() {
            repaint();
        }
    }


    private class Ball implements Runnable {

        int xPos;
        int yPos;
        int xOffset;
        int yOffset;
        int radius;
        boolean xImpact = false;
        boolean yImpact = false;

        public Ball() {
            xPos = 100;
            yPos = 50;
            xOffset = 10;
            yOffset = 10;
            radius = 20;
        }

        public Ball(int xPos, int yPos, int yOffset, int xOffset, int radius) {
            this.xPos = xPos;
            this.yPos = yPos;
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            this.radius = radius;

        }

        public void move() {
            xPos += xOffset;
            yPos += yOffset;
        }

        private void fireNewBall() {
            Ball temp;
            if (balls.size() < MAX_BALLS) {

                temp = new Ball(xPos, yPos, -yOffset, -xOffset, radius);
                balls.add(temp);
                executorService.scheduleWithFixedDelay(temp, 100, 50, TimeUnit.MILLISECONDS);
            }
        }

        @Override
        public void run() {
            move();

            if ((xPos + (2 * radius)) > getWidth()) {

                xPos = getWidth() - (2 * radius);
                xOffset *= -1;
                fireNewBall();
                yImpact = false;
                xImpact = true;
            }

            if ((xPos) < 0) {
                xPos = 0;
                xOffset *= -1;
                fireNewBall();

                yImpact = false;
                xImpact = true;

            }

            if ((yPos) < 0) {
                yPos = 0;
                yOffset *= -1;
                fireNewBall();

                yImpact = true;
                xImpact = false;
            }

            if (yPos + (2 * radius) > getHeight()) {

                yPos = getHeight() - 2 * radius;
                yOffset *= -1;
                fireNewBall();


                yImpact = true;
                xImpact = false;
            }
        }
    }
}