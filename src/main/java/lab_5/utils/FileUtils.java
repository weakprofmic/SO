package lab_5.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {

  public static String findInString(String regex, String text) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    int matches = 0;
    if (matcher.find()) {
      System.out.println(matcher.group("coeff"));
      return matcher.group("coeff");
    }
    return "No occurencies";
  }

  private String readFromInputStream(InputStream inputStream)
      throws IOException {
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    }
    return resultStringBuilder.toString();
  }

  public String readFromFile(String filename) {
    // String expectedData = "Hello, world!";
    // System.out.println(filename);
    String data = null;

    // clazz.getResourceAsStream("/fileTestw.txt");
    try (InputStream inputStream = new FileInputStream(filename)) {
      data = this.readFromInputStream(inputStream);
    } catch (Exception e) {

    }

    return data;

  }

  public static ArrayList<File> listFilesForFolder(final File folder) {
    ArrayList<File> files = new ArrayList<>();
    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        files.addAll(listFilesForFolder(fileEntry));
      } else {
        files.add(fileEntry);
      }
    }
    return files;
  }

  public static String[] getNames(Class<? extends Enum<?>> e) {
    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
  }

  public static <T> boolean contains(T[] array, T value) {
    for (T element : array) {
      System.out.println(element + " ? " + value);
      if ((T) element == (T) value) {
        System.out.println(element + " == " + value);
        return true;
      }
    }
    return false;
  }

}