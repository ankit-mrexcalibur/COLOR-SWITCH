package sample;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.animation.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.event.Event;
import javafx.stage.*;
import javafx.scene.*;



public class Controller implements Initializable {


    public void Menumusic(String s) throws InterruptedException {
        String path = s;
        Thread musicThread = new Thread(new MusicClass(path, false));
        musicThread.start();
        musicThread.join();
    }

    @FXML
    Circle player = new Circle();

    static volatile AnchorPane gameScreen;
    static volatile ScrollPane gameLayout;

    @FXML
    JFXButton PauseButton = new JFXButton();

    @FXML
    private Circle Switcher = new Circle();


    @FXML
    private JFXButton StartButton = new JFXButton();

    @FXML
    private JFXButton ExitButton = new JFXButton();

    @FXML
    private JFXTextField HighScore = new JFXTextField();


    @FXML
    private ImageView O1 = new ImageView();

    @FXML
    private ImageView O2 = new ImageView();

    private boolean menu = true;

    @FXML
    private JFXTextField ScoreBoard = new JFXTextField();

//    private Cursor cursor = new Cursor();

    RotateTransition rt;
    RotateTransition rt2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

            DropShadow ds = new DropShadow();
            ds.setOffsetX(5);
            ds.setOffsetY(5);
            ds.setColor(Color.GRAY);
            HighScore.setText("0");
            ScoreBoard.setStyle("-fx-text-inner-color: white");
            HighScore.setStyle("-fx-text-inner-color: white");

            O1.setEffect(ds);
            O2.setEffect(ds);
            rt = new RotateTransition(Duration.seconds(2), O1);
            rt2 = new RotateTransition(Duration.seconds(2), O2);

            rt.setByAngle(360);
            rt2.setByAngle(-360);
            rt.setInterpolator(Interpolator.LINEAR);
            rt2.setInterpolator(Interpolator.LINEAR);
            rt.setCycleCount(Timeline.INDEFINITE);
            rt2.setCycleCount(Timeline.INDEFINITE);
            rt.play();
            rt2.play();

