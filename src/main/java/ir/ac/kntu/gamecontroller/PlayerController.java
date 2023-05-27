package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.Game;
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
    public void handlePlayerMovements(KeyCode keyCode) {
        //TODO set controller
        Player player = Game.getPlayer();
        if (keyCode == KeyCode.UP) {
            player.move(10, Direction.UP);
        }
        if (keyCode == KeyCode.DOWN) {
            player.move(10, Direction.DOWN);
        }
    }
}
