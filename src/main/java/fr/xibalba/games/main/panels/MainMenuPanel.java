package fr.xibalba.games.main.panels;

import fr.xibalba.games.main.Const;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.ui.PanelManager;
import fr.xibalba.utils.javaFX.DisplayUtils;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import static fr.xibalba.games.main.GameCore.getHeight;
import static fr.xibalba.games.main.GameCore.getWidth;

public class MainMenuPanel extends AMenuPanel {

    private Label title;
    private TextMenuButton play, settings, leave;

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        title = new Label(Const.TITLE);

        play = new TextMenuButton("PLAY", Font.font(45), getWidth() * 0.355, getHeight() * 0.085);
        play.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(new GamesListPanel()));

        settings = new TextMenuButton("SETTINGS", Font.font(30), 170, 40);
        settings.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(new GlobalSettingsPanel(this)));

        leave = new TextMenuButton("LEAVE", Font.font(30), 170, 40);
        leave.setOnMouseClicked(event -> GameCore.stopApp());

        root.getChildren().addAll(title, play, leave, settings);
    }

    @Override
    public void doSize() {

        super.doSize();

        title.setFont(Font.font(getWidth() * 0.07));
        title.setTranslateX((getWidth() - DisplayUtils.computeTextWidth(title.getFont(), title.getText(), 0)) * 0.5);
        title.setTranslateY(getHeight() * 0.16);

        play.setSize(getWidth() * 0.355, getHeight() * 0.08, Font.font(getHeight() * 0.075));
        play.setTranslateX((getWidth() - play.getPrefWidth()) * 0.5);
        play.setTranslateY((getHeight() - play.getPrefHeight()) * 0.5);

        settings.setSize(getWidth() * 0.17, getHeight() * 0.066, Font.font(getHeight() * 0.045));
        settings.setTranslateX(getWidth() * 0.322);
        settings.setTranslateY(getHeight() * 0.625);

        leave.setSize(getWidth() * 0.17, getHeight() * 0.066, Font.font(getHeight() * 0.045));
        leave.setTranslateX(getWidth() * 0.510);
        leave.setTranslateY(getHeight() * 0.625);
    }

    @Override
    public String getName() {

        return "Main Menu Panel";
    }
}