        //new RotateOut(HighScore).play();
    }

    Stage sourceStage;
    static int obstacle_number = 0;
    public void init_game() {
        gameScreen = (AnchorPane) gameLayout.getContent() ;
        sourceStage.setScene(new Scene(gameLayout, 600, 900));
        player = (Circle) gameScreen.getChildren().get(1);
        player.setRadius(15);
        PauseButton = (JFXButton) gameScreen.getChildren().get(3);
        ScoreBoard = (JFXTextField) gameScreen.getChildren().get(2);
    }
    static ArrayList<CirclesObstacle> obstacles = new ArrayList<>();
    static Color color_names[] = {Color.rgb(141,11,251),Color.rgb(255,0,129), Color.rgb(50,227,242), Color.rgb(246,224,6)};
    static volatile boolean goingup = false;

    public void StartNewGame(javafx.event.ActionEvent event) throws Exception {
        sourceStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        gameLayout = (ScrollPane) FXMLLoader.load(getClass().getResource("GameScreen3.fxml"));
        gameLayout.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gameLayout.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        for(int i = 0; i < 20; i++)
            obstacles.add(new CirclesObstacle());
        System.out.println(obstacles.size());
        init_game();
        generateObstacles();
        System.out.println("Height: " + gameScreen.getHeight()+ "Width: " + gameScreen.getWidth());
        System.out.println(player.getLayoutY());
        Flash kaboom = new Flash(gameLayout);
        kaboom.play();
        double init_curs_x = player.getLayoutX();
        double init_curs_y = player.getLayoutY();
        new Thread(()->{
            boolean flag = true;
            while(flag){
                while(!goingup){
                    player.setLayoutY(player.getLayoutY() + 1);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        try {
                            gameOver();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }

                }
            }

        }).start();
    }


    private void generateObstacles() {
        double init_position = 6650;
        for(int i = 0; i < 20; i++) {
            CirclesObstacle obstacle = new CirclesObstacle(player.getLayoutX(), init_position);
            Group g = obstacle.getGroup();
            Circle pt = obstacle.getPoint();
            switcher changeColor = obstacle.getColorSwitch();
            gameScreen.getChildren().add(g);
            gameScreen.getChildren().add(pt);
            gameScreen.getChildren().add(changeColor.getGroup());
            init_position-=300;
            obstacles.add(i, obstacle);
        }

        System.out.println("gameScreen height: " + gameScreen.getHeight());
    }


    public void Exit(javafx.event.ActionEvent event) throws Exception{
        sourceStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new saveSomething("savedGames.txt",gameLayout, false);
        sourceStage.close();
    }

    static volatile double lowerBound = 7150;
    static volatile double decrement_vValue = 200.0/7300.0;

    public void launchGame(Parent root){

    }

    public void BounceBall(Event event) throws Exception{
        goingup = false;
        System.out.println(player.getLayoutY());
        System.out.println("Lower bound: " + lowerBound);
        boolean initially = true;
        String path = "src/sample/swoosh.wav";


        Menumusic(path);


        Line line = new Line();


        line.setEndY(-50);


        TranslateTransition tt;

        PathTransition pt = new PathTransition();

        pt.setNode(player);

        pt.setPath(line);

        pt.setDuration(Duration.millis(500));

        pt.setInterpolator(Interpolator.EASE_OUT);

        System.out.println("Cursor -Y :" + player.getLayoutY());

        pt.play();
        player.setLayoutY(player.getLayoutY()-50);
        if( lowerBound - player.getLayoutY() >= 200) {
            System.out.println("diff: " + (lowerBound - player.getLayoutY()));

            gameLayout.setVvalue(gameLayout.getVvalue() - decrement_vValue);
            lowerBound -= 200;
            tt = new TranslateTransition(Duration.ZERO);
            PauseButton.setLayoutY(PauseButton.getLayoutY() - 200);
            tt.setNode(ScoreBoard);
            tt.play();
//            if(obstacles.get(obstacle_number).getGroup().getLayoutY() - 50 > player.getLayoutY())
//                obstacle_number++;
        }
    }
    public synchronized void checkCollision() throws IOException {







        if(player.getBoundsInParent().intersects(obstacles.get(obstacle_number).getGroup().getBoundsInParent())){
            Group g = obstacles.get(obstacle_number).getGroup();
            double degrees = g.getRotate();
            degrees %= 360;
            if(degrees >= 90 && degrees <= 180){
                if(!player.getFill().equals(Color.rgb(141,11,251)))
                    gameOver();
            }




            else if(degrees <= 90){
                if(!player.getFill().equals(Color.rgb(255,0,129)))
                    gameOver();
            }




            else if(degrees >= 180 && degrees <= 270){
                if(!player.getFill().equals(Color.rgb(50,227,242)))
                    gameOver();
            }




            else if(degrees >= 270 && degrees <= 360){
                if(!player.getFill().equals(Color.rgb(246,224,6)))
                    gameOver();
            }
        }

        else if(player.getBoundsInParent().intersects(obstacles.get(obstacle_number).getPoint().getBoundsInParent()))
            ScoreBoard.setText("" + (Integer.parseInt(ScoreBoard.getText()) + 1));

        //Color Switcher code


        else{



            for(Node node: obstacles.get(obstacle_number).getColorSwitch().getGroup().getChildren()){


                Shape s = (Shape) node;

                if(s.getBoundsInParent().intersects(player.getBoundsInParent())){

                    Random random = new Random();

                    int d = random.nextInt(4);

                    player.setFill(color_names[d]);

                    break;
                }
            }
        }
    }

    public void loadGame(ActionEvent event) throws IOException {
        sourceStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/loadGame.fxml"));
        sourceStage.setScene(new Scene(root, 600, 900));
        launchGame(root);
    }
































































































































































