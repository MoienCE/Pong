package ir.ac.kntu.gamecontroller;

import ir.ac.kntu.Game;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class EventHandler {
    private static final EventHandler instance = new EventHandler();

    public static EventHandler getInstance() {
        return instance;
    }

    private EventHandler() {}

    public void attachEventHandlers(Scene scene){
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            KeyCode code = keyEvent.getCode();
            PlayerController.getInstance().handlePlayerMovements(code, Game.getPlayer1(), Game.getPlayer2());
        });
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {});
    }
}
