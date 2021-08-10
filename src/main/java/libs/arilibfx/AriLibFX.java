package libs.arilibfx;

import libs.arilibfx.utils.AriLogger;

/**
 * Created by Arinonia on 05/03/2020 inside the package - libs.arilibfx
 */

public class AriLibFX {

    private static String programName;
    private static String resourcePath;
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

    public static void setProgramName(String programName) { AriLibFX.programName = programName; }

    public static String getProgramName() {
        if (programName == null)
            return "";
        return programName;
    }
    public static void setResourcePath(String resourcePath) {
        AriLibFX.resourcePath = resourcePath.endsWith("/") ? resourcePath.substring(0, resourcePath.length() - 1) : resourcePath;
    }
    public static String getResourcePath() {return resourcePath;}
}