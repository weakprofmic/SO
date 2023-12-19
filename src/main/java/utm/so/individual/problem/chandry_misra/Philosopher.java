package utm.so.individual.problem.chandry_misra;

import javafx.application.Platform;
import utm.so.individual.App;
import utm.so.individual.controllers.mainController;
import utm.so.individual.utils.CommonUtils;
import utm.so.individual.utils.Setup;
import utm.so.individual.utils.PhilosopherUtils.IHaveState;
import utm.so.individual.utils.PhilosopherUtils.PhilosopherState;

public class Philosopher implements Runnable, IHaveState {

    private PhilosopherState state = PhilosopherState.THINKING;

    public PhilosopherState getState() {
        return state;
    }

    public void setState(PhilosopherState state) {
        this.state = state;
        Platform.runLater(() -> mainController.canvas.draw());
    }

    private final int id;
    private final ChopStick leftStick;
    private final ChopStick rightStick;
    protected int eatCount = 0;

    public boolean isStopped = false;

    public Philosopher(int id, ChopStick leftStick, ChopStick rightStick) {
        this.id = id;
        this.leftStick = leftStick;
        this.rightStick = rightStick;
    }

    @Override
    public void run() {
        try {
            think();
            while (!isStopped) {
                boolean flag = false;
                if (getState() != PhilosopherState.HUNGRY)
                    setState(PhilosopherState.HUNGRY);
                if (leftStick.request() && rightStick.request()) {
                    flag = true;
                    leftStick.take();
                    rightStick.take();
                    setState(PhilosopherState.EATING);
                    eat();
                    leftStick.put();
                    rightStick.put();
                }
                if (flag) {
                    setState(PhilosopherState.THINKING);
                    think();
                }
            }
        } catch (InterruptedException ignored) {
        }
    }

    public void stop() {
        isStopped = true;
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
