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
    private Rectangle rectangle, collide;
    private Text text;

    public TextMenuButton(String name, double width, double height) {

        this(name, Font.font(20), width, height);
    }

    public TextMenuButton(String name, Font font, double width, double height) {

        this.name = name;
        this.setPrefSize(width, height);
        this.setMaxSize(width, height);
        this.setMinSize(width, height);

        text = new Text(name);
        text.setFont(font);
        text.setFill(Color.WHITE);

        collide = new Rectangle(width, height);
        collide.setOpacity(0);
        collide.widthProperty().bind(this.widthProperty());
        collide.heightProperty().bind(this.heightProperty());

        rectangle = new Rectangle(width, height);
        rectangle.setOpacity(1);
        rectangle.setFill(Color.gray(0.05));
        rectangle.setArcHeight(90);
        rectangle.setArcWidth(90);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        rectangle.setStroke(Color.gray(0.8));
        rectangle.setStrokeWidth(3);

        this.setAlignment(Pos.CENTER);

        collide.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                rectangle.setFill(Color.gray(0.1));
            } else {
                rectangle.setFill(Color.BLACK);
            }
        });

        this.getChildren().addAll(rectangle, text, collide);
    }

    public void setSize(double width, double height) {

        this.setSize(width, height, text.getFont());
    }

    public void setSize(double width, double height, Font font) {

        this.setPrefSize(width, height);
        this.setMaxSize(width, height);
        this.setMinSize(width, height);
        text.setFont(font);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
    }

    public void setText(String text) {

        this.text.setText(text);
    }
}