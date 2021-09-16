package fr.xibalba.games.gameOfLife.entities.fx;

import fr.xibalba.games.gameOfLife.panels.GameOfLifePanel;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Parent {

    private static final Color GRAY = Color.grayRgb(100);
    private static final Color BLUE = Color.rgb(27, 208, 255);

    private int posX, posY;
    private boolean isAlive;
    private Rectangle cell;

    public Cell(int posX, int posY) {

        this.posX = posX;
        this.posY = posY;

        this.cell = new Rectangle(0, 0, 12, 12);
        this.cell.setFill(GRAY);

        this.setOnMouseClicked(event -> this.setAlive(!this.isAlive));
        this.setOnMouseEntered(event -> {
            if (GameOfLifePanel.isRightClick())
                this.setAlive(!this.isAlive());
        });

        this.getChildren().add(this.cell);
    }

    public int getPosX() {

        return posX;
    }

    public int getPosY() {

        return posY;
    }

    public boolean isAlive() {

        return isAlive;
    }

    public void setAlive(boolean alive) {

        isAlive = alive;

        this.cell.setFill(isAlive ? BLUE : GRAY);
    }
}
