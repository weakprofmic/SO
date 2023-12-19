package utm.so.individual.problem.waiter;

import javafx.application.Platform;
import utm.so.individual.App;
import utm.so.individual.controllers.mainController;
import utm.so.individual.problem.base.BaseChopStick;
import utm.so.individual.problem.base.BasePhilosopher;
import utm.so.individual.utils.CommonUtils;
import utm.so.individual.utils.PhilosopherUtils.IHaveState;
import utm.so.individual.utils.PhilosopherUtils.PhilosopherState;
import utm.so.individual.utils.Setup;

public class Philosopher extends BasePhilosopher implements Runnable,IHaveState {

    private Waiter waiter;
    private final int id;

    private PhilosopherState state = PhilosopherState.THINKING;

    public PhilosopherState getState() {
        return state;
    }

    public void setState(PhilosopherState state) {
        this.state = state;
        Platform.runLater(() -> mainController.canvas.draw());
    }

    public Philosopher(int id, BaseChopStick leftStick, BaseChopStick rightStick, Waiter waiter) {
        super(leftStick, rightStick);
        this.id = id;
        this.waiter = waiter;
    }

    @Override
    public void run() {
        try {
            think();
            while (!Thread.currentThread().isInterrupted()) {
                setState(PhilosopherState.HUNGRY);
                waiter.askForSticks();
                synchronized (leftStick) {
                    leftStick.take();
                    synchronized (rightStick) {
                        rightStick.take();
                        setState(PhilosopherState.EATING);
                        eat();
                        rightStick.put();
                        leftStick.put();
                        waiter.returnSticks();
                    }
                }
                setState(PhilosopherState.THINKING);
                think();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " is interrupted");
        }
    }

    protected void eat() throws InterruptedException {
        System.out.println("I e " + id);

        if (Setup.EM_L > 0)
            Thread.sleep(Setup.EM_L * 1000 + CommonUtils.getRandomInt((Setup.EMX_L - Setup.EM_L) * 1000));

        ++eatCount;
    }

    protected void think() throws InterruptedException {
        System.out.println("I t " + id);

        if (Setup.TM_L > 0)
            Thread.sleep(Setup.TM_L * 1000 + CommonUtils.getRandomInt((Setup.TMX_L - Setup.TM_L) * 1000));
    }
}
