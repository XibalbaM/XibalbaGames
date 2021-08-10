package fr.xibalba.games.main.entities.fx;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ImageMenuButton extends StackPane {

    private Image image;

    public ImageMenuButton(Image image, double width, double height) {

        this.image = image;

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width - 10);
        imageView.setFitHeight(height - 10);

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

        this.getChildren().addAll(rectangle, imageView);
    }
}
