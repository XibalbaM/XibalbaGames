package fr.xibalba.games.gameOfLife.entities;

public record GameOfLifeRules(boolean surviveIf0, boolean surviveIf1, boolean surviveIf2, boolean surviveIf3,
                              boolean surviveIf4, boolean surviveIf5, boolean surviveIf6, boolean surviveIf7,
                              boolean surviveIf8,
                              boolean bornIf0, boolean bornIf1, boolean bornIf2, boolean bornIf3, boolean bornIf4,
                              boolean bornIf5, boolean bornIf6, boolean bornIf7, boolean bornIf8) {

    public boolean[] getSurviveIf() {

        return new boolean[]{surviveIf0, surviveIf1, surviveIf2, surviveIf3, surviveIf4, surviveIf5, surviveIf6, surviveIf7, surviveIf8};
    }

    public boolean[] getBornIf() {

        return new boolean[]{bornIf0, bornIf1, bornIf2, bornIf3, bornIf4, bornIf5, bornIf6, bornIf7, bornIf8};
    }
}