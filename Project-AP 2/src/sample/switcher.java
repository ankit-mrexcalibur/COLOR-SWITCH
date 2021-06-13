package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class switcher extends Application {
    private double rad = 15;
    private double XCENTER = 0, YCENTER = 0;
    private Group group;
    boolean play = true;
    Arc arcA = new Arc();   Arc arcB = new Arc();   Arc arcC = new Arc();   Arc arcD = new Arc();
    switcher(){
    System.out.println("inside switcher class");
    }
    switcher(double lx, double ly){
        arcA.setLength(90); arcA.setStrokeWidth(0); arcA.setFill(Color.rgb(141,11,251));arcA.setCenterX(XCENTER); arcA.setStartAngle(0.0f); arcA.setType(ArcType.OPEN);arcA.setRadiusX(rad); arcA.setCenterY(YCENTER); arcA.setRadiusY(rad); arcA.setStroke(Color.rgb(141,11,251));
        arcB.setLength(90); arcB.setStrokeWidth(0); arcB.setFill(Color.rgb(255,0,129)); arcB.setCenterX(XCENTER); arcB.setStartAngle(90);  arcB.setType(ArcType.OPEN); arcB.setRadiusX(rad); arcB.setCenterY(YCENTER); arcB.setRadiusY(rad); arcB.setStroke(Color.rgb(255,0,129));
        arcC.setLength(90); arcC.setStrokeWidth(0); arcC.setFill(Color.rgb(50,227,242));arcC.setCenterX(XCENTER); arcC.setStartAngle(180); arcC.setType(ArcType.OPEN); arcC.setRadiusX(rad); arcC.setCenterY(YCENTER); arcC.setRadiusY(rad); arcC.setStroke(Color.rgb(50,227,242));
        arcD.setLength(90); arcD.setStrokeWidth(0); arcD.setFill(Color.rgb(246,224,6)); arcD.setCenterX(XCENTER); arcD.setStartAngle(270); arcD.setType(ArcType.OPEN); arcD.setRadiusX(rad); arcD.setCenterY(YCENTER); arcD.setRadiusY(rad); arcD.setStroke(Color.rgb(246,224,6));

        group = new Group(arcA,arcB,arcC,arcD);
        group.setLayoutX(lx);
        group.setLayoutY(ly - 200);
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

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
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
    @Override
    public void start(Stage stage) throws Exception {
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
}
