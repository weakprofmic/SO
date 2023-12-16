package utm.so.individual.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import utm.so.individual.App;
import utm.so.individual.utility.Race;

public class startController implements Initializable {

    @FXML 
    private Label label;
    
    @FXML 
    private ProgressBar progressBar;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // String javaVersion = System.getProperty("java.version");
        // String javafxVersion = System.getProperty("javafx.version");
        // label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " +
        // javaVersion + ".");
        // Race r = new Race();
        // label.textProperty().bind(r.getParticipants().get(0).message);
        // progressBar.progressProperty().bind(r.getParticipants().get(0).progress);
        // r.start();
    }

    @FXML
    private void toRace() {
        try {
            App.changeScene("mainScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void toSetup() {
        try {
            App.changeScene("setupScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

