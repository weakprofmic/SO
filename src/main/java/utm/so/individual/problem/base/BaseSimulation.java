package utm.so.individual.problem.base;

import utm.so.individual.App;
import utm.so.individual.utils.Setup;

// import utm.so.individual.App;

public class BaseSimulation {
  protected static Thread[] threads = new Thread[Setup.N_F];

  protected static void waitTime(long millis) {
    if (millis <= 0) {
      return;
    }
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  protected static void stopThreads(Thread[] threads) {
    for (Thread thread : threads) {
      if (!thread.isInterrupted()) {
        thread.interrupt();
      }
    }
    waitTime(100);
  }
}
