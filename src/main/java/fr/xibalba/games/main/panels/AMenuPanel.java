package fr.xibalba.games.main.panels;

import fr.xibalba.games.main.Const;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.ui.PanelManager;
import fr.xibalba.games.ui.panel.Panel;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public abstract class AMenuPanel extends Panel {

    protected Pane root = new Pane();

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        this.layout.getChildren().add(root);
    }

    @Override
    public void onShow() {

        super.onShow();

        GameCore.getPanelManager().getCenterPanel().setBackground(new Background(Const.responsiveBackgroundImage(Const.MENU_BACKGROUND)));
    }
}
