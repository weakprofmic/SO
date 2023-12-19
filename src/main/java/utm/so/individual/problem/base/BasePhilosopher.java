package utm.so.individual.problem.base;

import utm.so.individual.App;
import utm.so.individual.utils.CommonUtils;
import utm.so.individual.utils.Setup;

public class BasePhilosopher {
  protected BaseChopStick leftStick;
  protected BaseChopStick rightStick;

  public int eatCount = 0;

  protected BasePhilosopher() {
  }

  protected BasePhilosopher(BaseChopStick l, BaseChopStick r) {
    leftStick = l;
    rightStick = r;
  }

  protected void eat() throws InterruptedException {
    System.out.println("I e ");

    if (Setup.EM_L > 0)
      Thread.sleep(Setup.EM_L * 1000 + CommonUtils.getRandomInt((Setup.EMX_L - Setup.EM_L) * 1000));

    ++eatCount;
  }

  protected void think() throws InterruptedException {
    System.out.println("I t ");

    if (Setup.TM_L > 0)
      Thread.sleep(Setup.TM_L * 1000 + CommonUtils.getRandomInt((Setup.TMX_L - Setup.TM_L) * 1000));
  }
}
