package fr.xibalba.utils.javaFX;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DisplayUtils {

    public static double computeTextWidth(Font font, String text, double wrappingWidth) {

        Text helper = new Text();
        helper.setFont(font);
        helper.setText(text);
        // Note that the wrapping width needs to be set to zero before
        // getting the text's real preferred width.
        helper.setWrappingWidth(0);
        helper.setLineSpacing(0);
        double w = Math.min(helper.prefWidth(-1), wrappingWidth);
        helper.setWrappingWidth((int) Math.ceil(w));
        double textWidth = Math.ceil(helper.getLayoutBounds().getWidth());
        return textWidth;
    }
}
