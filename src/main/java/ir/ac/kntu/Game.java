package ir.ac.kntu;

import ir.ac.kntu.constants.GlobalConstants;
import ir.ac.kntu.gamecontroller.EventHandler;
import ir.ac.kntu.gameobjects.Ball;
import ir.ac.kntu.gameobjects.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.Random;

import static ir.ac.kntu.constants.GlobalConstants.*;

public class Game extends Application {

    private static Player player;
    private static Player computer;
    private static Ball ball;
    private GameState gameState;
    private int playerScore = 0;
    private int computerScore = 0;

    public static void main(String[] args) {
        player = new Player(false);
        computer = new Player(true);
        ball = new Ball(1, 1);
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pong34 build.01");
        Canvas canvas = new Canvas(CANVAS_WIDTH, GlobalConstants.CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10),
                e -> start(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);
        Scene scene = new Scene(new Pane(canvas));
        canvas.setOnMouseClicked(e -> gameState = GameState.RUNNING);
        //TODO attach EventHandler
        EventHandler.getInstance().attachEventHandlers(scene);
        stage.setScene(scene);
        stage.show();
        tl.play();
    }

    public void start(GraphicsContext gc) {
        gc.setFill(Color.SILVER);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        if (gameState == GameState.RUNNING) {
            //TODO make ball moving and draw it
            ball.setXPos(ball.getPositionX() + ball.getXSpeed());
            ball.setYPos(ball.getPositionY() + ball.getYSpeed());

            //computer playing
            if (ball.getPositionX() < CANVAS_WIDTH - CANVAS_WIDTH / 6.5) {
                computer.setYPos(ball.getPositionY() - PLAYER_HEIGHT / 2.0);
            } else {
                computer.setYPos(ball.getPositionY() > computer.getPositionY() + PLAYER_HEIGHT / 2.0 ?
                        computer.getPositionY() + 1 : computer.getPositionY() - 1);
            }
            ball.draw(gc);
        } else {
            gc.setFont(Font.font(50));
            gc.setFill(Color.DARKBLUE);
            gc.setStroke(Color.DARKBLUE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("GRAND THEFT PONG VI", (double) CANVAS_WIDTH / 2, (double) CANVAS_HEIGHT / 2);
            ball.setXPos((double) CANVAS_WIDTH / 2);
            ball.setYPos((double) CANVAS_HEIGHT / 2);

            ball.setXSpeed(new Random().nextInt(2) == 0 ? 1 : -1);
            ball.setYSpeed(new Random().nextInt(2) == 0 ? 1 : -1);
        }
        if (ball.getPositionX() < player.getPositionX() - PLAYER_WIDTH) {
            computerScore++;
            gameState = GameState.FINISHED;
        }
        if (ball.getPositionX() > computer.getPositionX() + PLAYER_WIDTH) {
            playerScore++;
            gameState = GameState.FINISHED;
        }
        if (ball.getPositionY() > CANVAS_HEIGHT - BALL_RADIUS || ball.getPositionY() < 0) {
            ball.setYSpeed(ball.getYSpeed() * (-1));
        }

        if( ((ball.getPositionX() + BALL_RADIUS > computer.getPositionX()) && ball.getPositionY() >=
                computer.getPositionY() && ball.getPositionY() <= computer.getPositionY() +
                PLAYER_HEIGHT) || ((ball.getPositionX() < player.getPositionX() + PLAYER_WIDTH) &&
                ball.getPositionY() >= player.getPositionY() && ball.getPositionY() <= player.getPositionY()
                + PLAYER_HEIGHT) ) {

            if (ball.getXSpeed() < 0) {
                ball.setXSpeed(ball.getXSpeed() - 0.5);
            } else ball.setXSpeed(ball.getXSpeed() + 0.5);
            if (ball.getYSpeed() < 0) {
                ball.setYSpeed(ball.getYSpeed() - 0.5);
            } else ball.setYSpeed(ball.getYSpeed() + 0.5);

            ball.setXSpeed(ball.getXSpeed() * (-1));
        }

        gc.setFont(Font.font(25));
        gc.setStroke(Color.RED);
        gc.strokeText(playerScore + "\t\t" + computerScore, (double) CANVAS_WIDTH/2, 50);

        player.draw(gc);
        computer.draw(gc);
    }

    public static Player getPlayer() {
        return player;
    }
}
