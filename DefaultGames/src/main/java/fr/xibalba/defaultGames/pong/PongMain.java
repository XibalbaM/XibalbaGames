package fr.xibalba.defaultGames.pong;

import fr.xibalba.games.main.Const;
import fr.xibalba.games.main.annotations.Game;

public class PongMain {

    @Game(name = "Test", description = "A test game", iconURL = Const.AXIUM_LOGO_URL)
    public static void start() {

        System.out.println("hello");
    }

    @Game(name = "sbradaradjan", description = "sbradaradjansbradaradjan")
    public static void sbradaradjan() {

        System.out.println("sbradaradjan");
    }
}
