package fr.xibalba.games.gameOfLife.panels;

import fr.xibalba.games.gameOfLife.entities.fx.Cell;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.main.panels.AMenuPanel;
import fr.xibalba.games.ui.PanelManager;
import javafx.scene.control.Slider;

import java.util.Random;

public class RandomizePanel extends AMenuPanel {

    private GameOfLifePanel panel;
    private Slider cursor;

    public RandomizePanel(GameOfLifePanel panel) {

        this.panel = panel;
    }

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        TextMenuButton randomizeButton = new TextMenuButton("Randomizer", 150, 75);
        randomizeButton.setTranslateX(375);
        randomizeButton.setTranslateY(400);
        randomizeButton.setOnMouseClicked(event -> this.randomize());

        cursor = new Slider(0, 100, 50);
        cursor.setBlockIncrement(25);
        cursor.setShowTickLabels(true);
        cursor.setScaleX(1.5);
        cursor.setScaleY(1.5);
        cursor.setTranslateX(400);
        cursor.setTranslateY(100);

        this.layout.getChildren().addAll(randomizeButton, cursor);
    }

    private void randomize() {

        Cell[][] cells = this.panel.getCells();

        for (Cell[] cells1 : cells) {
            for (Cell cell : cells1) {

                cell.setAlive(new Random().nextInt(100) < cursor.getValue());
            }
        }

        this.panelManager.showPanel(this.panel, false);
    }
}
