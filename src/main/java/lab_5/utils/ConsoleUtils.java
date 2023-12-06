package lab_5.utils;
public class ConsoleUtils {
  public final static void clearConsole() {
    try {
      final String os = System.getProperty("os.name");
      if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        new ProcessBuilder("clear").inheritIO().start().waitFor();
      }
    } catch (final Exception e) {
      e.printStackTrace();
    } finally {
      System.out.print("\033[H\033[2J");
      System.out.flush();
    }
  }
}
