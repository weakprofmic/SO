package utm.so.individual.utility;

import java.util.*;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Task {
  static abstract class CourseStageTask extends TimerTask {
    String stageName;
    float stageDistance;
    SimpleDoubleProperty	progress = new SimpleDoubleProperty(0);
    SimpleStringProperty	message = new SimpleStringProperty("");

    public String getMessage() {
      
      return message.get();
    }

    public void setMessage(String message) {
      Platform.runLater(() -> this.message.set(message));
    }


    public double getProgress() {
      return progress.get();
    }

    public void setProgress(double progress) {
      Platform.runLater(() -> this.progress.set(progress));
    }

    CourseStageTask(String stageName, float stageDistance) {
      this.stageName = stageName;
      this.stageDistance = stageDistance;
    }

    @Override
    public void run() {
      // System.out.println(
      //     Thread.currentThread().getName() + " is now on stage " + stageName);
      try {
        toString();
        doTheThing();
        setProgress(100);
        setMessage("Finished");
      } catch (Exception e) {
      }
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("On");
      sb.append(stageName).append(", course length is ").append(stageDistance);
      return sb.toString();
    }

    abstract void doTheThing() throws InterruptedException;
  }
}
