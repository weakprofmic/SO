package utm.so.individual.problem.naive;

import javafx.application.Platform;
import utm.so.individual.App;
import utm.so.individual.controllers.mainController;
import utm.so.individual.problem.base.BaseChopStick;
import utm.so.individual.problem.base.BaseSimulation;
import utm.so.individual.utils.PhilosopherUtils.IHaveState;
import utm.so.individual.utils.PhilosopherUtils.PhilosopherState;
import utm.so.individual.utils.Setup;

public class Simulation extends BaseSimulation {
    private static Philosopher[] philosophers = new Philosopher[Setup.N_F];

    public static Philosopher[] getPhilosophers() {
        return philosophers;
    }

    public static void start() {
        System.out.println(System.nanoTime() + ": Dinner is started");

        BaseChopStick[] forks = new BaseChopStick[Setup.N_F];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new BaseChopStick(i + 1);
        }
        threads = new Thread[Setup.N_F];
        philosophers = new Philosopher[Setup.N_F];
        mainController.canvas.setPhilosophers((IHaveState[]) philosophers);
        
        for (int i = 0; i < philosophers.length; i++) {
            BaseChopStick leftFork = forks[i];
            BaseChopStick rightFork = forks[(i + 1) % forks.length];

            philosophers[i] = new Philosopher(leftFork, rightFork);

            threads[i] = new Thread(philosophers[i], "Philosopher " + (i + 1));
            threads[i].start();
        }

        waitTime(Setup.L * 1000);
        stopThreads(threads);
        try {
            stopPhilosophers(philosophers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(System.nanoTime() + ": Dinner is finished");
        printStats(philosophers);
    }

    private static void stopPhilosophers(Philosopher[] philosophers) throws InterruptedException {
        for (Philosopher philosopher : philosophers) {
            philosopher.setState(PhilosopherState.INACTIVE);
        }
        Platform.runLater(() -> mainController.canvas.draw());
    }

    private static void printStats(Philosopher[] philosophers) {
        int totalCount = 0;
        for (Philosopher philosopher : philosophers) {
            totalCount += philosopher.eatCount;
        }
        if (totalCount > 0) {
            System.out.println("Eating stats:");
            System.out.println("Total: " + totalCount);
            for (int i = 0; i < philosophers.length; i++) {
                System.out.println(
                        "Philosopher " + (i + 1) + ": " + (100.0 * philosophers[i].eatCount / totalCount) + "%");
            }
        }
    }

}
