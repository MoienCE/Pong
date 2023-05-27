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
import java.util.Scanner;

import static ir.ac.kntu.constants.GlobalConstants.*;

public class Game extends Application {

    private static Player player1;
    private static Player player2;
    private static Ball ball;
    private GameState gameState;
    private int player1Score = 0;
    private int player2Score = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("window width: ");
        GlobalConstants.setCanvasWidth(Integer.parseInt(scanner.nextLine()));
        System.out.print("window height: ");
        GlobalConstants.setCanvasHeight(Integer.parseInt(scanner.nextLine()));

        player1 = new Player(false);
        player2 = new Player(true);
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
        EventHandler.getInstance().attachEventHandlers(scene);
        stage.setScene(scene);
        stage.show();
        tl.play();
    }

    public void start(GraphicsContext gc) {

        gc.setFill(Color.SILVER);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        if (gameState == GameState.RUNNING) {
            ball.setXPos(ball.getPositionX() + ball.getXSpeed());
            ball.setYPos(ball.getPositionY() + ball.getYSpeed());
            //computer playing

            ball.draw(gc);
        } else {
            gc.setFont(Font.font(CANVAS_WIDTH / 30.0));
            gc.setFill(Color.DARKBLUE);
            gc.setStroke(Color.DARKBLUE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("GRAND THEFT PONG VI", (double) CANVAS_WIDTH / 2,
                    (double) (CANVAS_HEIGHT / 2) + 10);
            ball.setXPos((double) CANVAS_WIDTH / 2);
            ball.setYPos((double) CANVAS_HEIGHT / 2);

            ball.setXSpeed(new Random().nextInt(2) == 0 ? 1 : -1);
            ball.setYSpeed(new Random().nextInt(2) == 0 ? 1 : -1);
        }
        if (ball.getPositionX() < player1.getPositionX() - PLAYER_WIDTH) {
            player2Score++;
            gameState = GameState.FINISHED;
        }
        if (ball.getPositionX() > player2.getPositionX() + PLAYER_WIDTH) {
            player1Score++;
            gameState = GameState.FINISHED;
        }
        if (ball.getPositionY() > CANVAS_HEIGHT - BALL_RADIUS || ball.getPositionY() < 0) {
            ball.setYSpeed(ball.getYSpeed() * (-1));
        }

        if( ((ball.getPositionX() + BALL_RADIUS > player2.getPositionX()) && ball.getPositionY() >=
                player2.getPositionY() && ball.getPositionY() <= player2.getPositionY() +
                PLAYER_HEIGHT) || ((ball.getPositionX() < player1.getPositionX() + PLAYER_WIDTH) &&
                ball.getPositionY() >= player1.getPositionY() && ball.getPositionY() <= player1.getPositionY()
                + PLAYER_HEIGHT) ) {

            if (ball.getXSpeed() < 0) {
                ball.setXSpeed(ball.getXSpeed() - 0.8);
            } else ball.setXSpeed(ball.getXSpeed() + 0.8);
            if (ball.getYSpeed() < 0) {
                ball.setYSpeed(ball.getYSpeed() - 0.8);
            } else ball.setYSpeed(ball.getYSpeed() + 0.8);

            ball.setXSpeed(ball.getXSpeed() * (-1));
            System.out.println(ball.getXSpeed() + " : " + ball.getYSpeed());
        }

        gc.setFont(Font.font(25));
        gc.setStroke(Color.RED);
        gc.strokeText(player1Score + "\t\t" + player2Score, (double) CANVAS_WIDTH/2, 50);

        player1.draw(gc);
        player2.draw(gc);

    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }
}
