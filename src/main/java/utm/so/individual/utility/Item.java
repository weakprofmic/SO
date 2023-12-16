package utm.so.individual.utility;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import utm.so.individual.App;

public class Item extends ListCell<Athlete> {

  @FXML
  private Button btnDelete;
  @FXML
  private TextField tfName;
  @FXML
  private TextField tfSwSpeed;
  @FXML
  private TextField tfCycSpeed;
  @FXML
  private TextField tfRunSpeed;

  public Item() {
    FXMLLoader loader = new FXMLLoader(App.class.getClassLoader().getResource("fxml/components/Item.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    try {
      loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    tfName.textProperty()
        .addListener((observable, oldValue, newValue) -> itemProperty().get().setName(tfName.getText()));
    tfSwSpeed.textProperty().addListener((observable, oldValue, newValue) -> itemProperty().get()
        .setSwimmingSpeed(Float.parseFloat(tfSwSpeed.getText())));
    tfCycSpeed.textProperty().addListener((observable, oldValue, newValue) -> itemProperty().get()
        .setCyclingSpeed(Float.parseFloat(tfCycSpeed.getText())));
    tfRunSpeed.textProperty().addListener((observable, oldValue, newValue) -> itemProperty().get()
        .setRunningSpeed(Float.parseFloat(tfRunSpeed.getText())));

    btnDelete.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        int index = getIndex();
        if (index >= 0) {
          // Remove the item from the list
          getListView().getItems().remove(index);
        }
      }
    });
  }

  @Override
  protected void updateItem(Athlete item, boolean empty) {
    super.updateItem(item, empty);

    if (empty || item == null) {
      setText(null);
      setContentDisplay(ContentDisplay.TEXT_ONLY);
    } else {
      tfName.setText(item.getName());
      tfSwSpeed.setText(item.getSwimmingSpeed() + "");
      tfCycSpeed.setText(item.getCyclingSpeed() + "");
      tfRunSpeed.setText(item.getRunningSpeed() + "");

      setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }
  }

}