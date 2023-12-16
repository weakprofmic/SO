package utm.so.individual.utility;


public class RaceState {
  enum State {
    PRESTART, 
    GOING,
    FINISHED
  }
  enum Stage {
    DEFAULT,
    SWIMMING,
    CYCLING, 
    RUNNING,
  }

  private State state = State.PRESTART;
  private Stage stage = Stage.DEFAULT;

  public State getState() {
    return state;
  }
  public void setState(State state) {
    this.state = state;
  }
  
  public Stage getStage() {
    return stage;
  }
  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public Stage nextStage() {
    switch(this.stage) {
      case CYCLING: return Stage.RUNNING;
      case DEFAULT: return Stage.SWIMMING;
      case RUNNING: return Stage.DEFAULT;
      case SWIMMING: return Stage.CYCLING;
      default: System.out.println("Incorrect value"); return null;
    }
  }

  public static Stage nextStage(Stage stage) {
    switch(stage) {
      case CYCLING: return Stage.RUNNING;
      case DEFAULT: return Stage.SWIMMING;
      case RUNNING: return Stage.DEFAULT;
      case SWIMMING: return Stage.CYCLING;
      default: System.out.println("Incorrect value"); return null;
    }
  }
}
