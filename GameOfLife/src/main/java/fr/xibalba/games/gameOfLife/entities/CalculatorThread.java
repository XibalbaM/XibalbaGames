package fr.xibalba.games.gameOfLife.entities;

import fr.xibalba.games.gameOfLife.entities.fx.Cell;
import fr.xibalba.games.gameOfLife.panels.GameOfLifePanel;

public class CalculatorThread extends Thread {

    private boolean isInGame;
    private int[][] cellsData;
    private Cell[][] cells;
    private GameOfLifeRules rules;
    private GameOfLifePanel panel;

    public CalculatorThread(int[][] cellsData, Cell[][] cells, GameOfLifeRules rules, GameOfLifePanel panel) {

        this.isInGame = true;
        this.cellsData = cellsData;
        this.cells = cells;
        this.rules = rules;
        this.panel = panel;
    }

    @Override
    public synchronized void run() {

        while (isInGame) {

            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                isInGame = false;
                return;
            }

            if (GameOfLifePanel.isRunning()) {

                System.out.println("Calc");

                for (int i = 0; i < panel.getSizeX(); i++) {
                    for (int j = 0; j < panel.getSizeY(); j++) {

                        byte neighbours = 0;

                        if (cells[(i - 1 + panel.getSizeX()) % panel.getSizeX()][(j - 1 + panel.getSizeY()) % panel.getSizeY()].isAlive())
                            neighbours++;

                        if (cells[i][(j - 1 + panel.getSizeY()) % panel.getSizeY()].isAlive())
                            neighbours++;

                        if (cells[(i + 1) % panel.getSizeX()][(j - 1 + panel.getSizeY()) % panel.getSizeY()].isAlive())
                            neighbours++;

                        if (cells[(i + 1) % panel.getSizeX()][j].isAlive())
                            neighbours++;

                        if (cells[(i + 1) % panel.getSizeX()][(j + 1) % panel.getSizeY()].isAlive())
                            neighbours++;

                        if (cells[i][(j + 1) % panel.getSizeY()].isAlive())
                            neighbours++;

                        if (cells[(i - 1 + panel.getSizeX()) % panel.getSizeX()][(j + 1) % panel.getSizeY()].isAlive())
                            neighbours++;

                        if (cells[(i - 1 + panel.getSizeX()) % panel.getSizeX()][j].isAlive())
                            neighbours++;

                        if (cells[i][j].isAlive()) {

                            if (!rules.getSurviveIf()[neighbours])
                                cellsData[i][j] = 0;
                        } else {

                            if (rules.getBornIf()[neighbours]) {
                                cellsData[i][j] = 1;
                            }
                        }
                    }
                }
            }
        }
    }
}
