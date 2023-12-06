package lab_5.problem;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import lab_5.utils.SolutionUtils;

public class Problems {
  private Map<Integer, Problem> problemsRepo;

  public Map<Integer, Problem> getProblemsRepo() {
    return problemsRepo;
  }

  public Problems() throws IOException {
    // System.out.println("I am here");
    problemsRepo = new HashMap<>();
    try {
      Method[] methods = ProblemsSolutions.class.getDeclaredMethods();
      for (int i = 0; i < methods.length; i++) {
        problemsRepo.put(i, new Problem(methods[i], methods[i].getName()));
      }
    } catch (Throwable e) {
      System.err.println(e);
    }
  }

  static class Problem {
    ProblemDescription pd;
    ProblemSolution ps;

    Problem(Method solution, String problemId) {
      pd = new ProblemDescription(problemId);
      ps = new ProblemSolution(solution);
    }
  }

  public void describe(int problemInd) {
    // System.out.println(problemsRepo);
    System.out.println(problemsRepo.get(problemInd).pd.description);
  }

  public void readSolve(int problemInd) {
    Object[] args = SolutionUtils.getParams(problemsRepo.get(problemInd).ps.solution, true);
    problemsRepo.get(problemInd).ps.run(args);
    System.out.println();
  }

  public void solve(int problemInd) {
    Object[] args = SolutionUtils.getParams(problemsRepo.get(problemInd).ps.solution, false);
    problemsRepo.get(problemInd).ps.run(args);
    System.out.println();
  }
}