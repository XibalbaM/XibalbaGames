package fr.xibalba.games.ui.panel;

import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.panels.popup.TopPopupPanel;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import libs.arilibfx.ui.utils.ResizeHelper;

public class PopupPanel extends Stage {

    protected GridPane layout = new GridPane();
    protected StackPane centerPanel = new StackPane();
    protected TopPopupPanel topPanel;

    public PopupPanel(int width, int height) {

        super();
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(GameCore.getPanelManager().getStage());
        this.initStyle(StageStyle.UNDECORATED);

        RowConstraints topPanelConstraints = new RowConstraints();
        topPanelConstraints.setValignment(VPos.TOP);
        topPanelConstraints.setMinHeight(25);
        topPanelConstraints.setMaxHeight(25);
        this.layout.getRowConstraints().addAll(topPanelConstraints, new RowConstraints());
        this.topPanel = new TopPopupPanel();
        this.layout.add(this.topPanel.getLayout(), 0, 0);
        this.topPanel.init(this);

        this.layout.add(this.centerPanel, 0, 1);
        GridPane.setVgrow(this.centerPanel, Priority.ALWAYS);
        GridPane.setHgrow(this.centerPanel, Priority.ALWAYS);

        Scene dialogScene = new Scene(this.layout, width, height);
        this.setScene(dialogScene);
        ResizeHelper.addResizeListener(this);
        this.show();
    }
}
