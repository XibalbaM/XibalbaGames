package fr.xibalba.games.main.panels;

import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.entities.Game;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.ui.PanelManager;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static fr.xibalba.games.main.GameCore.getHeight;
import static fr.xibalba.games.main.GameCore.getWidth;

public class GameLoadError extends AMenuPanel {

    private final Game game;
    private Text text, gameName;
    private TextMenuButton back;

    public GameLoadError(Game game) {

        this.game = game;
    }

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        text = new Text("FAILED TO LOAD THE GAME:");
        text.setTextOrigin(VPos.CENTER);
        text.setFill(Color.DARKRED);

        gameName = new Text(this.game.name());
        gameName.setFont(new Font(70));
        gameName.setTextOrigin(VPos.CENTER);
        gameName.setX(500 - (gameName.getLayoutBounds().getWidth() / 2));
        gameName.setY(300);
        gameName.setFill(Color.DARKRED);

        back = new TextMenuButton("BACK", Font.font(40), 150, 35);
        back.setTranslateX(425);
        back.setTranslateY(375);
        back.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(new GamesListPanel()));

        root.getChildren().addAll(text, gameName, back);
    }

    @Override
    public void doSize() {

        super.doSize();

        text.setFont(new Font(getWidth() * 0.06));
        text.setX((getWidth() - text.getLayoutBounds().getWidth()) / 2);
        text.setY(getHeight() * 0.33);

        gameName.setFont(new Font(getWidth() * 0.07));
        gameName.setX(getWidth() * 0.425);
        gameName.setY(getHeight() * 0.5);

        back.setSize(getWidth() * 0.15, getHeight() * 0.058, Font.font(getHeight() * 0.06));
        back.setTranslateX(getWidth() * 0.425);
        back.setTranslateY(getHeight() * 0.625);
    }

    @Override
    public String getName() {

        return "Game Load Error Panel";
    }
}
