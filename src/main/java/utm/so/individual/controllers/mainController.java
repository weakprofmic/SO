package utm.so.individual.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utm.so.individual.App;
import utm.so.individual.problem.base.BasePhilosopher;
import utm.so.individual.problem.naive.Philosopher;
import utm.so.individual.utility.ResizableCanvas;
import utm.so.individual.utils.Setup;
import utm.so.individual.utils.PhilosopherUtils.IHaveState;

public class mainController implements Initializable {

    public static ResizableCanvas canvas = new ResizableCanvas();

    private boolean isRunning = false;
    @FXML
    private Label label, labelMins, labelSecs, labelHours, labelTimeCell;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Canvas canvas_;
    @FXML
    private VBox box;

    @FXML
    private Button btnStart;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        anchorPane.getChildren().add(canvas);
        anchorPane.setLeftAnchor(canvas, 50d);
        anchorPane.setRightAnchor(canvas, 50d);
        anchorPane.setTopAnchor(canvas, 20d);
        anchorPane.setBottomAnchor(canvas, 20d);

        // try {
        //     Class simul = Class.forName("utm.so.individual.problem." + Setup.method + ".Simulation");
        //     Method m = simul.getMethod("getPhilosophers");

        //     canvas.setPhilosophers((IHaveState[]) m.invoke(null));
        // } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
        //         | SecurityException e) {
        //     e.printStackTrace();
        // }
        
        canvas.setPhilosophers((IHaveState[]) new Philosopher[Setup.N_F]);


        anchorPane.heightProperty().addListener(e -> {
            System.out.println("force");
            canvas.custResize(anchorPane.getWidth() - 100, anchorPane.getHeight() - 40);
        });

        anchorPane.widthProperty().addListener(e -> {
            System.out.println("force");
            canvas.custResize(anchorPane.getWidth() - 100, anchorPane.getHeight() - 40);
        });

        canvas.resize(500, 500);


        labelMins.setText("-"); 
        labelSecs.setText("-");
        labelHours.setText("-");

        // // Bind canvas size to stack pane size.
        // canvas.widthProperty().bind(
        // anchorPane.widthProperty());
        // canvas.heightProperty().bind(
        // anchorPane.heightProperty());

        // System.out.println(canvas.getHeight());
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

    class IntHolder {

        public IntHolder(int value) {
            this.value = value;
        }

        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    @FXML
    private void start() {
        if (!isRunning) {
            isRunning = !isRunning;
            btnStart.setDisable(true);
            System.out.println(Setup.L);
            final IntHolder secs = new IntHolder(Setup.L), mins = new IntHolder(0), hours = new IntHolder(0);
            mins.setValue(secs.getValue() / 60);
            hours.setValue(mins.getValue() / 60);
            labelSecs.setText((secs.getValue() - mins.getValue() * 60 - hours.getValue() * 3600) + "");
            labelMins.setText(mins.getValue() + "");
            labelHours.setText(hours.getValue() + "");
            Thread testThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    final IntHolder rem = new IntHolder(
                            secs.getValue() - mins.getValue() * 60 - hours.getValue() * 3600);

                    Timer tm = new Timer();
                    tm.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            System.out.println(secs + " Remaining");
                            Platform.runLater(() -> labelSecs.setText(rem.getValue() + ""));
                            if (rem.getValue() == 0) {
                                if (mins.getValue() > 0) {
                                    rem.setValue(59);
                                    mins.setValue(mins.getValue() - 1);
                                    Platform.runLater(() -> labelMins.setText(mins.getValue() + ""));
                                } else if (hours.getValue() > 0) {
                                    mins.setValue(59);
                                    rem.setValue(59);
                                    hours.setValue(mins.getValue() - 1);
                                    Platform.runLater(() -> labelMins.setText(mins.getValue() + ""));
                                    Platform.runLater(() -> labelHours.setText(hours.getValue() + ""));
                                } else {
                                    tm.cancel();
                                    isRunning = !isRunning;
                                    btnStart.setDisable(false);
                                }
                            } else
                                rem.setValue(rem.getValue() - 1);
                        }

                    }, 1000, 1000);

                    try {
                        Class simul = Class.forName("utm.so.individual.problem." + Setup.method + ".Simulation");
                        Method m = simul.getMethod("start");

                        m.invoke(null);
                    } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException
                            | NoSuchMethodException
                            | SecurityException e) {
                        e.printStackTrace();
                    }

                }

            });
            testThread.start();
        }

        else
            System.out.println("Programm is still running");
    }
}
