package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class CirclesObstacle extends Application {
    private double rad = 80;
    private Circle point = new Circle(15, Color.GREY);
    private switcher colorSwitch;
    private double XCENTER = 0, YCENTER = 0;
    private Group group;
    boolean play = false;
    Arc arcA = new Arc();   Arc arcB = new Arc();   Arc arcC = new Arc();   Arc arcD = new Arc();

    CirclesObstacle(){

    }
    CirclesObstacle(double lx, double ly){
        arcA.setLength(90); arcA.setStrokeWidth(20); arcA.setStroke(Color.rgb(141,11,251)); arcA.setFill(Color.TRANSPARENT); arcA.setCenterX(XCENTER); arcA.setStartAngle(0.0f); arcA.setType(ArcType.OPEN); arcA.setRadiusX(rad); arcA.setCenterY(YCENTER); arcA.setRadiusY(rad); arcA.setStroke(Color.rgb(141,11,251));
        arcB.setLength(90); arcB.setStrokeWidth(20); arcB.setStroke(Color.rgb(255,0,129)); arcB.setFill(Color.TRANSPARENT); arcB.setCenterX(XCENTER); arcB.setStartAngle(90); arcB.setType(ArcType.OPEN); arcB.setRadiusX(rad); arcB.setCenterY(YCENTER); arcB.setRadiusY(rad); arcB.setStroke(Color.rgb(255,0,129));
        arcC.setLength(90); arcC.setStrokeWidth(20); arcC.setStroke(Color.rgb(50,227,242)); arcC.setFill(Color.TRANSPARENT); arcC.setCenterX(XCENTER); arcC.setStartAngle(180); arcC.setType(ArcType.OPEN); arcC.setRadiusX(rad); arcC.setCenterY(YCENTER); arcC.setRadiusY(rad); arcC.setStroke(Color.rgb(50,227,242));
        arcD.setLength(90); arcD.setStrokeWidth(20); arcD.setStroke(Color.rgb(246,224,6)); arcD.setFill(Color.TRANSPARENT); arcD.setCenterX(XCENTER); arcD.setStartAngle(270); arcD.setType(ArcType.OPEN); arcD.setRadiusX(rad); arcD.setCenterY(YCENTER); arcD.setRadiusY(rad); arcD.setStroke(Color.rgb(246,224,6));

        group = new Group(arcA,arcB,arcC,arcD);
        group.setLayoutX(lx);
        group.setLayoutY(ly);
        point.setLayoutY(group.getLayoutY());
        point.setLayoutX(lx);
        colorSwitch = new switcher(lx, ly+30);
        rotate();
    }

    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane ap = new AnchorPane();
        {
            if (play) {
                ap.getChildren().add(group);
                ap.setPrefWidth(500);
                ap.setPrefHeight(500);
                AnchorPane.setBottomAnchor(group, 100.0);
                AnchorPane.setRightAnchor(group, 250.0);
                Scene s = new Scene(ap, 500, 500);
                s.setFill(Color.BLACK);
                stage.setScene(s);
                stage.show();
            }
        }
    }



































































































































































    private void rotate() {
        RotateTransition rt = new RotateTransition(Duration.millis(2500), group);
        rt.setByAngle(360);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setAxis(Rotate.Z_AXIS);
        rt.play();
    }

    public double getRad() {
        return rad;
    }

    public void setRad(double rad) {
        this.rad = rad;
    }


    public double getXCENTER() {
        return XCENTER;
    }

    public void setXCENTER(double XCENTER) {
        this.XCENTER = XCENTER;
    }

    public double getYCENTER() {
        return YCENTER;
    }

    public void setYCENTER(double YCENTER) {
        this.YCENTER = YCENTER;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public static void main(String[] args){
        launch(args);
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public Circle getPoint() {
        return point;
    }

    public void setPoint(Circle point) {
        this.point = point;
    }

    public switcher getColorSwitch() {
        return colorSwitch;
    }

    public void setColorSwitch(switcher colorSwitch) {
        this.colorSwitch = colorSwitch;
    }

    public Arc getArcA() {
        return arcA;
    }

    public void setArcA(Arc arcA) {
        this.arcA = arcA;
    }

    public Arc getArcB() {
        return arcB;
    }

    public void setArcB(Arc arcB) {
        this.arcB = arcB;
    }

    public Arc getArcC() {
        return arcC;
    }

    public void setArcC(Arc arcC) {
        this.arcC = arcC;
    }

    public Arc getArcD() {
        return arcD;
    }

    public void setArcD(Arc arcD) {
        this.arcD = arcD;
    }
}
