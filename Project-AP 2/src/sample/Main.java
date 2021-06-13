package sample;

import animatefx.animation.*;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.*;


import java.io.*;
import java.util.ArrayList;


class MusicClass implements Runnable{
    private final Media m;
    private final AudioClip mp;
    private final boolean loop;
    MusicClass(String path, boolean loop){
        this.m = new Media(new File(path).toURI().toString());
        this.mp = new AudioClip(m.getSource());
        this.loop = loop;
    }
    @Override
    public void run() {
        mp.play();
    }
}



public class Main extends Application {

    public void Menumusic(String s) throws InterruptedException {
        String path = s;
        Thread musicThread = new Thread(new MusicClass(path, true));
        musicThread.start();
        musicThread.join();
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //AnchorPane anchorpane = (AnchorPane) FXMLLoader.load(getClass().getResource("../gameScreen.fxml"));
        //ScrollPane sp = (ScrollPane) FXMLLoader.load(getClass().getResource("gameScreen2.fxml"));
        primaryStage.setTitle("Color Switch");
        Scene s = new Scene(root, 600, 800);
        primaryStage.setScene(s);
        primaryStage.show();
        Menumusic("src/sample/menu1.wav");
        new FadeIn(root).play();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

class saveSomething implements Serializable{

    private File f;
    private FileOutputStream fos;
    private ObjectOutputStream oos;

    <T> saveSomething(String s, T obj, boolean overwrite) throws IOException {
        f = new File(s);
        fos = new FileOutputStream(f);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.flush();
        new SerialDemo((ScrollPane) obj);
        if(overwrite)
            oos.close();
    }
}



class ObstacleConfig{
    private double speed;
    protected double boundary_width = 8;
    private int noOfColors;
    protected String motion;
    private ArrayList<Color> coloursUsed;
    private Coordinates Position;

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setBoundary_width(double boundary_width) {
        this.boundary_width = boundary_width;

    }

    public void setNoOfColors(int noOfColors) {
        this.noOfColors = noOfColors;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    public void setPosition(Coordinates position) {
        Position = position;
    }

    public double getSpeed() {
        return speed;
    }

    public double getBoundary_width() {
        return boundary_width;
    }

    public int getNoOfColors() {
        return noOfColors;
    }

    public Coordinates getPosition() {
        return Position;
    }

    public String getMotion() {
        return motion;
    }
    void addColor(Color c){
        noOfColors++;
        coloursUsed.add(c);
    }
    void move(){

    }
}

class PolygonConfig extends ObstacleConfig{
    private double length;
    private String direction;

    public void setLength(double length) {
        this.length = length;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public double getLength() {
        return length;
    }


}
class LinearStripsConfig extends ObstacleConfig{
    private double length;
    private int noOfStrips;
    double PauseTime;

    public void setLength(double length) {
        this.length = length;
    }

    public void setNoOfStrips(int noOfStrips) {
        this.noOfStrips = noOfStrips;
    }

    public void setPauseTime(double pauseTime) {
        PauseTime = pauseTime;
    }

    public double getLength() {
        return length;
    }

    public int getNoOfStrips() {
        return noOfStrips;
    }

    public double getPauseTime() {
        return PauseTime;
    }
}
class WindmillConfig extends ObstacleConfig{
    private double length;
    private String direction;

    public void setLength(double length) {
        this.length = length;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getLength() {
        return length;
    }

    public String getDirection() {
        return direction;
    }
}

class Coordinates {
    private double x;
    private double y;

    void setCoords(double x, double y) {
    }
    ArrayList<Double> getCoords() {
        ArrayList<Double> coords = new ArrayList<>();
        coords.add(x);
        coords.add(y);
        return coords;
    }
}





class Star extends noDangerConfig
{
    int value;
    void setValue(int value) {
    }
    int getValue() {
        return value;
    }
    void incrementScore(int Score) {
    }
}
class MyTimer extends AnimationTimer {

    @Override
    public void handle(long now) {

        doHandle();
    }

    private void doHandle() {

    }
}

class Mixer extends noDangerConfig
{
    ArrayList<Color> colors;
    ObstacleConfig coverObstacle;
    void changeColor(CursorConfig cursor) {
    }
    ObstacleConfig getObstacle() {
        return coverObstacle;
    }
}




class Score{
    protected int value;
    protected int highscore;
    boolean isHighScore() {
        return true;
    }
    void setHighScore(int highscore) {
    }
    int getHighScore() {
        return highscore;
    }
}














class Circle extends ObstacleConfig{
    private double radius;
    private String direction;
    private double area;
    void setSize(double radius) {
    }
    void setDirection(String direction) {
    }
    double getSize() {
        return radius;
    }
    String gegetDirection(){
        return direction;
    }
}


