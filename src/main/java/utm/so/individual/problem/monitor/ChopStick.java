package utm.so.individual.problem.monitor;

import utm.so.individual.problem.base.BaseChopStick;

public class ChopStick extends BaseChopStick{
  public ChopStick(int id) {
    super(id);
  }

  private boolean isAvailable = true;

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }
}
