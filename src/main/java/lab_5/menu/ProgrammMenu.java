package lab_5.menu;
import java.io.IOException;
import java.util.Scanner;

import lab_5.problem.Problems;
import lab_5.utils.ConsoleUtils;

import static lab_5.utils.ConsoleUtils.*;

public class ProgrammMenu {
  private static boolean fContinue = true;
  private static boolean fContinueProblems = true;
  private static boolean fContinueProblem = true;
  private static final Scanner scanner = new Scanner(System.in);
  private static final Menu mainMenu = new Menu();
  private static final Problems problems = initProblems();

  public static Problems getProblems() {
    return problems;
  }

  static Problems initProblems() {
    try {
      return new Problems();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  static {
    initMainMenu();
  }

  public static Menu getMainMenu() {
    return mainMenu;
  }

  public static boolean isNotClosed() {
    return fContinue;
  }

  public static void initMainMenu() {
    mainMenu.add("Solve problems", new MenuCallback() {
      public void Invoke() {
        problemsHandler();
      }
    });
    mainMenu.add("Exit", new MenuCallback() {
      public void Invoke() {
        exitHandler();
      }
    });
  }

  public static void run() {
    fContinue = false;
  }

  /**
   * @brief Handle Menu exit choice
   */
  public static void exitHandler() {
    fContinue = false;
    scanner.close();
  }

  public static void SolveHandler(int problemId) {
    if (problems != null)
      problems.solve(problemId);
  }

  public static void ReadSolveHandler(int problemId) {
    if (problems != null)
      problems.readSolve(problemId);
  }

  public static void ProblemHandler(int problemId) {
    fContinueProblem = true;
    Menu menu = new Menu();

    menu.add("Solve", new MenuCallback() {
      public void Invoke() {
        SolveHandler(problemId);
      }
    });
    menu.add("Read new and Solve", new MenuCallback() {
      public void Invoke() {
        ReadSolveHandler(problemId);
      }
    });
    menu.add("Back", new MenuCallback() {
      public void Invoke() {
        backHandlerL2();
      }
    });

    while (fContinueProblem) {
      // ConsoleUtils.clearConsole();
      if (problems != null) {
        problems.describe(problemId);
      }
      System.out.println("Problem: Please choose an option.");
      menu.show();
    }
  }

  /**
   * @brief Handle Menu back choice
   */
  public static void backHandler() {
    fContinueProblems = false;
  }

  public static void backHandlerL2() {
    fContinueProblem = false;
  }

  /**
   * @brief Handle Menu item2 choice
   */
  public static void problemsHandler() {
    fContinueProblems = true;
    Menu menu = new Menu();
    // System.out.println(problems);
    for (int k : problems.getProblemsRepo().keySet()) {
      menu.add("Problem " + k, new MenuCallback() {
        public void Invoke() {
          ProblemHandler(k);
        }
      });
    }
    menu.add("Back", new MenuCallback() {
      public void Invoke() {
        backHandler();
      }
    });

    while (fContinueProblems) {
      // ConsoleUtils.clearConsole();
      System.out.println("Action: Please choose an option.");
      menu.show();
    }
  }

  /**
   * @brief the program entry point
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    while (fContinue) {
      ConsoleUtils.clearConsole();
      System.out.println("Please choose an option.");
      mainMenu.show();
    }

    System.exit(0);

  }

}