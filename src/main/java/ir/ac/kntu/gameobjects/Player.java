package ir.ac.kntu.gameobjects;

import ir.ac.kntu.constants.Direction;
import ir.ac.kntu.constants.GlobalConstants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static ir.ac.kntu.constants.GlobalConstants.PLAYER_HEIGHT;
import static ir.ac.kntu.constants.GlobalConstants.PLAYER_WIDTH;

public class Player implements GameObject{
    private final double xPos;
    private double yPos;

    public Player(boolean isPlayer2) {
        if (isPlayer2) {
            this.xPos = GlobalConstants.COMPUTER_FIRST_POS_X;
            this.yPos = GlobalConstants.COMPUTER_FIRST_POS_Y;
        } else {
            this.xPos = GlobalConstants.PLAYER_FIRST_POS_X;
            this.yPos = GlobalConstants.PLAYER_FIRST_POS_Y;
        }
    }

    public void move(int step, Direction direction) {
        if (step != 0) {
            switch (direction) {
                case UP -> yPos -= step;
                case DOWN -> yPos += step;
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        //TODO complete
        gc.setFill(Color.rgb(38, 144, 144));
        gc.fillRect(xPos, yPos,PLAYER_WIDTH , PLAYER_HEIGHT);
    }

    @Override
    public double getPositionX() {
        return xPos;
    }

    @Override
    public double getPositionY() {
        return yPos;
    }

}