//    class ObstacleConfig{
//        private double speed;
//        protected double boundary_width = 8;
//        private int noOfColors;
//        protected String motion;
//        private ArrayList<Color> coloursUsed;
//        private sample.Coordinates Position;
//
//        public void setSpeed(double speed) {
//            this.speed = speed;
//        }
//
//        public void setBoundary_width(double boundary_width) {
//            this.boundary_width = boundary_width;
//
//        }
//
//        public void setNoOfColors(int noOfColors) {
//            this.noOfColors = noOfColors;
//        }
//
//        public void setMotion(String motion) {
//            this.motion = motion;
//        }
//
//        public void setPosition(sample.Coordinates position) {
//            Position = position;
//        }
//
//        public double getSpeed() {
//            return speed;
//        }
//
//        public double getBoundary_width() {
//            return boundary_width;
//        }
//
//        public int getNoOfColors() {
//            return noOfColors;
//        }
//
//        public sample.Coordinates getPosition() {
//            return Position;
//        }
//
//        public String getMotion() {
//            return motion;
//        }
//        void addColor(Color c){
//            noOfColors++;
//            coloursUsed.add(c);
//        }
//        void move(){
//
//        }
//    }
//
//    class PolygonConfig extends sample.ObstacleConfig {
//        private double length;
//        private String direction;
//
//        public void setLength(double length) {
//            this.length = length;
//        }
//
//        public void setDirection(String direction) {
//            this.direction = direction;
//        }
//
//        public String getDirection() {
//            return direction;
//        }
//
//        public double getLength() {
//            return length;
//        }
//
//
//    }
//    class LinearStripsConfig extends sample.ObstacleConfig {
//        private double length;
//        private int noOfStrips;
//        double PauseTime;
//
//        public void setLength(double length) {
//            this.length = length;
//        }
//
//        public void setNoOfStrips(int noOfStrips) {
//            this.noOfStrips = noOfStrips;
//        }
//
//        public void setPauseTime(double pauseTime) {
//            PauseTime = pauseTime;
//        }
//
//        public double getLength() {
//            return length;
//        }
//
//        public int getNoOfStrips() {
//            return noOfStrips;
//        }
//
//        public double getPauseTime() {
//            return PauseTime;
//        }
//    }
//    class WindmillConfig extends sample.ObstacleConfig {
//        private double length;
//        private String direction;
//
//        public void setLength(double length) {
//            this.length = length;
//        }
//
//        public void setDirection(String direction) {
//            this.direction = direction;
//        }
//
//        public double getLength() {
//            return length;
//        }
//
//        public String getDirection() {
//            return direction;
//        }
//    }
//
//    class Coordinates {
//        private double x;
//        private double y;
//
//        void setCoords(double x, double y) {
//        }
//        ArrayList<Double> getCoords() {
//            ArrayList<Double> coords = new ArrayList<>();
//            coords.add(x);
//            coords.add(y);
//            return coords;
//        }
//    }
//
//
//
//
//
//    class Star extends noDangerConfig
//    {
//        int value;
//        void setValue(int value) {
//        }
//        int getValue() {
//            return value;
//        }
//        void incrementScore(int Score) {
//        }
//    }
//
//    class Mixer extends noDangerConfig
//    {
//        ArrayList<Color> colors;
//        sample.ObstacleConfig coverObstacle;
//        void changeColor(CursorConfig cursor) {
//        }
//        sample.ObstacleConfig getObstacle() {
//            return coverObstacle;
//        }
//    }
//
//    class Score{
//        protected int value;
//        protected int highscore;
//        boolean isHighScore() {
//            return true;
//        }
//        void setHighScore(int highscore) {
//        }
//        int getHighScore() {
//            return highscore;
//        }
//    }
//
//    class Circle extends sample.ObstacleConfig {
//        private double radius;
//        private String direction;
//        private double area;
//        void setSize(double radius) {
//        }
//        void setDirection(String direction) {
//        }
//        double getSize() {
//            return radius;
//        }
//        String gegetDirection(){
//            return direction;
//        }
//    }




























































































































































































































































































