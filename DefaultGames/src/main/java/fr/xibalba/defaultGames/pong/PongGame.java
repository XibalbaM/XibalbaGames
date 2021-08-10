package fr.xibalba.defaultGames.pong;

import fr.xibalba.defaultGames.pong.entities.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PongGame {

    private static Timeline loop;
    private static Ball ball;
    private static PlayerPlatform player1;
    private static Platform player2;

    public static void startGame(Settings gameSettings) {

        Group root = new Group();

        Scene scene = new Scene(root, 1000, 600);
        scene.setFill(new RadialGradient(0, 0, 500, 300, 500, false, CycleMethod.NO_CYCLE, new Stop(0, Color.grayRgb(60)), new Stop(1, Color.BLACK)));

        //Objects :
        PongGame.ball = new Ball();
        PongGame.player1 = new PlayerPlatform(80, 250, gameSettings.platformsHeight(), gameSettings.nameJ1(), Color.GREEN, KeyCode.Z, KeyCode.S);
        if (gameSettings.solo()) {
            PongGame.player2 = new AIPlatform(890, 250, gameSettings.platformsHeight(), gameSettings.nameJ2(), Color.BLUE, gameSettings.botLevel());
        } else {
            PongGame.player2 = new PlayerPlatform(890, 250, gameSettings.platformsHeight(), gameSettings.nameJ2(), Color.BLUE, KeyCode.UP, KeyCode.DOWN);
        }
        root.getChildren().addAll(ball, player1, player2);

        /*//Controls
        PongGame.getStage().getScene().setOnKeyPressed(event -> {
            player1.getKeyHandler().accept(event);
            if (player2 instanceof PlayerPlatform)
                ((PlayerPlatform)player2).getKeyHandler().accept(event);
        });*/

        //loop the game mechanics
        PongGame.loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                ball.loop();
                player1.loop();
                player2.loop();

                if (player1.getScore() == 3) {
                    PongGame.endGame(player1, gameSettings.solo(), root);
                } else if (player2.getScore() == 3) {
                    PongGame.endGame(player2, gameSettings.solo(), root);
                }
            }
        }));
        PongGame.loop.setCycleCount(Timeline.INDEFINITE);
        PongGame.loop.play();
        endGame(player1, gameSettings.solo(), root);
    }

    private static void endGame(Platform winner, boolean solo, Group root) {

        Text text = new Text(winner instanceof AIPlatform ? "YOU LOOSE !" : winner.getPlayerName().toUpperCase() + " WON !");
        text.setFont(Font.font("Verdana", 70));
        text.setFill(winner instanceof AIPlatform ? Color.RED : Color.GREEN);
        text.setTextOrigin(VPos.CENTER);
        text.setLayoutX(400);
        text.setLayoutY(300);
        text.setTranslateX( -(text.getLayoutX()/2));

        //GameMenuButton replay = new GameMenuButton("Replay");
        //replay.setOnMouseClicked(event -> PongGame.startGame(Settings.getLastSettings()));

        //GameMenuButton mainMenu = new GameMenuButton("Leave");

        root.getChildren().add(text);

        loop.stop();
        System.out.println(winner.getPlayerName() + " won the game !");

        ball = null;
        ball = new Ball();
    }

    public static void addPoint(PlayerPlatform player) {

        player.setScore(player.getScore() + 1);
    }

    public static void stopApp() {

        System.exit(0);
    }

    public static Timeline getLoop() {

        return loop;
    }

    public static Ball getBall() {

        return ball;
    }

    public static PlayerPlatform getPlayer1() {

        return player1;
    }

    public static Platform getPlayer2() {

        return player2;
    }
}
