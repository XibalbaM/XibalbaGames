package fr.xibalba.defaultGames.pong.entities;

import fr.xibalba.defaultGames.pong.PongGame;
import javafx.scene.paint.Color;

public class AIPlatform extends Platform {

    private int level;

    public AIPlatform(double x, double y, double height, String name, Color color, int level) {

        super(x, y, height, color, name);
        this.level = level;
    }

    @Override
    public void loop() {
        if ((this.getY() + this.getHeight() / 2) < PongGame.getBall().getCenterY() && this.getY() + this.getHeight() < 600)
            this.setY(this.getY() + this.level);
        else if ((this.getY() + this.getHeight() / 2) > PongGame.getBall().getCenterY() && this.getY() > 0)
            this.setY(this.getY() - this.level);

    }
}
