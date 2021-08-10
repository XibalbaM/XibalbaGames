package fr.xibalba.games.main.entities.fx;

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

    public GameView(Image icon, String name, String description) {

        this.icon = icon;
        this.name = name;
        this.description = description;
        this.textFill = textFill;
        this.backgroundColor = backgroundColor;

        this.setPrefSize(550, 115);
        this.getStylesheets().clear();
        this.getStylesheets().add(this.getClass().getResource("gameview.css").toExternalForm());

        Rectangle rectangle = new Rectangle(550, 100);
        rectangle.setOpacity(1);
        rectangle.setFill(Color.gray(0.05));
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        rectangle.setStroke(Color.gray(0.8));
        rectangle.setStrokeWidth(3);
        this.getChildren().add(rectangle);

        if (this.icon != null) {

            ImageView imageView = new ImageView(icon);
            imageView.setFitWidth(75);
            imageView.setFitHeight(75);
            imageView.setTranslateX(-225);

            Separator separator = new Separator();
            separator.setOrientation(Orientation.VERTICAL);
            separator.setTranslateX(-175);
            separator.setMinHeight(100);
            separator.setMaxHeight(100);
            separator.setMinWidth(2);
            separator.setMaxWidth(2);

            Text title = new Text(this.name);
            title.setFill(Color.WHITE);
            title.setFont(Font.font(35));
            title.setTranslateX(-130);
            title.setTranslateY(-30);

            Label label = new Label(this.description);
            label.setFont(Font.font(25));
            label.setTextFill(Color.gray(0.65));
            label.setMinHeight(85);
            label.setMaxHeight(85);
            label.setMinWidth(525);
            label.setMaxWidth(525);
            label.setTranslateX(95);
            label.setTranslateY(20);

            this.getChildren().addAll(separator, title, label, imageView);
        } else {

            Text title = new Text(this.name);
            title.setFill(Color.WHITE);
            title.setFont(Font.font(35));
            title.setTranslateX(-225);
            title.setTranslateY(-30);
            title.setTextOrigin(VPos.CENTER);

            Label label = new Label(this.description);
            label.setFont(Font.font(25));
            label.setTextFill(Color.gray(0.65));
            label.setMinHeight(85);
            label.setMaxHeight(85);
            label.setMinWidth(525);
            label.setMaxWidth(525);
            label.setTranslateX(10);
            label.setTranslateY(20);

            this.getChildren().addAll(title, label);
        }

        this.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                rectangle.setFill(Color.gray(0.1));
            else
                rectangle.setFill(Color.gray(0.05));
        });
    }

    public static GameView gameToView(Game game) {

        return new GameView(game.icon(), game.name(), game.description());
    }
}
