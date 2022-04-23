package fr.xibalba.games.game2048.ui;

import fr.xibalba.games.game2048.ui.items.ColorWithPicker;
import fr.xibalba.games.main.entities.fx.TextMenuButton;
import fr.xibalba.games.main.panels.AMenuPanel;

import java.util.ArrayList;
import java.util.List;

public class MenuPanel extends AMenuPanel {

    /*
    4 things: dificulty, goal, colors and play button
     */
    private TextMenuButton playButton;
    private List<ColorWithPicker> colorList = new ArrayList<>();

}