//    class ObstacleConfig{
//        private double speed;
//        protected double boundary_width = 8;
//        private int noOfColors;
//        protected String motion;
//        private ArrayList<Color> coloursUsed;
//        private sample.Coordinates Position;
//
//        public void setSpeed(double speed) {
//            this.speed = speed;
//        }
//
//        public void setBoundary_width(double boundary_width) {
//            this.boundary_width = boundary_width;
//
//        }
//
//        public void setNoOfColors(int noOfColors) {
//            this.noOfColors = noOfColors;
//        }
//
//        public void setMotion(String motion) {
//            this.motion = motion;
//        }
//
//        public void setPosition(sample.Coordinates position) {
//            Position = position;
//        }
//
//        public double getSpeed() {
//            return speed;
//        }
//
//        public double getBoundary_width() {
//            return boundary_width;
//        }
//
//        public int getNoOfColors() {
//            return noOfColors;
//        }
//
//        public sample.Coordinates getPosition() {
//            return Position;
//        }
//
//        public String getMotion() {
//            return motion;
//        }
//        void addColor(Color c){
//            noOfColors++;
//            coloursUsed.add(c);
//        }
//        void move(){
//
//        }
//    }
//
//    class PolygonConfig extends sample.ObstacleConfig {
//        private double length;
//        private String direction;
//
//        public void setLength(double length) {
//            this.length = length;
//        }
//
//        public void setDirection(String direction) {
//            this.direction = direction;
//        }
//
//        public String getDirection() {
//            return direction;
//        }
//
//        public double getLength() {
//            return length;
//        }
//
//
//    }
//    class LinearStripsConfig extends sample.ObstacleConfig {
//        private double length;
//        private int noOfStrips;
//        double PauseTime;
//
//        public void setLength(double length) {
//            this.length = length;
//        }
//
//        public void setNoOfStrips(int noOfStrips) {
//            this.noOfStrips = noOfStrips;
//        }
//
//        public void setPauseTime(double pauseTime) {
//            PauseTime = pauseTime;
//        }
//
//        public double getLength() {
//            return length;
//        }
//
//        public int getNoOfStrips() {
//            return noOfStrips;
//        }
//
//        public double getPauseTime() {
//            return PauseTime;
//        }
//    }
//    class WindmillConfig extends sample.ObstacleConfig {
//        private double length;
//        private String direction;
//
//        public void setLength(double length) {
//            this.length = length;
//        }
//
//        public void setDirection(String direction) {
//            this.direction = direction;
//        }
//
//        public double getLength() {
//            return length;
//        }
//
//        public String getDirection() {
//            return direction;
//        }
//    }
//
//    class Coordinates {
//        private double x;
//        private double y;
//
//        void setCoords(double x, double y) {
//        }
//        ArrayList<Double> getCoords() {
//            ArrayList<Double> coords = new ArrayList<>();
//            coords.add(x);
//            coords.add(y);
//            return coords;
//        }
//    }
//
//
//
//
//
//    class Star extends noDangerConfig
//    {
//        int value;
//        void setValue(int value) {
//        }
//        int getValue() {
//            return value;
//        }
//        void incrementScore(int Score) {
//        }
//    }
//
//    class Mixer extends noDangerConfig
//    {
//        ArrayList<Color> colors;
//        sample.ObstacleConfig coverObstacle;
//        void changeColor(CursorConfig cursor) {
//        }
//        sample.ObstacleConfig getObstacle() {
//            return coverObstacle;
//        }
//    }
//
//    class Score{
//        protected int value;
//        protected int highscore;
//        boolean isHighScore() {
//            return true;
//        }
//        void setHighScore(int highscore) {
//        }
//        int getHighScore() {
//            return highscore;
//        }
//    }
//
//    class Circle extends sample.ObstacleConfig {
//        private double radius;
//        private String direction;
//        private double area;
//        void setSize(double radius) {
//        }
//        void setDirection(String direction) {
//        }
//        double getSize() {
//            return radius;
//        }
//        String gegetDirection(){
//            return direction;
//        }
//    }










































































































































    public void PauseMenu(javafx.event.ActionEvent event) throws Exception{
        sourceStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Pause.fxml"));
        sourceStage.setScene(new Scene(root, 600, 900));
        new BounceOut(root).play();
    }

    public void gameOver() throws IOException { ;
        Parent root = FXMLLoader.load(getClass().getResource("/sample/gameOver.fxml"));
        sourceStage.setScene(new Scene(root, 600, 900));
        if(Integer.parseInt(HighScore.getText()) < Integer.parseInt(ScoreBoard.getText()))
            HighScore.setText(ScoreBoard.getText());



        new FadeOut(root).play();
    }
    public void resume(javafx.event.ActionEvent event) throws Exception{
        sourceStage.setScene(new Scene(gameLayout, 600, 900));
        new FadeIn(gameLayout).play();
    }

    public void saveGame(javafx.event.ActionEvent event) throws Exception{
        sourceStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        saveSomething s = new saveSomething("savedGames.txt", gameLayout, false);

    }

}

class CursorConfig{
    private double size = 10.0;
    private Color color;
    private double dropSpeed;
    private double bounce;
    private Coordinates position;

    void switchColor(Color color) {
    }
    void increaseSpeed(double speed) {
    }
    void changeBounce(double bounce) {
    }
    void detectColor(Color color) {
    }
    void bounce(double bounce) {
    }
    double getSize() {
        return size;
    }
    void setColor(Color color) {
    }
    void setDspeed(double dropSpeed) {
    }
    void setBounce(double bounce) {
    }
    void setPosition(Coordinates position) {
    }
    Color getColor() {
        return color;
    }
    double getDSpeed() {
        return dropSpeed;
    }
    double getBounce() {
        return bounce;
    }
    Coordinates getPosition() {
        return position;
    }
}

class noDangerConfig{
    private String type;
    private double size=10.0;
    private String shape;
    private Coordinates position;

    void setType(String type) {
    }
    void setSize(double size) {
    }
    void setShape(String shape) {
    }
    String getType() {
        return type;
    }
    double getSize(){
        return size;
    }

    public String getShape() {
        return shape;
    }
}
