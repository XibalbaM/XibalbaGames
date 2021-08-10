package fr.xibalba.games.main;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Const {

    public static final String TITLE = "Xibalba's Games";
    public static final String AXIUM_LOGO_URL = "https://www.dropbox.com/s/brq8gu0ydw40272/__6_-removebg-preview.png?dl=1";
    public static final Image AXIUM_LOGO = new Image(AXIUM_LOGO_URL);
    public static final String BACKGROUND_URL = "images/background.PNG";
    public static final String MENU_BACKGROUND_URL = "images/background_menu.PNG";
    public static final Image BACKGROUND = new Image(Const.class.getClassLoader().getResource(BACKGROUND_URL).toExternalForm());
    public static final Image MENU_BACKGROUND = new Image(Const.class.getClassLoader().getResource(MENU_BACKGROUND_URL).toExternalForm());
    public static final BackgroundSize RESPONSIVE_BACKGROUND_SIZE = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true);
    public static final Object NULL = null;

    public static BackgroundImage responsiveBackgroundImage(Image image) {

        return new BackgroundImage(image, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, RESPONSIVE_BACKGROUND_SIZE);
    }
}
