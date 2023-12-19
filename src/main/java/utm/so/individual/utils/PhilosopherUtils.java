package utm.so.individual.utils;

public class PhilosopherUtils {

  public enum PhilosopherState {
    HUNGRY("I am hungry"),
    EATING("I eat"),
    THINKING("I think"),
    INACTIVE("Inactive state");

    String message;

    public String getMessage() {
      return message;
    }

    PhilosopherState(String message) {
      this.message = message;
    }
  }

  public interface IHaveState {
    public PhilosopherState getState();
  }

}
