package libs.arilibfx;

import libs.arilibfx.utils.AriLogger;

/**
 * Created by Arinonia on 05/03/2020 inside the package - libs.arilibfx
 */

public class AriLibFX {

    public static final AriLogger LOGGER = new AriLogger("AriLibFX");
    public static String setResponsiveBackground(String url) {

        return "-fx-background-image: url('" + url + "');"
                + "-fx-backgound-repeat: skretch;"
                + "-fx-backgound-position: center center;"
                + "-fx-background-size: cover;";
    }

    public static String setResponsiveBackgroundColor(String colorName) {

        return "-fx-background-color: " + colorName + ";"
                + "-fx-backgound-repeat: skretch;"
                + "-fx-backgound-position: center center;"
                + "-fx-background-size: cover;";
    }
}