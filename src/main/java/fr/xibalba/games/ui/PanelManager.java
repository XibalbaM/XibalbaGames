package fr.xibalba.games.ui;

import com.guigarage.responsive.ResponsiveHandler;
import fr.xibalba.games.main.Const;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.ui.panel.IPanel;
import fr.xibalba.games.ui.panel.Panel;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PanelManager {

    private final GameCore gameCore;
    private final Stage stage;
    private Scene scene;
    private GridPane layout;
    private IPanel currentPanel;

    public PanelManager(GameCore gameCore, Stage stage) {

        this.gameCore = gameCore;
        this.stage = stage;
    }

    public void init() {

        this.currentPanel = new Panel();
        this.stage.setTitle(Const.TITLE);
        this.stage.centerOnScreen();

        this.layout = new GridPane();

        this.scene = new Scene(this.layout, 1000, 600);
        this.stage.setScene(this.scene);
        ResponsiveHandler.addResponsiveToWindow(this.stage);

        this.stage.setMinWidth(500);
        this.stage.setWidth(1000);
        this.stage.setMinHeight(300);
        this.stage.setHeight(600);

        this.stage.widthProperty().addListener((observable, oldValue, newValue) -> this.onResize());
        this.stage.heightProperty().addListener((observable, oldValue, newValue) -> this.onResize());

        this.stage.show();
    }

    private void onResize() {

        this.currentPanel.doSize();
    }

    public void showPanel(IPanel panel) {

        this.showPanel(panel, true);
    }

    public void showPanel(IPanel panel, boolean init) {

        this.currentPanel.onHide();
        this.layout.getChildren().clear();
        this.layout.getChildren().add(panel.getLayout());
        this.currentPanel = panel;
        if (init)
            panel.init(this);
        currentPanel.doSize();
        panel.onShow();
    }

    public void updatePanel() {

        IPanel panel = null;
        try {
            panel = this.currentPanel.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.layout.getChildren().clear();
        this.layout.getChildren().add(panel.getLayout());
        this.currentPanel = panel;
        currentPanel.init(this);
        currentPanel.doSize();
        currentPanel.onRefresh();
    }

    public Stage getStage() {
        return stage;
    }

    public GameCore getGameCore() {
        return gameCore;
    }

    public IPanel getCurrentPanel() {
        return currentPanel;
    }

    public Scene getScene() {

        return scene;
    }

    public GridPane getLayout() {

        return layout;
    }
}
