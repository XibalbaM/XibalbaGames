package fr.xibalba.games.main.panels;

import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.entities.Game;
import fr.xibalba.games.main.entities.fx.GameView;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.ui.PanelManager;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

import static fr.xibalba.games.main.GameCore.getHeight;
import static fr.xibalba.games.main.GameCore.getWidth;

public class GamesListPanel extends AMenuPanel {

    private TextMenuButton back;
    private ScrollPane scrollPane;
    private VBox gameList;
    private List<GameView> gameViews = new ArrayList<>();

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        back = new TextMenuButton("BACK", Font.font(35), 150, 35);
        back.setTranslateX(20);
        back.setTranslateY(545);
        back.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(new MainMenuPanel()));

        /*ImageMenuButton test = new ImageMenuButton(Const.AXIUM_LOGO, 30 + 10, 30 + 10);
        test.setTranslateX(20);
        test.setTranslateY(20);
        test.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(this.oldPanel, false));*/

        this.initGameList();

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
            view.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(new GameLoadError(game)));
            gameViews.add(view);
            gameList.getChildren().add(view);
        }

        scrollPane.setContent(gameList);
        this.root.getChildren().add(scrollPane);
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

    @Override
    public String getName() {

        return "Game List Panel";
    }
}
