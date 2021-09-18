package fr.xibalba.games.main.panels.popup;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.xibalba.games.main.Const;
import fr.xibalba.games.ui.panel.PopupPanel;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TopPopupPanel {

    private GridPane layout = new GridPane();
    private PopupPanel popup;
    private GridPane topBar;

    public void init(PopupPanel popup) {

        GridPane.setHgrow(layout, Priority.ALWAYS);
        GridPane.setVgrow(layout, Priority.ALWAYS);
        this.topBar = this.layout;
        this.popup = popup;
        this.layout.getStylesheets().add(getClass().getClassLoader().getResource("style/top.css").toString());
        this.layout.setBackground(new Background(new BackgroundFill(Color.rgb(31, 35, 37), CornerRadii.EMPTY, Insets.EMPTY)));

        initTitle();
        initButtons();
    }

    public void initTitle() {

        Label topBarTitle = new Label(Const.TITLE);
        this.layout.getChildren().add(topBarTitle);
        GridPane.setHgrow(topBarTitle, Priority.ALWAYS);
        GridPane.setVgrow(topBarTitle, Priority.ALWAYS);
        GridPane.setHalignment(topBarTitle, HPos.CENTER);
        topBarTitle.setFont(Font.font("Consolas", FontWeight.THIN, FontPosture.REGULAR, 22.0f));
        topBarTitle.setStyle("-fx-text-fill: #ababab");
    }

    private void initButtons() {

        GridPane topBarButton = new GridPane();
        this.layout.getChildren().add(topBarButton);
        topBarButton.setMinWidth(100.0d);
        topBarButton.setMaxWidth(100.0d);
        GridPane.setHgrow(topBarButton, Priority.ALWAYS);
        GridPane.setVgrow(topBarButton, Priority.ALWAYS);
        GridPane.setHalignment(topBarButton, HPos.RIGHT);

        MaterialDesignIconView close = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_CLOSE);
        GridPane.setVgrow(close, Priority.ALWAYS);

        close.setFill(Color.WHITE);
        close.setOpacity(0.70f);
        close.setSize("18.0px");
        close.setOnMouseEntered(e -> close.setOpacity(1.0f));
        close.setOnMouseExited(e -> close.setOpacity(0.70f));
        close.setOnMouseClicked(e -> {
            this.popup.close();
        });
        close.setTranslateX(70.0d);

        topBarButton.getChildren().addAll(close);
    }

    public GridPane getLayout() {

        return layout;
    }
}
