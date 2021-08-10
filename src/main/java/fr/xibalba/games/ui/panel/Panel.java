package fr.xibalba.games.ui.panel;

import fr.xibalba.games.main.Const;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.ui.PanelManager;
import javafx.animation.FadeTransition;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

public class Panel implements IPanel {

    protected GridPane layout = new GridPane();
    protected PanelManager panelManager;

    @Override
    public void init(PanelManager panelManager) {

        this.panelManager = panelManager;
        GridPane.setHgrow(layout, Priority.ALWAYS);
        GridPane.setVgrow(layout, Priority.ALWAYS);
    }

    @Override
    public void doSize() {

        layout.setPrefWidth(GameCore.getPanelManager().getStage().getWidth());
        layout.setPrefHeight(GameCore.getPanelManager().getStage().getHeight());
    }

    @Override
    public GridPane getLayout() {

        return this.layout;
    }

    @Override
    public void onShow() {

        GameCore.getPanelManager().getLayout().setBackground(new Background(Const.responsiveBackgroundImage(Const.BACKGROUND)));

        System.out.println("Showing " + getName());
        FadeTransition transition = new FadeTransition(Duration.seconds(1), this.layout);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setAutoReverse(true);
        transition.play();
    }

    @Override
    public void onHide() {

        System.out.println("Hiding " + getName());
    }

    @Override
    public void onRefresh() {

        System.out.println("Refreshing " + getName());
    }

    @Override
    public String getName() {
        return "panel";
    }
}