package lab_6.menu;

import java.util.Scanner;

import lab_6.solutions.test.SolutionBTest;

public class ProgrammMenu {
  private static boolean fContinue = true;
  private static boolean fContinueL2 = true;
  private static final Scanner scanner = new Scanner(System.in);
  private static final Menu mainMenu = new Menu();

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
    mainMenu.add("Start program", new MenuCallback() {
      public void Invoke() {
        startHandler();
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

  public static void backHandler() {
    fContinueL2 = false;
  }

  public static void executeHandler() {
    SolutionBTest.test();
  }

  public static void startHandler() {
    fContinueL2 = true;
    Menu menu = new Menu();

    menu.add("Do job", new MenuCallback() {
      public void Invoke() {
        executeHandler();
        System.out.println("\nPress enter to continue...");
      }
    });
    menu.add("Back", new MenuCallback() {
      public void Invoke() {
        backHandler();
      }
    });

    while (fContinueL2) {
      ConsoleUtils.clearConsole();
      System.out.println("Problem: Please choose an option.");
      menu.show();
    }
  }

  /**
   * @brief the program entry point
   * @param args the command line arguments
   */
  public static void showMenu() {

    while (fContinue) {
      ConsoleUtils.clearConsole();
      System.out.println("Please choose an option.");
      mainMenu.show();
    }

    System.exit(0);

  }

}