package fr.xibalba.games.main;

import fr.xibalba.games.main.entities.Game;
import fr.xibalba.games.main.panels.MainMenuPanel;
import fr.xibalba.games.ui.PanelManager;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.File;
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

            this.modsDirectory = new File(jarPos).getParentFile().toPath();
            System.out.println(modsDirectory.toString());

            if (!(Files.exists(modsDirectory) && Files.isDirectory(modsDirectory))) {
                Files.createDirectory(modsDirectory);
            }

            loadGamesThread = new Thread(() -> {

                games = GameDetection.getGames(modsDirectory);
                System.out.println(games.size());
            }, "loadGamesThread");
            loadGamesThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopApp() {

        getPanelManager().getCurrentPanel().onHide();
        Platform.exit();
    }

    public static PanelManager getPanelManager() {

        return panelManager;
    }

    public static List<Game> getGames() {

        return games;
    }

    public static double getWidth() {

        return getPanelManager().getCenterPanel().getWidth();
    }

    public static double getHeight() {

        return getPanelManager().getCenterPanel().getHeight();
    }

    public static Path getModsDirectory() {

        return modsDirectory;
    }
}