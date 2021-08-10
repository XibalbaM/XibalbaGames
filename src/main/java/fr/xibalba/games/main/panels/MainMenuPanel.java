package fr.xibalba.games.main.panels;

import fr.xibalba.games.main.Const;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.ui.PanelManager;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

public class MainMenuPanel extends AMenuPanel {

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        Label title = new Label(Const.TITLE);
        title.setFont(new Font(70));
        title.setPrefSize(700, 50);
        title.setTranslateX(250);
        title.setTranslateY(100);

        TextMenuButton play = new TextMenuButton("PLAY", Font.font(45), 355, 50);
        play.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(new GamesListPanel(this)));
        play.setTranslateX(322);
        play.setTranslateY(300);

        TextMenuButton settings = new TextMenuButton("SETTINGS", Font.font(30), 170, 40);
        settings.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(new GlobalSettingsPanel(this)));
        settings.setTranslateX(322);
        settings.setTranslateY(375);

        TextMenuButton leave = new TextMenuButton("LEAVE", Font.font(30), 170, 40);
        leave.setOnMouseClicked(event -> GameCore.stopApp());
        leave.setTranslateX(510);
        leave.setTranslateY(375);

        root.getChildren().addAll(title, play, leave, settings);
    }

    @Override
    public String getName() {

        return "Main Menu Panel";
    }
}
