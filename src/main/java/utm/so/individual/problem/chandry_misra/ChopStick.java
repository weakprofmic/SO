package utm.so.individual.problem.chandry_misra;

import utm.so.individual.problem.base.BaseChopStick;

public class ChopStick extends BaseChopStick {
  public ChopStick(int id) {
    super(id);
  }

  private boolean isDirty = true;

  public void setDirty(boolean isDirty) {
    this.isDirty = isDirty;
  }



  public synchronized boolean request() {
    return isDirty;
  }

  

  public void take() {
    super.take();
    setDirty(false);
  }

  public void put() {
    super.put();
    setDirty(true);
  }
}
