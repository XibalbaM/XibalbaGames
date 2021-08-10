package fr.xibalba.games.main.panels;

import fr.xibalba.games.main.Const;
import fr.xibalba.games.ui.PanelManager;
import fr.xibalba.games.ui.panel.Panel;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class AMenuPanel extends Panel {

    protected Pane root = new Pane();

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        Pane background = new Pane();

        background.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        background.setOpacity(0.2);
        background.setMinWidth(panelManager.getStage().getMinWidth());
        background.setMaxWidth(panelManager.getStage().getMaxWidth());
        background.setMinHeight(panelManager.getStage().getMinHeight());
        background.setMaxHeight(panelManager.getStage().getMaxHeight());

        root.getChildren().add(background);

        this.layout.getChildren().add(root);
    }
}
