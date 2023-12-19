package utm.so.individual.problem.naive;

import javafx.application.Platform;
import utm.so.individual.controllers.mainController;
import utm.so.individual.problem.base.BaseChopStick;
import utm.so.individual.problem.base.BasePhilosopher;
import utm.so.individual.utils.PhilosopherUtils.IHaveState;
import utm.so.individual.utils.PhilosopherUtils.PhilosopherState;

public class Philosopher extends BasePhilosopher implements Runnable, IHaveState {
    private PhilosopherState state = PhilosopherState.THINKING;

    public PhilosopherState getState() {
        return state;
    }

    public void setState(PhilosopherState state) {
        this.state = state;
        Platform.runLater(() -> mainController.canvas.draw());
    }

    protected Philosopher(BaseChopStick leftStick, BaseChopStick rightStick) {
        super(leftStick, rightStick);
    }

    @Override
    public void run() {
        try {
            think();
            while (!Thread.currentThread().isInterrupted()) {
                setState(PhilosopherState.HUNGRY);
                synchronized (leftStick) {
                    leftStick.take();
                    synchronized (rightStick) {
                        rightStick.take();
                        setState(PhilosopherState.EATING);
                        eat();
                        rightStick.put();
                        leftStick.put();
                    }
                }
                setState(PhilosopherState.THINKING);
                think();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " is interrupted");
        }
    }
}
