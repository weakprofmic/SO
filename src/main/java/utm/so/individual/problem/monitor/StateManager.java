package utm.so.individual.problem.monitor;

import java.util.concurrent.locks.*;

import javafx.application.Platform;
import utm.so.individual.controllers.mainController;
import utm.so.individual.utils.Setup;
import utm.so.individual.utils.PhilosopherUtils.PhilosopherState;

public class StateManager {
  private int philosophersCount;
  private Philosopher[] philosophers;

  private PhilosopherState[] state;

  private Lock lock;
  private Condition[] cond;

  public StateManager(Philosopher[] philosophers) {
    this.philosophers = philosophers;
    this.philosophersCount = philosophers.length;
    lock = new ReentrantLock();
    state = new PhilosopherState[philosophersCount];
    cond = new Condition[philosophersCount];
    for (int i = 0; i < philosophersCount; i++) {
      state[i] = PhilosopherState.THINKING;
      cond[i] = lock.newCondition();
    }
  }

  public void takeSticks(int id, ChopStick l, ChopStick r) {
    lock.lock();
    try {
      setState(id, PhilosopherState.HUNGRY);
      while (!philosophers[id].isStopped && (!l.isAvailable() || !r.isAvailable())) {
        cond[id].await();
      }
      l.setAvailable(false);
      r.setAvailable(false);
      setState(id, PhilosopherState.EATING);
      if (Setup.D) {
        printState(id);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public void putSticks(int id, ChopStick l, ChopStick r) {
    lock.lock();
    try {
      setState(id, PhilosopherState.THINKING);
      l.setAvailable(true);
      r.setAvailable(true);
      cond[(id + 1) % philosophersCount].signalAll();
      cond[(id + philosophersCount - 1) % philosophersCount].signalAll();
      if (Setup.D) {
        printState(id);
      }
    } finally {
      lock.unlock();
    }
  }

  void setState(int id, PhilosopherState s) {
    state[id] = s;
    Platform.runLater(() -> mainController.canvas.draw());
  }

  public PhilosopherState getState(int id) {
    return state[id]; 
  }

  private void printState(int id) {
    StringBuffer line = new StringBuffer();
    for (int i = 0; i < philosophersCount; i++) {
      switch (state[i]) {
        case THINKING:
          line.append("O ");
          break;
        case HUNGRY:
          line.append("- ");
          break;
        case EATING:
          line.append("X ");
          break;
        default:
          break;
      }
    }
    System.out.println(line + "(" + (id + 1) + ")");
  }
}
