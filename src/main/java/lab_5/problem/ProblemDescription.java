package lab_5.problem;
import java.net.URL;

import lab_5.Main;
import lab_5.utils.FileUtils;

public class ProblemDescription {
  String description;

  ProblemDescription(String problemId) {
    description = initDescription(problemId);
  }

  String initDescription(String problemId) {
    String preDescription = "No description for this problem";
    String description = preDescription;
    URL path = Main.class.getClassLoader().getResource("descriptions/" + problemId + ".txt");
    // System.out.println(path);
    if (path != null)
      description = (new FileUtils()).readFromFile(path.getPath());
    // System.out.println("descriptions\\" + problemId + ".txt");
    return description == null? preDescription : description;
  }
}