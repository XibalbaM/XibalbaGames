package fr.xibalba.games.main.entities;

import javafx.scene.image.Image;

import java.lang.reflect.Method;

public record Game(Image icon, String name, String description, Method main) {}