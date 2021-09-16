package fr.xibalba.games.gameOfLife;

import fr.xibalba.games.gameOfLife.panels.GameOfLifePanel;
import fr.xibalba.games.main.GameCore;
import fr.xibalba.games.main.annotations.Game;

public class Main {

    @Game(name = "Jeu de la Vie", description = "Le célebre Game Of Life !")
    public static void start() {

        System.out.println("Starting Game Of Life");

        GameCore.getPanelManager().showPanel(new GameOfLifePanel(60, 35));
    }
}