package fr.xibalba.games.main.panels;

import fr.xibalba.games.main.Const;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.entities.Game;
import fr.xibalba.games.main.entities.fx.GameView;
import fr.xibalba.games.main.entities.fx.ImageMenuButton;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.ui.PanelManager;
import fr.xibalba.games.ui.panel.Panel;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GamesListPanel extends AMenuPanel {

    private final Panel oldPanel;

    public GamesListPanel(Panel lastPanel) {

        this.oldPanel = lastPanel;
    }

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        TextMenuButton back = new TextMenuButton("BACK", Font.font(35), 150, 35);
        back.setTranslateX(20);
        back.setTranslateY(545);
        back.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(this.oldPanel, false));

        ImageMenuButton test = new ImageMenuButton(Const.AXIUM_LOGO, 30 + 10, 30 + 10);
        test.setTranslateX(20);
        test.setTranslateY(20);
        test.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(this.oldPanel, false));

        this.initGameList();

        this.root.getChildren().addAll(back, test);
    }

    private void initGameList() {

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.getStylesheets().add(this.getClass().getResource("scrollpane.css").toExternalForm());
        scrollPane.setMinWidth(600);
        scrollPane.setMaxWidth(600);
        scrollPane.setMinHeight(panelManager.getStage().getMinHeight() - 60);
        scrollPane.setMaxHeight(panelManager.getStage().getMaxHeight() - 60);
        scrollPane.setTranslateX(200);
        scrollPane.setTranslateY(30);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        VBox gameList = new VBox();
        gameList.setMinWidth(600);
        gameList.setMaxWidth(600);
        gameList.setMinWidth(panelManager.getStage().getMinHeight());
        gameList.setMaxWidth(panelManager.getStage().getMinHeight());
        gameList.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        gameList.setStyle("-fx-background-color: transparent");

        for (Game game : GameCore.getGames()) {

            GameView view = GameView.gameToView(game);
            view.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(new GameLoadError(game, this)));
            gameList.getChildren().add(view);
        }

        scrollPane.setContent(gameList);
        this.root.getChildren().add(scrollPane);
    }

    @Override
    public String getName() {

        return "Game List Panel";
    }
}
