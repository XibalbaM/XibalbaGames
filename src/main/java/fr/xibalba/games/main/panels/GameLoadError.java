package fr.xibalba.games.main.panels;

import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.entities.Game;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.ui.PanelManager;
import fr.xibalba.games.ui.panel.Panel;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameLoadError extends AMenuPanel {

    private final Game game;
    private final Panel oldPanel;

    public GameLoadError(Game game, Panel lastPanel) {

        this.oldPanel = lastPanel;
        this.game = game;
    }

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        Text text = new Text("FAILED TO LOAD THE GAME:");
        text.setFont(new Font(60));
        text.setTextOrigin(VPos.CENTER);
        text.setX(500 - (text.getLayoutBounds().getWidth() / 2));
        text.setY(200);
        text.setFill(Color.DARKRED);

        Text gameName = new Text(this.game.name());
        gameName.setFont(new Font(70));
        gameName.setTextOrigin(VPos.CENTER);
        gameName.setX(500 - (gameName.getLayoutBounds().getWidth() / 2));
        gameName.setY(300);
        gameName.setFill(Color.DARKRED);

        TextMenuButton back = new TextMenuButton("BACK", Font.font(40), 150, 35);
        back.setTranslateX(425);
        back.setTranslateY(375);
        back.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(this.oldPanel, false));

        root.getChildren().addAll(text, gameName, back);
    }

    @Override
    public String getName() {

        return "Game Load Error Panel";
    }
}
