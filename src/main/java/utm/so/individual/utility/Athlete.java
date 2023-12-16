package utm.so.individual.utility;

import java.util.*;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import utm.so.individual.utility.RaceState.Stage;
import utm.so.individual.utility.Task.*;

public class Athlete extends Timer {
  Stage stage = Stage.DEFAULT;
  Race participantOf;
  public boolean isCanceled = false;

  public SimpleDoubleProperty progress = new SimpleDoubleProperty(0);
  public SimpleDoubleProperty swimmingProgress = new SimpleDoubleProperty(0);
  public SimpleDoubleProperty cyclingProgress = new SimpleDoubleProperty(0);
  public SimpleDoubleProperty runningProgress = new SimpleDoubleProperty(0);
  public SimpleStringProperty message = new SimpleStringProperty("");
  public SimpleDoubleProperty speed = new SimpleDoubleProperty(0);

  String name;
  private float swimmingSpeed;
  private float cyclingSpeed;
  private float runningSpeed;

  public void onStageChange(String message, double speed) {
    setMessage(message);
    setSpeed(speed);
    stage = RaceState.nextStage(stage);
  }

  public void onRaceFinish(String message, double speed, double progress) {
    onStageChange(message, speed);
    setProgress(progress);

    // System.out.println(message);

    cancel();
    isCanceled = true;
  }

  public double getSwimmingProgress() {
    return swimmingProgress.get();
  }

  public void setSwimmingProgress(double swimmingProgress) {
    Platform.runLater(() -> this.swimmingProgress.set(swimmingProgress));
  }

  public double getCyclingProgress() {
    return cyclingProgress.get();
  }

  public void setCyclingProgress(double cyclingProgress) {
    Platform.runLater(() -> this.cyclingProgress.set(cyclingProgress));
  }

  public double getRunningProgress() {
    return runningProgress.get();
  }

  public void setRunningProgress(double runningProgress) {
    Platform.runLater(() -> this.runningProgress.set(runningProgress));
  }

  public String getMessage() {

    return message.get();
  }

  public void setMessage(String message) {
    Platform.runLater(() -> this.message.set(message));
  }

  public double getProgress() {
    return progress.get();
  }

  public void addProgress() {
    setProgress(getProgress() + speed.get() / participantOf.getCourse().getFullDistance());
    switch (stage) {
      case CYCLING:
        setCyclingProgress(cyclingProgress.get() + speed.get() /
            participantOf.getCourse().getCyclingDistance());
        break;
      case DEFAULT:
        break;
      case RUNNING:
        setRunningProgress(runningProgress.get() + speed.get() /
            participantOf.getCourse().getRunningDistance());
        break;
      case SWIMMING:
        setSwimmingProgress(
            swimmingProgress.get() + speed.get() / participantOf.getCourse().getSwimmingDistance());
        break;
      default:
        break;

    }
  }

  public void setProgress(double progress) {
    Platform.runLater(() -> this.progress.set(progress));
  }

  public double getSpeed() {
    return speed.get();
  }

  public void setSpeed(double speed) {
    Platform.runLater(() -> this.speed.set(speed));
  }

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public float getSwimmingSpeed() {
    return swimmingSpeed;
  }

  public void setSwimmingSpeed(float swimmingSpeed) {
    this.swimmingSpeed = swimmingSpeed;
  }

  public float getCyclingSpeed() {
    return cyclingSpeed;
  }

  public void setCyclingSpeed(float cyclingSpeed) {
    this.cyclingSpeed = cyclingSpeed;
  }

  public float getRunningSpeed() {
    return runningSpeed;
  }

  public void setRunningSpeed(float runningSpeed) {
    this.runningSpeed = runningSpeed;
  }

  public Athlete(String name, float swimmingSpeed, float cyclingSpeed, float runningSpeed) {
    super(true);
    this.name = name;
    this.swimmingSpeed = swimmingSpeed;
    this.cyclingSpeed = cyclingSpeed;
    this.runningSpeed = runningSpeed;
  };

  public Athlete(Athlete other) {
    super(true);
    this.name = other.name;
    this.swimmingSpeed = other.swimmingSpeed;
    this.cyclingSpeed = other.cyclingSpeed;
    this.runningSpeed = other.runningSpeed;
  };

  public Race getParticipantOf() {
    return participantOf;
  }

  public void setParticipantOf(Race participantOf) {
    this.participantOf = participantOf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void race() {
    schedule(new CourseStageTask("Swimming", participantOf.getCourse().getSwimmingDistance()) {

      @Override
      void doTheThing() throws InterruptedException {
        Athlete.this.onStageChange("on swimming stage", swimmingSpeed);
        float distance = stageDistance;

        while (distance > 0) {
          // System.out.println("Distance to swim " + distance / stageDistance * 100 +
          // "%");
          distance -= speed.get();
          Athlete.this.addProgress();
          Thread.sleep(speed.get() > distance ? ((int) (distance / speed.get())) * 1000 : 1000);
        }

        // System.out.println("Race stage " + participantOf.getRaceState().getStage());
        // System.out.println("Race state " + participantOf.getRaceState().getState());

        cancel();

      }

    }, 0);

    schedule(new CourseStageTask("Cycling", participantOf.getCourse().getCyclingDistance()) {

      @Override
      void doTheThing() throws InterruptedException {
        Athlete.this.onStageChange("on cycling stage", cyclingSpeed);
        float distance = stageDistance;
        while (distance > 0) {
          // System.out.println("Distance to ride " + distance / stageDistance * 100 +
          // "%");
          distance -= speed.get();
          Athlete.this.addProgress();
          Thread.sleep(speed.get() > distance ? ((int) (distance / speed.get())) * 1000 : 1000);
        }
        // System.out.println("Race stage " + participantOf.getRaceState().getStage());
        // System.out.println("Race state " + participantOf.getRaceState().getState());

        cancel();
      }

    }, 1);

    schedule(new CourseStageTask("Running", participantOf.getCourse().getRunningDistance()) {

      @Override
      void doTheThing() throws InterruptedException {
        Athlete.this.onStageChange("on running stage", runningSpeed);
        float distance = stageDistance;
        while (distance > 0) {
          // System.out.println("Distance to run " + distance / stageDistance * 100 +
          // "%");
          distance -= speed.get();
          Athlete.this.addProgress();
          Thread.sleep(speed.get() > distance ? ((int) (distance / speed.get())) * 1000 : 1000);
        }
        // System.out.println("Race stage " + participantOf.getRaceState().getStage());
        // System.out.println("Race state " + participantOf.getRaceState().getState());

        Athlete.this.onRaceFinish("finished", 0, 1);
        // System.out.println("Race stage " + participantOf.getRaceState().getStage());
        // System.out.println("Race state " + participantOf.getRaceState().getState());
        cancel();
      }

    }, 2);
  }

}
