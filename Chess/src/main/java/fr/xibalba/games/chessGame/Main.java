package fr.xibalba.games.chessGame;

import fr.xibalba.games.chessGame.panels.MenuPanel;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.annotations.Game;

public class Main {

    public static final String GAME_ICON = Main.class.getResource("/images/icon.png").toExternalForm();

    @Game(name = "Échecs version IA", description = "Le jeu d'échecs contre une IA")
    public static void main() {

        GameCore.getPanelManager().showPanel(new MenuPanel());
    }
}