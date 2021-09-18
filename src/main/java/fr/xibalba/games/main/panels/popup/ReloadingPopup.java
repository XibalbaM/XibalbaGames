package fr.xibalba.games.main.panels.popup;

import fr.xibalba.games.ui.panel.PopupPanel;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ReloadingPopup extends PopupPanel {

    public ReloadingPopup() {

        super(500, 300);

        this.centerPanel.setBackground(new Background(new BackgroundFill(Color.web("#181818"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label label = new Label("Reloading game list !");
        label.setTranslateY(-25);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setTranslateY(25);

        this.centerPanel.getChildren().addAll(label, progressIndicator);
    }
}
