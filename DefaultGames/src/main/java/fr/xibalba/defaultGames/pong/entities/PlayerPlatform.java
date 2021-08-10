package fr.xibalba.defaultGames.pong.entities;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

public class PlayerPlatform extends Platform {

    private KeyCode keyUp, keyDown;
    private Consumer<KeyEvent> keyHandler;

    public PlayerPlatform(int x, int y, int height, String name, Color color, KeyCode keyUp, KeyCode keyDown) {

        super(x, y, height, color, name);

        this.keyUp = keyUp;
        this.keyDown = keyDown;

        this.keyHandler = event -> {

            if (event.getCode().equals(keyUp) && this.getY() > 0) {
                this.setY(this.getY() - 10);
            } else if (event.getCode().equals(keyDown) && this.getY() + this.getHeight() < 600) {
                this.setY(this.getY() + 10);
            }
        };
    }

    public Consumer<KeyEvent> getKeyHandler() {

        return keyHandler;
    }
}
