package utm.so.individual.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utm.so.individual.App;
import utm.so.individual.utility.Athlete;
import utm.so.individual.utility.Race;
import utm.so.individual.utility.RaceItem;
import utm.so.individual.utility.ResizableCanvas;

public class mainController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Canvas canvas_;
    @FXML
    private VBox box;

    @FXML
    private Button btnStart;

    @FXML
    private VBox vbRaceAthletes;

    private static ArrayList<Athlete> athletes;

    public static ArrayList<Athlete> getAthletes() {
        return athletes;
    }

    public static void setAthletes(ArrayList<Athlete> athletes_) {
        athletes = athletes_;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // if (athletes != null && !athletes.isEmpty()) {
        // // System.out.println(athletes);
        // btnStart.setDisable(false);
        // for (Athlete athlete : athletes) {
        // vbRaceAthletes.getChildren().add(new RaceItem(athlete));
        // }
        // } else {
        // btnStart.setDisable(true);
        // // System.out.println("No athletes here");
        // }

        ResizableCanvas canvas = new ResizableCanvas();

        anchorPane.getChildren().add(canvas);

        // Bind canvas size to stack pane size.
        canvas.widthProperty().bind(
                anchorPane.widthProperty());
        canvas.heightProperty().bind(
                anchorPane.heightProperty());
        
       
        System.out.println(canvas.getHeight());
    }

    @FXML
    private void toSetup() {
        try {
            App.changeScene("setupScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void toMenu() {
        try {
            App.changeScene("startScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void start() {
        for (int i = 0; i < athletes.size(); ++i) {
            if (athletes.get(i).isCanceled) {
                athletes.set(i, new Athlete(athletes.get(i)));
                vbRaceAthletes.getChildren().set(i, new RaceItem(athletes.get(i)));
            }
            Race r = new Race(athletes, "Moldova");
            try {
                r.start();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                // System.out.println("RACE IS OVER");
            }
        }

    }
}
