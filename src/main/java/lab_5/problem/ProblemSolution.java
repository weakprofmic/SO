package lab_5.problem;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProblemSolution{
  static final ProblemsSolutions pss = new ProblemsSolutions();
  Method solution;

  ProblemSolution(Method solution) {
    this.solution = solution;
  }
 
  public Object run(Object... t) {
    // System.out.println(t[0] + " First argument");
    try {
      return solution.invoke(pss, t);
    } catch (IllegalAccessException | InvocationTargetException | java.lang.IllegalArgumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  
}