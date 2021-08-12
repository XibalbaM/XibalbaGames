package fr.xibalba.games.main.panels;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.xibalba.games.main.Const;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.ui.PanelManager;
import fr.xibalba.games.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TopPanel extends Panel {

    private GridPane topBar;

    @Override
    public void init(PanelManager manager) {

        super.init(manager);
        this.topBar = this.layout;
        //this.layout.getStylesheets().add(getClass().getClassLoader().getResource("style/top.css").toString());
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
        MaterialDesignIconView fullScreen = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_MAXIMIZE);
        GridPane.setVgrow(fullScreen, Priority.ALWAYS);
        MaterialDesignIconView hide = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_MINIMIZE);
        GridPane.setVgrow(hide, Priority.ALWAYS);
        close.setFill(Color.WHITE);
        close.setOpacity(0.70f);
        close.setSize("18.0px");
        close.setOnMouseEntered(e -> close.setOpacity(1.0f));
        close.setOnMouseExited(e -> close.setOpacity(0.70f));
        close.setOnMouseClicked(e -> {
            GameCore.stopApp();
        });
        close.setTranslateX(70.0d);

        fullScreen.setFill(Color.WHITE);
        fullScreen.setOpacity(0.70f);
        fullScreen.setSize("16.0px");
        fullScreen.setOnMouseEntered(e -> fullScreen.setOpacity(1.0f));
        fullScreen.setOnMouseExited(e -> fullScreen.setOpacity(0.70f));
        fullScreen.setOnMouseClicked(e -> {
            this.panelManager.getStage().setMaximized(!this.panelManager.getStage().isMaximized());
            this.panelManager.onResize();
        });

        fullScreen.setTranslateX(50.0d);

        hide.setFill(Color.WHITE);
        hide.setOpacity(0.70f);
        hide.setSize("16.0px");
        hide.setOnMouseEntered(e -> hide.setOpacity(1.0f));
        hide.setOnMouseExited(e -> hide.setOpacity(0.70f));
        hide.setOnMouseClicked(e -> this.panelManager.getStage().setIconified(true));
        hide.setTranslateX(26.0d);

        topBarButton.getChildren().addAll(close, fullScreen, hide);
    }

    @Override
    public String getName() {

        return "paneltop";
    }
}
