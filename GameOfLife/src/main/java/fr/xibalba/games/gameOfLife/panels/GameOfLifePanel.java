package fr.xibalba.games.gameOfLife.panels;

import fr.xibalba.games.gameOfLife.entities.CalculatorThread;
import fr.xibalba.games.gameOfLife.entities.GameOfLifeRules;
import fr.xibalba.games.gameOfLife.entities.fx.Cell;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.main.panels.GamesListPanel;
import fr.xibalba.games.ui.PanelManager;
import fr.xibalba.games.ui.panel.Panel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class GameOfLifePanel extends Panel {

    private int sizeX, sizeY;
    private ScrollPane gamePane;
    private GridPane game;
    private StackPane menu;
    private Cell[][] cells;
    private GameOfLifeRules rules = new GameOfLifeRules(false, false, true, true, false, false, false, false, false,
            false, false, false, true, false, false, false, false, false);
    private BooleanProperty isRunning = new SimpleBooleanProperty(false);
    private static boolean rightClick = false;
    private Slider speedSlider;
    private CalculatorThread calculatorThread;
    private IntegerProperty currentCycle = new SimpleIntegerProperty(0);

    public Timeline timeline;
    public int[][] cellsInfos;

    public GameOfLifePanel(int sizeX, int sizeY) {

        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);

        initGlobal();
        initMenu();
        initGame();

        panelManager.getScene().setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                rightClick = !rightClick;
            }
        });
    }

    private void initGlobal() {

        game = new GridPane();
        gamePane = new ScrollPane();
        gamePane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gamePane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gamePane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        menu = new StackPane();

        gamePane.setContent(game);

        RowConstraints bottConstraints = new RowConstraints();
        bottConstraints.setValignment(VPos.BOTTOM);
        bottConstraints.setMinHeight(100);
        bottConstraints.setMaxHeight(100);
        this.layout.getRowConstraints().addAll(new RowConstraints(), bottConstraints);
        this.layout.add(gamePane, 0, 0);
        this.layout.add(menu, 0, 1);
        GridPane.setValignment(menu, VPos.BOTTOM);
        GridPane.setVgrow(menu, Priority.ALWAYS);
        GridPane.setHgrow(menu, Priority.ALWAYS);

        menu.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        game.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void initGame() {

        this.game.setVgap(3);
        this.game.setHgap(3);

        this.cells = new Cell[this.sizeX][this.sizeY];
        this.cellsInfos = new int[this.sizeX][this.sizeY];

        for (int i = 0; i < this.sizeX; i++) {
            for (int j = 0; j < this.sizeY; j++) {
                this.cells[i][j] = new Cell(i, j);
                this.game.add(this.cells[i][j], i, j);
                this.cellsInfos[i][j] = -1;
            }
        }

        this.doTimelineWithSpeed(true);
        this.speedSlider.valueProperty().addListener(observable -> this.doTimelineWithSpeed(false));
    }

    private void initMenu() {

        TextMenuButton rulesButton = new TextMenuButton("Régles", 125, 40);
        rulesButton.setOnMouseClicked(event -> GameCore.getPanelManager().showPanel(new GameOfLifeRulesPanel(this)));
        rulesButton.setTranslateX(-225);

        TextMenuButton playButton = new TextMenuButton("Lancer", 200, 75);
        GridPane.setValignment(playButton, VPos.CENTER);
        GridPane.setHalignment(playButton, HPos.CENTER);
        isRunning.addListener((observableValue, aBoolean, t1) -> {
            if (aBoolean) {
                playButton.setText("Lancer");
                System.out.println("Lancer");
            } else {
                playButton.setText("Arreter");
                System.out.println("Arreter");
            }
        });
        playButton.setOnMouseClicked(event -> {
            if (!isRunning.getValue()) {
                isRunning.setValue(true);
                currentCycle.set(0);
            } else {
                isRunning.setValue(false);
            }
        });

        TextMenuButton randomizeButton = new TextMenuButton("Randomiser", 125, 40);
        randomizeButton.setOnMouseClicked(event -> this.panelManager.showPanel(new RandomizePanel(this)));
        randomizeButton.setTranslateX(-375);
        randomizeButton.setTranslateY(-25);

        TextMenuButton clearButton = new TextMenuButton("Vider", 125, 40);
        clearButton.setOnMouseClicked(event -> this.setAll(false));
        clearButton.setTranslateX(375);
        clearButton.setTranslateY(-25);

        TextMenuButton fillButton = new TextMenuButton("Remplir", 125, 40);
        fillButton.setOnMouseClicked(event -> this.setAll(true));
        fillButton.setTranslateX(375);
        fillButton.setTranslateY(25);

        TextMenuButton exitButton = new TextMenuButton("Quitter", 125, 40);
        exitButton.setOnMouseClicked(event -> this.panelManager.showPanel(new GamesListPanel()));
        exitButton.setTranslateX(-375);
        exitButton.setTranslateY(25);

        this.speedSlider = new Slider();
        speedSlider.setMaxSize(125, 50);
        speedSlider.setMin(10);
        speedSlider.setMax(1000);
        speedSlider.setOrientation(Orientation.HORIZONTAL);
        speedSlider.setShowTickLabels(true);
        speedSlider.setMajorTickUnit(250);
        speedSlider.setMinorTickCount(50);
        speedSlider.setShowTickMarks(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.setLabelFormatter(new StringConverter<Double>() {

            @Override
            public String toString(Double object) {

                return String.valueOf(1000 - object.intValue() + 10);
            }

            @Override
            public Double fromString(String s) {

                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        speedSlider.setTranslateX(225);
        speedSlider.setTranslateY(-10);

        Label speedValue = new Label("Vitesse : " + (1000 - (int) speedSlider.getValue() + 10) + " ms");
        speedSlider.valueProperty().addListener(ov -> speedValue.setText("Vitesse : " + (1000 - (int) speedSlider.getValue() + 10) + " ms"));
        speedValue.setTranslateX(225);
        speedValue.setTranslateY(-35);

        Label cycleValue = new Label("Cicle : " + currentCycle.getValue());
        currentCycle.addListener(observable -> cycleValue.setText("Cicle : " + currentCycle.getValue()));
        cycleValue.setTranslateX(225);
        cycleValue.setTranslateY(35);

        this.menu.getChildren().addAll(rulesButton, playButton, randomizeButton, clearButton, fillButton, exitButton, speedSlider, speedValue, cycleValue);
    }

    private void doTimelineWithSpeed(boolean init) {

        if (init) {

            calculatorThread = new CalculatorThread(cellsInfos, cells, rules, this);
            calculatorThread.start();

            this.timeline = new Timeline();
            this.timeline.setCycleCount(Timeline.INDEFINITE);
        } else {

            this.timeline.stop();
        }

        KeyFrame key = new KeyFrame(Duration.millis(1000 - speedSlider.getValue() + 10), event -> {

            System.out.println("Refresh");
            for (int i = 0; i < this.sizeX; i++) {
                for (int j = 0; j < this.sizeY; j++) {

                    if (this.cellsInfos[i][j] == 1 && !this.cells[i][j].isAlive()) {
                        this.cells[i][j].setAlive(true);
                    }
                    if (this.cellsInfos[i][j] == 0 && this.cells[i][j].isAlive()) {
                        this.cells[i][j].setAlive(false);
                    }

                    this.cellsInfos[i][j] = -1;
                }
            }

            if (isRunning.getValue()) {
                this.currentCycle.set(this.currentCycle.get() + 1);
            }

            synchronized (calculatorThread) {
                calculatorThread.notify();
            }
        });

        this.timeline.getKeyFrames().setAll(key);
        this.timeline.play();
    }

    @Override
    public void onShow() {

        super.onShow();

        GameCore.getPanelManager().getStage().setWidth(900);
        GameCore.getPanelManager().getStage().setHeight(655);

        GameCore.getPanelManager().getStage().setResizable(false);
    }

    @Override
    public void onHide() {

        super.onHide();

        GameCore.getPanelManager().getStage().setResizable(true);

        calculatorThread.interrupt();
        timeline.stop();
    }

    @Override
    public void onRefresh() {

        super.onRefresh();

        calculatorThread = new CalculatorThread(cellsInfos, cells, rules, this);
        calculatorThread.start();
        timeline.play();
    }

    public void setAll(boolean alive) {

        for (Cell[] cells1 : this.cells) {
            for (Cell cell : cells1) {
                cell.setAlive(alive);
            }
        }
    }

    public void setRules(GameOfLifeRules rules) {

        this.rules = rules;
    }

    public GameOfLifeRules getRules() {

        return rules;
    }

    public boolean isRunning() {

        return isRunning.get();
    }

    public void setRunning(boolean isRunning) {

        this.isRunning.set(isRunning);
    }

    public static boolean isRightClick() {

        return rightClick;
    }

    public Cell[][] getCells() {

        return cells;
    }

    public int getSizeX() {

        return sizeX;
    }

    public int getSizeY() {

        return sizeY;
    }
}
