package utm.so.individual.problem.chandry_misra;

import utm.so.individual.App;
import utm.so.individual.controllers.mainController;
import utm.so.individual.problem.base.BaseSimulation;
import utm.so.individual.utils.Setup;
import utm.so.individual.utils.PhilosopherUtils.IHaveState;

public class Simulation extends BaseSimulation{
    private static Philosopher[] philosophers = new Philosopher[Setup.N_F];

    public static Philosopher[] getPhilosophers() {
        return philosophers;
    }
    public static void start() throws InterruptedException {
        System.out.println(System.nanoTime() + ": Dinner is started");

        ChopStick[] sticks = new ChopStick[Setup.N_F];
        for (int i = 0; i < sticks.length; i++) {
            sticks[i] = new ChopStick(i + 1);
        }
        threads = new Thread[Setup.N_F];
        philosophers = new Philosopher[Setup.N_F];
        mainController.canvas.setPhilosophers((IHaveState[]) philosophers);

        for (int i = 0; i < philosophers.length; i++) {
            ChopStick leftStick = sticks[i];
            ChopStick rightStick = sticks[(i + 1) % sticks.length];

            philosophers[i] = new Philosopher(i, leftStick, rightStick);

            threads[i] = new Thread(philosophers[i], "Philosopher " + (i + 1));
            threads[i].start();
        }

        waitTime(Setup.L * 1000);
        stopPhilosophers(philosophers);
        stopThreads(threads);

        System.out.println(System.nanoTime() + ": Dinner is finished");
        printStats(philosophers);
    }

    private static void stopPhilosophers(Philosopher[] philosophers) throws InterruptedException {
        for (Philosopher philosopher: philosophers) {
            if (!philosopher.isStopped) {
                philosopher.stop();
            }
        }
        waitTime(200);
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
