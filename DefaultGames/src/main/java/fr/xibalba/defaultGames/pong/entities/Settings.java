package fr.xibalba.defaultGames.pong.entities;

import javafx.scene.paint.Color;

public record Settings(boolean solo, int botLevel, Color colorJ1, Color colorJ2, int platformsHeight, String nameJ1, String nameJ2) {

    private static Settings lastSettings;

    public Settings {

        lastSettings = this;
    }

    @Override
    public boolean solo() {

        return solo;
    }

    @Override
    public int botLevel() {

        return botLevel;
    }

    @Override
    public Color colorJ1() {

        return colorJ1;
    }

    @Override
    public Color colorJ2() {

        return colorJ2;
    }

    @Override
    public int platformsHeight() {

        return platformsHeight;
    }

    public static Settings getLastSettings() {

        return lastSettings;
    }

    public static void setLastSettings(Settings lastSettings) {

        Settings.lastSettings = lastSettings;
    }
}