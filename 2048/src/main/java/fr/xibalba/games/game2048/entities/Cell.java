package fr.xibalba.games.game2048.entities;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Cell extends StackPane {


    public Cell(int size) {


    }

    public static enum CellColor {
        TEST(Color.RED);

        Color color = Color.YELLOW;

        CellColor(Color color) {

        }

        public Color getColor() {

            return color;
        }
    }
}
