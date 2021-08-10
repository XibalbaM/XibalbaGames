package fr.xibalba.games.main;

import javafx.application.Application;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("javafx.application.Application");
            Application.launch(FxApplication.class, args);
        } catch (ClassNotFoundException e) {
            System.out.println("JavaFx not found");
            JOptionPane.showMessageDialog(null, "Une erreur avec java à été détectée.\n" + e.getMessage() + " Not found", "Erreur Java", JOptionPane.ERROR_MESSAGE);
        }
    }
}
