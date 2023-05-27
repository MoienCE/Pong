package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.gameobjects.Player;
import javafx.scene.input.KeyCode;

public interface InputManager {
    void handlePlayerMovements(KeyCode keyCode, Player player1, Player player2);
}
