package fr.xibalba.games.main.entities.fx;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextMenuButton extends StackPane {

    private String name;

    public TextMenuButton(String name, double width, double height) {
        this(name, Font.font(20), width, height);
    }

    public TextMenuButton(String name, Font font, double width, double height) {

        this.name = name;

        Text text = new Text(name);
        text.setFont(font);
        text.setFill(Color.WHITE);

        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setOpacity(1);
        rectangle.setFill(Color.gray(0.05));
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        rectangle.setStroke(Color.gray(0.8));
        rectangle.setStrokeWidth(3);

        this.setAlignment(Pos.CENTER);

        this.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                rectangle.setFill(Color.gray(0.1));
            } else {
                rectangle.setFill(Color.BLACK);
            }
        });

        this.getChildren().addAll(rectangle, text);
    }
}
