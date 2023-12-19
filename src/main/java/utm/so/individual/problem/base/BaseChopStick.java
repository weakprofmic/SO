package utm.so.individual.problem.base;

public class BaseChopStick {

  public BaseChopStick(int id) {
    this.id = id;
  }

  protected int id;

  /* Getters */
  public int getId() {
    return id;
  }

  /* Setters */
  public void setId(int id) {
    this.id = id;
  }

  /* Methods */
  public void take() {
    System.out.println(id + " Been t");
  }

  public void put() {
    System.out.println(id +" Been P");

  }

}
