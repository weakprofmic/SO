package utm.so.individual.utility;

import java.util.*;

public class Course {
  private String name;

  private float fullDistance;

  public float getFullDistance() {
    return fullDistance;
  }

  private float swimmingDistance;

  public float getSwimmingDistance() {
    return swimmingDistance;
  }

  public void setSwimmingDistance(float swimmingDistance) {
    this.swimmingDistance = swimmingDistance;
  }

  private float cyclingDistance;

  public float getCyclingDistance() {
    return cyclingDistance;
  }

  public void setCyclingDistance(float cyclingDistance) {
    this.cyclingDistance = cyclingDistance;
  }

  private float runningDistance;

  public float getRunningDistance() {
    return runningDistance;
  }

  public void setRunningDistance(float runningDistance) {
    this.runningDistance = runningDistance;
  }

  Random factor;

  public Course(String name) {
    this.name = name;
    generateCourse();
  }

  public void generateCourse() {
    factor = new Random();
    swimmingDistance = factor.nextFloat(2000) + 3000;
    cyclingDistance = factor.nextFloat(2000) + 3000;
    runningDistance = factor.nextFloat(2000) + 3000;
    fullDistance = swimmingDistance + cyclingDistance + runningDistance;
  }
}
