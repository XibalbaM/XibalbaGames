package fr.xibalba.defaultGames.pong.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Platform extends Rectangle {

    protected int score = 0;
    protected String playerName;

    public Platform(double x, double y, double height, Color color, String playerName) {

        super(x, y, 30, height);
        this.setArcHeight(5);
        this.setArcWidth(5);
        this.setFill(color);
        this.playerName = playerName;
    }

    public void loop() {

    }

    public int getScore() {

        return score;
    }

    public void setScore(int score) {

        this.score = score;
    }

    public String getPlayerName() {

        return playerName;
    }
}
