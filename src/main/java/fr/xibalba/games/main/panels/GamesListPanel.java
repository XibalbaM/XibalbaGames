package fr.xibalba.games.main.panels;

import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.GameDetection;
import fr.xibalba.games.main.entities.Game;
import fr.xibalba.games.main.entities.fx.GameView;
import fr.xibalba.games.main.entities.fx.ImageMenuButton;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.main.panels.popup.ReloadingPopup;
import fr.xibalba.games.ui.PanelManager;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static fr.xibalba.games.main.GameCore.getHeight;
import static fr.xibalba.games.main.GameCore.getWidth;

public class GamesListPanel extends AMenuPanel {

    private TextMenuButton back;
    private ScrollPane scrollPane;
    private VBox gameList;
    private List<GameView> gameViews = new ArrayList<>();
    ImageMenuButton reloadButton, openFolderButton;

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        back = new TextMenuButton("RETOUR", Font.font(35), 150, 35);
        back.setTranslateX(20);
        back.setTranslateY(545);
        back.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(new MainMenuPanel()));

        this.initGameList();
        this.initButtons();

        this.root.getChildren().addAll(back);
    }

    private void initGameList() {

        scrollPane = new ScrollPane();
        scrollPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.getStylesheets().add(this.getClass().getResource("scrollpane.css").toExternalForm());
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        gameList = new VBox();
        gameList.setPrefWidth(getWidth() * 0.6);
        gameList.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        gameList.setStyle("-fx-background-color: transparent");

        for (Game game : GameCore.getGames()) {

            GameView view = GameView.gameToView(game);
            view.setOnMouseClicked(event -> {
                try {
                    game.main().invoke(null);
                } catch (Exception e) {
                    e.printStackTrace();
                    GameCore.getPanelManager().showPanel(new GameLoadError(game));
                }
            });
            gameViews.add(view);
            gameList.getChildren().add(view);
        }

        scrollPane.setContent(gameList);
        this.root.getChildren().add(scrollPane);
    }

    private void initButtons() {

        openFolderButton = new ImageMenuButton(new Image(this.getClass().getClassLoader().getResource("images/folder-icon.png").toExternalForm()), 50 + 10, 50 + 10);
        openFolderButton.setOnMouseClicked(event -> {
            try {
                Desktop.getDesktop().open(new File(GameCore.getModsDirectory().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        openFolderButton.setTranslateX(20);
        openFolderButton.setTranslateY(20);

        reloadButton = new ImageMenuButton(new Image(this.getClass().getClassLoader().getResource("images/reload-icons.png").toExternalForm()), 50 + 10, 50 + 10);
        reloadButton.setOnMouseClicked(event -> this.reloadGameList());
        reloadButton.setTranslateX(20);
        reloadButton.setTranslateY(100);

        this.root.getChildren().addAll(openFolderButton, reloadButton);
    }

    public void reloadGameList() {

        Thread thread = new Thread(() -> {

            AtomicReference<ReloadingPopup> popup = new AtomicReference<>();
            Platform.runLater(() -> popup.set(new ReloadingPopup()));

            GameCore.setGames(GameDetection.getGames(GameCore.getModsDirectory().toPath()));

            Platform.runLater(() -> popup.get().close());
            Platform.runLater(() -> GameCore.getPanelManager().showPanel(new GamesListPanel()));
        });
        thread.start();
    }

    @Override
    public void doSize() {

        super.doSize();

        back.setSize(getWidth() * 0.17, getHeight() * 0.066, Font.font(getHeight() * 0.045));
        back.setTranslateX(getWidth() * 0.02);
        back.setTranslateY(getHeight() * 0.85);

        this.doGameListSize();
    }

    private void doGameListSize() {

        scrollPane.setPrefWidth(getWidth() * 0.6);
        scrollPane.setPrefHeight(getHeight() - (getHeight() * 0.15));
        scrollPane.setTranslateX((getWidth() - scrollPane.getPrefWidth()) / 2);
        scrollPane.setTranslateY(getHeight() * 0.05);

        gameList.setPrefWidth(getWidth() * 0.6);

        for (GameView gameView : this.gameViews) {
            gameView.doSize();
        }
    }

    private void doButtonsSize() {

    }

    @Override
    public String getName() {

        return "Game List Panel";
    }
}
