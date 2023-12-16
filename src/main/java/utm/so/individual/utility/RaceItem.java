package utm.so.individual.utility;

import java.io.IOException;

import javafx.scene.control.ProgressBar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import utm.so.individual.App;

public class RaceItem extends VBox {
 
  // private Athlete athlete;
  @FXML
  private Label lblName;
  @FXML
  private Label lblSpeed;
  @FXML
  private ProgressBar pbSwimming;
  @FXML
  private ProgressBar pbCycling;
  @FXML
  private ProgressBar pbRunning;
  @FXML
  private ProgressIndicator piSummary;


  public RaceItem(Athlete athlete) {
    FXMLLoader loader = new FXMLLoader(App.class.getClassLoader().getResource("fxml/components/RaceItem.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    try {
      loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    lblName.setText(athlete.getName());
    lblSpeed.textProperty().bind(athlete.speed.asString());
    pbSwimming.progressProperty().bind(athlete.swimmingProgress);
    pbCycling.progressProperty().bind(athlete.cyclingProgress);
    pbRunning.progressProperty().bind(athlete.runningProgress);
    piSummary.progressProperty().bind(athlete.progress);
  }

}