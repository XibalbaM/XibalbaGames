package fr.xibalba.games.ui;

import fr.xibalba.games.main.Const;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.panels.TopPanel;
import fr.xibalba.games.ui.panel.IPanel;
import fr.xibalba.games.ui.panel.Panel;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import libs.arilibfx.ui.utils.ResizeHelper;

public class PanelManager {

    private final GameCore gameCore;
    private final Stage stage;
    private Scene scene;
    private GridPane layout = new GridPane(), centerPanel = new GridPane();
    private TopPanel topPanel = new TopPanel();
    private IPanel currentPanel;

    public PanelManager(GameCore gameCore, Stage stage) {

        this.gameCore = gameCore;
        this.stage = stage;
    }

    public void init() {

        this.currentPanel = new Panel();
        this.stage.setTitle(Const.TITLE);
        this.stage.setMinWidth(500);
        this.stage.setWidth(1000);
        this.stage.setMinHeight(300);
        this.stage.setHeight(630);
        this.stage.centerOnScreen();

        this.scene = new Scene(this.layout, 1000, 630);
        this.stage.setScene(this.scene);
        ResizeHelper.addResizeListener(this.stage, 500, 300, Integer.MAX_VALUE, Integer.MAX_VALUE);

        RowConstraints topPanelConstraints = new RowConstraints();
        topPanelConstraints.setValignment(VPos.TOP);
        topPanelConstraints.setMinHeight(30);
        topPanelConstraints.setMaxHeight(30);
        this.layout.getRowConstraints().addAll(topPanelConstraints, new RowConstraints());
        this.layout.add(this.topPanel.getLayout(), 0, 0);
        this.topPanel.init(this);
        this.layout.add(this.centerPanel, 0, 1);
        GridPane.setVgrow(this.centerPanel, Priority.ALWAYS);
        GridPane.setHgrow(this.centerPanel, Priority.ALWAYS);

        this.stage.widthProperty().addListener((observable, oldValue, newValue) -> this.onResize());
        this.stage.heightProperty().addListener((observable, oldValue, newValue) -> this.onResize());

        this.stage.setOnCloseRequest(windowEvent -> {
            this.currentPanel.onHide();
            Platform.exit();
        });

        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.show();
    }

    public void onResize() {

        this.currentPanel.doSize();
    }

    public void showPanel(IPanel panel) {

        this.showPanel(panel, true);
    }

    public void showPanel(IPanel panel, boolean init) {

        this.currentPanel.onHide();
        this.centerPanel.getChildren().clear();
        this.centerPanel.getChildren().add(panel.getLayout());
        this.currentPanel = panel;
        if (init)
            panel.init(this);
        this.currentPanel.doSize();
        panel.onShow();
        if (!init)
            panel.onRefresh();
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
        this.centerPanel.getChildren().clear();
        this.centerPanel.getChildren().add(panel.getLayout());
        this.currentPanel = panel;
        this.currentPanel.init(this);
        this.currentPanel.doSize();
        this.currentPanel.onRefresh();
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

    public GridPane getCenterPanel() {

        return centerPanel;
    }

    public TopPanel getTopPanel() {

        return topPanel;
    }
}
