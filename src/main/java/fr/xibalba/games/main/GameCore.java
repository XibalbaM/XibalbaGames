package fr.xibalba.games.main;

import fr.xibalba.games.main.entities.Game;
import fr.xibalba.games.main.panels.MainMenuPanel;
import fr.xibalba.games.ui.PanelManager;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class GameCore {

    private static PanelManager panelManager;
    private static List<Game> games;
    private static File modsDirectory;
    private static Thread loadGamesThread;

    public void init(Stage stage) {

        panelManager = new PanelManager(this, stage);
        panelManager.init();
        panelManager.showPanel(new MainMenuPanel());

        try {
            String jarPos = GameCore.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            System.out.println(jarPos);

            this.modsDirectory = new File(new File(jarPos).getParent() + "/mods");
            System.out.println(modsDirectory.toString());

            if (!(modsDirectory.exists() && modsDirectory.isDirectory())) {
                modsDirectory.createNewFile();
            }

            loadGamesThread = new Thread(() -> {

                games = GameDetection.getGames(modsDirectory.toPath());
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

    public static File getModsDirectory() {

        return modsDirectory;
    }
}