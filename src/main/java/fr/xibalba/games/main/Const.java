package fr.xibalba.games.main;

import javafx.scene.image.Image;

public class Const {

    public static final String TITLE = "Xibalba's Games";
    public static final String AXIUM_LOGO_URL = "https://www.dropbox.com/s/brq8gu0ydw40272/__6_-removebg-preview.png?dl=1";
    public static final Image AXIUM_LOGO = new Image(AXIUM_LOGO_URL);
    public static final String BACKGROUND_URL = "/images/background.PNG";
    public static final String MENU_BACKGROUND_URL = "/images/background_menu.PNG";
    public static final Image BACKGROUND = new Image(Const.class.getResource(BACKGROUND_URL).toExternalForm());
    public static final Image MENU_BACKGROUND = new Image(Const.class.getResource(MENU_BACKGROUND_URL).toExternalForm());
    public static final Object NULL = null;
}
