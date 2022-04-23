package fr.xibalba.games.main;

import fr.xibalba.games.main.entities.Game;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class GameDetection {

    public static List<Game> getGames(Path directory) {

        List<Game> result = new ArrayList<>();

        for (File jar : getJars(directory)) {
            try {
                for (Class aClass : getAllClassesOfAJar(jar)) {
                    for (Method method : aClass.getMethods()) {
                        if (method.isAnnotationPresent(fr.xibalba.games.main.annotations.Game.class)) {
                            fr.xibalba.games.main.annotations.Game gameMain = method.getAnnotation(fr.xibalba.games.main.annotations.Game.class);
                            Image icon = gameMain.iconURL() == null || gameMain.iconURL().equals("") ? null : new Image(gameMain.iconURL());
                            result.add(new Game(icon, gameMain.name(), gameMain.description(), method));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static List<Class> getAllClassesOfAJar(File jar) {

        List<Class> result = new ArrayList<>();

        try {

            JarFile jarFile = new JarFile(jar);
            Enumeration<JarEntry> e = jarFile.entries();

            URL[] urls = {new URL("jar:file:" + jar.getAbsolutePath() + "!/")};
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if (je.isDirectory() || !(je.getName().endsWith(".class") || je.getName().endsWith(".java"))) {
                    continue;
                }
                // -6 because of .class
                String className =
                        je.getName().substring(0, je.getName().length() - (je.getName().endsWith(".java") ? 5 : 6));
                className = className.replace('/', '.');
                if (className.startsWith("fr")) {
                    result.add(cl.loadClass(className));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            return result;
        }
    }

    public static List<File> getJars(Path directory) {

        List<File> result = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path file : stream) {
                if (file != null && file.getFileName().toString().endsWith(".jar")) {
                    result.add(file.toFile());
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
            x.printStackTrace();
        } finally {

            return result;
        }
    }
}
