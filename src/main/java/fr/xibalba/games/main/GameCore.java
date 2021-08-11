package fr.xibalba.games.main;

import fr.xibalba.games.main.entities.Game;
import fr.xibalba.games.main.panels.MainMenuPanel;
import fr.xibalba.games.ui.PanelManager;
import javafx.stage.Stage;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GameCore {

    private static PanelManager panelManager;
    private static List<Game> games;
    private static Path modsDirectory;
    private static Thread loadGamesThread;

    public void init(Stage stage) {

        panelManager = new PanelManager(this, stage);
        panelManager.init();
        panelManager.showPanel(new MainMenuPanel());

        try {
            String jarPos = "file:" + GameCore.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            System.out.println(jarPos);

            if (jarPos.endsWith(".jar")) {
                modsDirectory = Path.of(new URI(jarPos.substring(0, jarPos.length() - "Games.jar".length()) + "mods/"));
            } else if (jarPos.endsWith("/")) {
                modsDirectory = Path.of(new URI(jarPos + "mods/"));
            }
            System.out.println(modsDirectory.toString());

            if (!(Files.exists(modsDirectory) && Files.isDirectory(modsDirectory))) {
                Files.createDirectory(modsDirectory);
            }

            loadGamesThread = new Thread(() -> {

                games = GameDetection.getGames(modsDirectory);
                games.add(new Game(null, "", "", null));
                games.add(new Game(null, "", "", null));
                games.add(new Game(null, "", "", null));
                games.add(new Game(null, "", "", null));
                games.add(new Game(null, "", "", null));
                games.add(new Game(null, "", "", null));
                System.out.println(games.size());
            }, "loadGamesThread");
            loadGamesThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopApp() {

        System.exit(0);
    }

    public static PanelManager getPanelManager() {

        return panelManager;
    }

    public static List<Game> getGames() {

        return games;
    }

    public static double getWidth() {

        return getPanelManager().getStage().getWidth();
    }

    public static double getHeight() {

        return getPanelManager().getStage().getHeight();
    }
}