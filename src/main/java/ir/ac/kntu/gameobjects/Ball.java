package ir.ac.kntu.gameobjects;

import ir.ac.kntu.constants.GlobalConstants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball implements GameObject{
    private final double radius;
    private double xPos;
    private double yPos;
    private double xSpeed;
    private double ySpeed;

    public Ball(int xSpeed, int ySpeed) {
        this.radius = GlobalConstants.BALL_RADIUS;
        this.xPos = GlobalConstants.CANVAS_WIDTH / 2.0;
        this.yPos = GlobalConstants.CANVAS_HEIGHT / 2.0;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(xPos, yPos, radius, radius);
    }

    @Override
    public double getPositionX() {
        return xPos;
    }

    @Override
    public double getPositionY() {
        return yPos;
    }


    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public double getXSpeed() {
        return xSpeed;
    }

    public double getYSpeed() {
        return ySpeed;
    }
}
