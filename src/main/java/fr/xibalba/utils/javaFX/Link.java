package fr.xibalba.utils.javaFX;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Link extends Label {

    public Link(String text, String url) {

        this.setText(text);
        GridPane.setVgrow(this, Priority.ALWAYS);
        GridPane.setHgrow(this, Priority.ALWAYS);
        this.setStyle("-fx-text-fill: #69a7ed; -fx-font-size: 14px;");
        this.setUnderline(false);
        this.setCursor(Cursor.HAND);
        this.setOnMouseEntered(e -> this.setUnderline(true));
        this.setOnMouseExited(e -> this.setUnderline(false));
        this.setOnMouseClicked(event -> {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
