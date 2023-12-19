package utm.so.individual.controllers;

import java.net.URL;
import java.io.IOException;
import java.util.*;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ArrayChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import utm.so.individual.App;
import utm.so.individual.utils.Setup;

public class setupController implements Initializable {

    private static int loaded;
    private static IntegerProperty phils = new SimpleIntegerProperty(5);
    private static IntegerProperty lsecs = new SimpleIntegerProperty(30);
    private static IntegerProperty mtt = new SimpleIntegerProperty(1);
    private static IntegerProperty mxtt = new SimpleIntegerProperty(5);
    private static IntegerProperty mte = new SimpleIntegerProperty(1);
    private static IntegerProperty mxte = new SimpleIntegerProperty(5);
    @FXML
    private Label label;

    @FXML
    private VBox vbLast;

    @FXML
    private TextField tfPhils, tfL, tfmtt, tfmxtt, tfmte, tfmxte;

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane tp1;

    @FXML
    private ComboBox cmb;

    // @FXML
    // private ListView<Athlete> lvAthletesSetup;

    // private static ObservableList<Athlete> athletesObservableList =
    // FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        accordion.setExpandedPane(tp1);
        tfPhils.textProperty().bind(phils.asString());
        tfL.textProperty().bind(lsecs.asString());
        tfmtt.textProperty().bind(mtt.asString());
        tfmxtt.textProperty().bind(mxtt.asString());
        tfmte.textProperty().bind(mte.asString());
        tfmxte.textProperty().bind(mxte.asString());

        cmb.getItems().addAll(
                "naive",
                "waiter",
                "monitor",
                "check_stick",
                "chandry_misra",
                "check_stick_odd_even");
        cmb.setValue(Setup.method);

        // cmb.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
        // @Override
        // public ListCell<String> call(ListView<String> p) {
        // return new ListCell<String>() {
        // private final Rectangle rectangle;
        // {
        // setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        // rectangle = new Rectangle(10, 10);
        // }

        // @Override
        // protected void updateItem(String item, boolean empty) {
        // super.updateItem(item, empty);

        // if (item == null || empty) {
        // setGraphic(null);
        // } else {
        // rectangle.setFill(item);
        // setGraphic(rectangle);
        // }
        // }
        // };
        // }
        // });
        // vbLast.getChildren().add(cmb);

        // if (athletesObservableList.isEmpty()) {
        // athletesObservableList.add(new Athlete("Greg", 0, 0, 0));
        // // System.out.println("empty show");
        // }

        // System.out.println("each show");
        // lvAthletesSetup.setItems(athletesObservableList);

        // lvAthletesSetup.setCellFactory(new Callback<ListView<Athlete>,
        // ListCell<Athlete>>() {

        // @Override
        // public ListCell<Athlete> call(ListView<Athlete> arg0) {
        // return new Item();
        // }

        // });
    }

    @FXML
    private void btnClick() {
        phils.setValue(5);
        lsecs.setValue(30);
        mtt.setValue(1);
        mxtt.setValue(5);
        mte.setValue(1);
        mxte.setValue(5);
        cmb.setValue("check_stick");
        // // System.out.println("Added?");
        // try {
        // athletesObservableList.add(new Athlete("jIM", 0, 0, 0));
        // // System.out.println("Added?");
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }

    @FXML
    private void save() {
        Setup.N_F = phils.getValue();
        Setup.L = lsecs.getValue();

        Setup.TM_L = mtt.getValue();
        Setup.TMX_L = mxtt.getValue();
        Setup.EM_L = mte.getValue();
        Setup.EMX_L = mxte.getValue();
        Setup.method = (String)cmb.getValue();

        // System.out.println("Saved?");
        // try {
        // for (Athlete athlete : athletesObservableList) {
        // // System.out.println(athlete.getName());
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }

    @FXML
    private void toMenu() {
        // mainController.setAthletes(new ArrayList<>(athletesObservableList));
        try {
            App.changeScene("startScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void toRace() {
        // mainController.setAthletes(new ArrayList<>(athletesObservableList));
        try {
            App.changeScene("mainScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void add() {
        phils.setValue(phils.getValue() + 1);
    }

    @FXML
    public void subs() {
        phils.setValue(phils.getValue() - 1);
    }

    @FXML
    public void addmtt() {
        mtt.setValue(mtt.getValue() + 1);
    }

    @FXML
    public void submtt() {
        mtt.setValue(mtt.getValue() - 1);
    }

    @FXML
    public void addmxtt() {
        mxtt.setValue(mxtt.getValue() + 1);
    }

    @FXML
    public void submxtt() {
        mxtt.setValue(mxtt.getValue() - 1);
    }

    @FXML
    public void addmte() {
        mte.setValue(mte.getValue() + 1);
    }

    @FXML
    public void submte() {
        mte.setValue(mte.getValue() - 1);
    }

    @FXML
    public void addmxte() {
        mxte.setValue(mxte.getValue() + 1);
    }

    @FXML
    public void submxte() {
        mxte.setValue(mxte.getValue() - 1);
    }

    @FXML
    public void add5() {
        lsecs.setValue(lsecs.getValue() + 5);
    }

    @FXML
    public void subs5() {
        lsecs.setValue(lsecs.getValue() - 5);
    }
}
