package fr.xibalba.games.ui.panel;

import fr.xibalba.games.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {

    void init(PanelManager manager);

    void doSize();

    GridPane getLayout();
    void onShow();
    void onRefresh();
    void onHide();
    String getName();
}