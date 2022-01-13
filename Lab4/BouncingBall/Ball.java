import java.awt.*;
import java.applet.*;

public class Ball {

    private int xPos = 50;
    private int yPos = 100;
    private int xOffset = 10;
    private int yOffset = 10;
    private int radius = 40;

    public int getXPos() {
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    public int getXOffset() {
        return this.xOffset;
    }

    public int getYOffset() {
        return this.yOffset;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public void setXOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public void setYOffset(int yOffset) {
        this.yOffset = yOffset;
    }


    public Ball(int xPos, int yPos, int xOffset, int yOffset, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.radius = radius;
    }

    public Ball() {
        this.xPos = 50;
        this.yPos = 100;
        this.xOffset = 10;
        this.yOffset = 10;
        this.radius = 40;
    }

    public void paintBall(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(this.xPos, this.yPos, this.radius * 2, this.radius * 2);
    }
}