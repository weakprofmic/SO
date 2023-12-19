package utm.so.individual.problem.waiter;

import utm.so.individual.problem.base.BaseChopStick;
import utm.so.individual.utils.Setup;
import java.util.concurrent.*;

public class Waiter {
  Semaphore keySticks  = new Semaphore(Setup.N_F - 1);

  public void askForSticks() throws InterruptedException {
      keySticks.acquire(1);
  }

  public void returnSticks() {
    keySticks.release(1);
  }
}
