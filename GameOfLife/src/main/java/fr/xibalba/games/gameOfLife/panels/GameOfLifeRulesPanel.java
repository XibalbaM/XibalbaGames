package fr.xibalba.games.gameOfLife.panels;

import fr.xibalba.games.gameOfLife.entities.GameOfLifeRules;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.main.panels.AMenuPanel;
import fr.xibalba.games.ui.PanelManager;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOfLifeRulesPanel extends AMenuPanel {

    private GameOfLifePanel oldPanel;
    private VBox survive, born;
    private Label surviveIf, bornIf;
    private CheckBox surviveIf0, surviveIf1, surviveIf2, surviveIf3, surviveIf4, surviveIf5, surviveIf6, surviveIf7, surviveIf8,
            bornIf0, bornIf1, bornIf2, bornIf3, bornIf4, bornIf5, bornIf6, bornIf7, bornIf8;

    public GameOfLifeRulesPanel(GameOfLifePanel oldPanel) {

        this.oldPanel = oldPanel;
    }

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        this.initSurvive();
        this.initBorn();

        TextMenuButton done = new TextMenuButton("Valider", Font.font(40), 150, 35);
        done.setOnMouseClicked(event -> {
            this.oldPanel.setRules(new GameOfLifeRules(surviveIf0.isSelected(), surviveIf1.isSelected(), surviveIf2.isSelected(), surviveIf3.isSelected(), surviveIf4.isSelected(), surviveIf5.isSelected(), surviveIf6.isSelected(), surviveIf7.isSelected(), surviveIf8.isSelected(),
                    bornIf0.isSelected(), bornIf1.isSelected(), bornIf2.isSelected(), bornIf3.isSelected(), bornIf4.isSelected(), bornIf5.isSelected(), bornIf6.isSelected(), bornIf7.isSelected(), bornIf8.isSelected()));
            GameCore.getPanelManager().showPanel(this.oldPanel, false);
        });
        done.setTranslateX(350);

        this.layout.getChildren().add(done);

    }

    private void initSurvive() {

        this.survive = new VBox();
        this.survive.setSpacing(10);

        this.surviveIf = new Label("Nombre de voisin pour SURVIVRE");
        this.survive.getChildren().add(this.surviveIf);

        this.surviveIf0 = new CheckBox("0");
        this.surviveIf0.setSelected(oldPanel.getRules().surviveIf0());
        this.survive.getChildren().add(surviveIf0);

        this.surviveIf1 = new CheckBox("1");
        this.surviveIf1.setSelected(oldPanel.getRules().surviveIf1());
        this.survive.getChildren().add(surviveIf1);

        this.surviveIf2 = new CheckBox("2");
        this.surviveIf2.setSelected(oldPanel.getRules().surviveIf2());
        this.survive.getChildren().add(surviveIf2);

        this.surviveIf3 = new CheckBox("3");
        this.surviveIf3.setSelected(oldPanel.getRules().surviveIf3());
        this.survive.getChildren().add(surviveIf3);

        this.surviveIf4 = new CheckBox("4");
        this.surviveIf4.setSelected(oldPanel.getRules().surviveIf4());
        this.survive.getChildren().add(surviveIf4);

        this.surviveIf5 = new CheckBox("5");
        this.surviveIf5.setSelected(oldPanel.getRules().surviveIf5());
        this.survive.getChildren().add(surviveIf5);

        this.surviveIf6 = new CheckBox("6");
        this.surviveIf6.setSelected(oldPanel.getRules().surviveIf6());
        this.survive.getChildren().add(surviveIf6);

        this.surviveIf7 = new CheckBox("7");
        this.surviveIf7.setSelected(oldPanel.getRules().surviveIf7());
        this.survive.getChildren().add(surviveIf7);

        this.surviveIf8 = new CheckBox("8");
        this.surviveIf8.setSelected(oldPanel.getRules().surviveIf8());
        this.survive.getChildren().add(surviveIf8);

        this.survive.setTranslateX(GameCore.getWidth() / 2 + 100);
        this.survive.getChildren().forEach(node -> {
            if (node instanceof Labeled)
                ((Labeled) node).setTextFill(Color.CYAN);
        });

        this.layout.getChildren().add(survive);
    }

    private void initBorn() {

        this.born = new VBox();
        this.born.setSpacing(10);

        this.bornIf = new Label("Nombre de voisin pour NAITRE");
        this.born.getChildren().add(this.bornIf);

        this.bornIf0 = new CheckBox("0");
        this.bornIf0.setSelected(oldPanel.getRules().bornIf0());
        this.born.getChildren().add(bornIf0);

        this.bornIf1 = new CheckBox("1");
        this.bornIf1.setSelected(oldPanel.getRules().bornIf1());
        this.born.getChildren().add(bornIf1);

        this.bornIf2 = new CheckBox("2");
        this.bornIf2.setSelected(oldPanel.getRules().bornIf2());
        this.born.getChildren().add(bornIf2);

        this.bornIf3 = new CheckBox("3");
        this.bornIf3.setSelected(oldPanel.getRules().bornIf3());
        this.born.getChildren().add(bornIf3);

        this.bornIf4 = new CheckBox("4");
        this.bornIf4.setSelected(oldPanel.getRules().bornIf4());
        this.born.getChildren().add(bornIf4);

        this.bornIf5 = new CheckBox("5");
        this.bornIf5.setSelected(oldPanel.getRules().bornIf5());
        this.born.getChildren().add(bornIf5);

        this.bornIf6 = new CheckBox("6");
        this.bornIf6.setSelected(oldPanel.getRules().bornIf6());
        this.born.getChildren().add(bornIf6);

        this.bornIf7 = new CheckBox("7");
        this.bornIf7.setSelected(oldPanel.getRules().bornIf7());
        this.born.getChildren().add(bornIf7);

        this.bornIf8 = new CheckBox("8");
        this.bornIf8.setSelected(oldPanel.getRules().bornIf8());
        this.born.getChildren().add(bornIf8);

        this.born.setTranslateX(100);
        this.born.getChildren().forEach(node -> {
            if (node instanceof Labeled)
                ((Labeled) node).setTextFill(Color.CYAN);
        });

        this.layout.getChildren().add(born);
    }

    @Override
    public void onShow() {

        super.onShow();
    }
}
