package fr.xibalba.games.game2048;

import javafx.scene.paint.Color;

public enum Colors {
    C1(null),
    C2(null),
    C4(null),
    C8(null),
    C16(null),
    C32(null),
    C64(null),
    C128(null),
    C256(null),
    C512(null),
    C1024(null),
    C2048(null),
    C4096(null),
    C8192(null),
    C16384(null),
    C32768(null);

    private Color color;

    Colors(Color color) {

        this.color = color == null ? Color.GRAY : color;
    }

    public static void init() {

    }

    public Color getColor() {

        return color;
    }

    public void setColor(Color color) {

        this.color = color;
    }
}
