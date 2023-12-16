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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import utm.so.individual.App;
import utm.so.individual.utility.Athlete;
import utm.so.individual.utility.Item;

public class setupController implements Initializable {

    private static int loaded;
    private static IntegerProperty phils = new SimpleIntegerProperty(0);
    @FXML
    private Label label;

    @FXML
    private TextField tfPhils;

     @FXML
    private Accordion accordion;
     @FXML
    private TitledPane tp1;

    // @FXML
    // private ListView<Athlete> lvAthletesSetup;

    // private static ObservableList<Athlete> athletesObservableList = FXCollections.observableArrayList();

     @FXML
    public void add() {
        phils.setValue(phils.getValue() + 1);
    }

     @FXML
    public void subs() {
        phils.setValue(phils.getValue() - 1);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        accordion.setExpandedPane(tp1);
        tfPhils.textProperty().bind(phils.asString());
        // if (athletesObservableList.isEmpty()) {
        //     athletesObservableList.add(new Athlete("Greg", 0, 0, 0));
        //     // System.out.println("empty show");
        // }

        // System.out.println("each show");
        // lvAthletesSetup.setItems(athletesObservableList);

        // lvAthletesSetup.setCellFactory(new Callback<ListView<Athlete>, ListCell<Athlete>>() {

        //     @Override
        //     public ListCell<Athlete> call(ListView<Athlete> arg0) {
        //         return new Item();
        //     }

        // });
    }

    @FXML
    private void btnClick() {
        // // System.out.println("Added?");
        // try {
        //     athletesObservableList.add(new Athlete("jIM", 0, 0, 0));
        //     // System.out.println("Added?");
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

    @FXML
    private void save() {
        // System.out.println("Saved?");
        // try {
        //     for (Athlete athlete : athletesObservableList) {
        //         // System.out.println(athlete.getName());
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
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

}
