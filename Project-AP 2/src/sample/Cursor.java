package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Circle;

public class Cursor extends Application {

    public Cursor(Circle cursor, ScrollPane gameLayout, AnchorPane gameScreen, JFXButton pauseButton, JFXTextField scoreboard) {
        this.cursor = cursor;
        this.gameLayout = gameLayout;
        this.gameScreen = gameScreen;
        this.pauseButton = pauseButton;
        Scoreboard = scoreboard;
    }

    public void Menumusic(String s) throws InterruptedException {
        String path = s;
        Thread musicThread = new Thread(new MusicClass(path, false));
        musicThread.start();
        musicThread.join();
    }
    private Circle cursor;
    private ScrollPane gameLayout;
    private AnchorPane gameScreen;
    private JFXButton pauseButton;
    private JFXTextField Scoreboard;

    public Cursor(){

    }

    void bounce() throws InterruptedException {
        boolean initially = true;
        String path = "src/sample/swoosh.wav";
        Menumusic(path);
        TranslateTransition tt = new TranslateTransition(Duration.millis(200), cursor);
        tt.setByY(-100);
        tt.setInterpolator(Interpolator.LINEAR);
        System.out.println("Cursor -Y :" + cursor.getLayoutY());
        tt.play();
    }
    @Override
    public void start(Stage stage) throws Exception {

    }
}
