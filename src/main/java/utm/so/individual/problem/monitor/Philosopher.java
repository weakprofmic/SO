package utm.so.individual.problem.monitor;

import utm.so.individual.App;
import utm.so.individual.utils.CommonUtils;
import utm.so.individual.utils.Setup;
import utm.so.individual.utils.PhilosopherUtils.IHaveState;
import utm.so.individual.utils.PhilosopherUtils.PhilosopherState;

public class Philosopher implements Runnable, IHaveState {
    private final int id;
    public int getId() {
        return id;
    }

    private final ChopStick leftStick;
    private final ChopStick rightStick;
    protected int eatCount = 0;

    public PhilosopherState getState() {
        return state.getState(id);
    }

    private final StateManager state;
    public boolean isStopped = false;

    public Philosopher(int id, ChopStick leftStick, ChopStick rightStick, StateManager state) {
        this.id = id;
        this.leftStick = leftStick;
        this.rightStick = rightStick;
        this.state = state;
    }

    @Override
    public void run() {
        try {
            think();
            while (!isStopped) {
                state.takeSticks(id, leftStick, rightStick);
                eat();
                state.putSticks(id, leftStick, rightStick);
                think();
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
