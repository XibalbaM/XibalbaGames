package fr.xibalba.defaultGames.pong.entities;

import fr.xibalba.defaultGames.pong.PongGame;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    private int dx = 5, dy = -5;

    public Ball() {

        super(500, 300, 25);
        this.setFill(Color.DARKOLIVEGREEN);
    }

    public void loop() {

        this.setCenterX(this.getCenterX() + dx);
        this.setCenterY(this.getCenterY() + dy);

        if (this.getCenterY() <= 25)
            dy = 5;
        else if (this.getCenterY() >= 600 - 25)
            dy = -5;

        if (this.getCenterX() >= PongGame.getPlayer2().getX() - 25 && this.getCenterY() >= PongGame.getPlayer2().getY() - 25 && this.getCenterY() <= PongGame.getPlayer2().getY() + PongGame.getPlayer2().getHeight() + 25) {

            dx = -5;
        } else if (this.getCenterX() <= PongGame.getPlayer1().getX() + PongGame.getPlayer1().getWidth() + 25 && this.getCenterY() >= PongGame.getPlayer1().getY() - 25 && this.getCenterY() <= PongGame.getPlayer1().getY() + PongGame.getPlayer1().getHeight() + 25) {

            dx = 5;
        }

        if (this.getCenterX() <= 25)
            //PongGame.addPoint(PongGame.getPlayer2());
            dx = 5;
        else if (this.getCenterX() >= 1000 - 25)
            //PongGame.addPoint(PongGame.getPlayer1());
            dx = -5;
    }

    public int getDx() {

        return dx;
    }

    public void setDx(int dx) {

        this.dx = dx;
    }

    public int getDy() {

        return dy;
    }

    public void setDy(int dy) {

        this.dy = dy;
    }
}
