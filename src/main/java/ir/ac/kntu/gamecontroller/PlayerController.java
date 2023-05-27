package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.constants.Direction;
import ir.ac.kntu.gameobjects.Player;
import javafx.scene.input.KeyCode;


public class PlayerController implements InputManager {

    private static final PlayerController instance = new PlayerController();

    public static PlayerController getInstance() {
        return instance;
    }

    private PlayerController() {}

    @Override
    public void handlePlayerMovements(KeyCode keyCode,
                                      Player player1, Player player2) {
        if (keyCode == KeyCode.W) {
            player1.move(10, Direction.UP);
        }
        if (keyCode == KeyCode.S) {
            player1.move(10, Direction.DOWN);
        }
        if (keyCode == KeyCode.UP) {
            player2.move(10, Direction.UP);
        }
        if (keyCode == KeyCode.DOWN) {
            player2.move(10, Direction.DOWN);
        }
    }
}
