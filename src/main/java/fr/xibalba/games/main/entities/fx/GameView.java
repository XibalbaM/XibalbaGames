package fr.xibalba.games.main.entities.fx;

import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.entities.Game;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameView extends StackPane {

    Image icon;
    String name, description;
    Color textFill, backgroundColor;

    Rectangle rectangle;
    ImageView imageView;
    Separator separator;
    Text title;
    Label label;

    public GameView(Image icon, String name, String description) {

        this.icon = icon;
        this.name = name;
        this.description = description;
        this.textFill = textFill;
        this.backgroundColor = backgroundColor;

        this.getStylesheets().clear();
        this.getStylesheets().add(this.getClass().getResource("gameview.css").toExternalForm());

        rectangle = new Rectangle(550, 100);
        rectangle.setOpacity(1);
        rectangle.setFill(Color.gray(0.05));
        rectangle.setArcHeight(90);
        rectangle.setArcWidth(90);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        rectangle.setStroke(Color.gray(0.8));
        rectangle.setStrokeWidth(3);
        this.getChildren().add(rectangle);

        if (this.icon != null) {

            imageView = new ImageView(icon);

            separator = new Separator();
            separator.setOrientation(Orientation.VERTICAL);

            title = new Text(this.name);
            title.setFill(Color.WHITE);

            label = new Label(this.description);
            label.setTextFill(Color.gray(0.65));

            this.getChildren().addAll(separator, title, label, imageView);
        } else {

            title = new Text(this.name);
            title.setFill(Color.WHITE);
            title.setTextOrigin(VPos.CENTER);

            label = new Label(this.description);
            label.setTextFill(Color.gray(0.65));

            this.getChildren().addAll(title, label);
        }

        this.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                rectangle.setFill(Color.gray(0.1));
            else
                rectangle.setFill(Color.gray(0.05));
        });

        this.doSize();
    }

    public void doSize() {

        this.setPrefSize(GameCore.getWidth() * 0.55, GameCore.getHeight() * 0.19);
        rectangle.setWidth(GameCore.getWidth() * 0.55);
        rectangle.setHeight(GameCore.getHeight() * 0.16);

        if (this.icon != null) {

            imageView.setFitWidth(GameCore.getWidth() * 0.075);
            imageView.setFitHeight(GameCore.getHeight() * 0.125);
            imageView.setTranslateX(GameCore.getWidth() * -0.225);

            separator.setTranslateX(GameCore.getWidth() * -0.175);
            separator.setMinHeight(rectangle.getHeight());
            separator.setMaxHeight(rectangle.getHeight());
            separator.setPrefWidth(2);

            title.setFont(Font.font(GameCore.getWidth() * 0.035));
            title.setTranslateX(GameCore.getWidth() * -0.1);
            title.setTranslateY(GameCore.getHeight() * -0.05);

            label.setFont(Font.font(GameCore.getWidth() * 0.025));
            label.setPrefWidth(GameCore.getWidth() * 0.525);
            label.setPrefHeight(GameCore.getHeight() * 0.14);
            label.setTranslateX(GameCore.getWidth() * 0.095);
            label.setTranslateY(GameCore.getHeight() * 0.03);
        } else {

            title.setFont(Font.font(GameCore.getWidth() * 0.035));
            title.setTranslateX((-(rectangle.getWidth()) / 2) + title.getLayoutBounds().getWidth() / 2 + 15);
            title.setTranslateY(GameCore.getHeight() * -0.03);

            label.setFont(Font.font(GameCore.getWidth() * 0.025));
            label.setMinWidth(GameCore.getWidth() * 0.525);
            label.setMaxWidth(GameCore.getWidth() * 0.525);
            label.setMinHeight(GameCore.getHeight() * 0.14);
            label.setMaxHeight(GameCore.getHeight() * 0.14);
            label.setTranslateX(GameCore.getWidth() * 0.01);
            label.setTranslateY(GameCore.getHeight() * 0.03);
        }
    }

    public static GameView gameToView(Game game) {

        return new GameView(game.icon(), game.name(), game.description());
    }
}
