package utm.so.individual.utility;

import java.util.*;

import utm.so.individual.utility.RaceState.Stage;
import utm.so.individual.utility.RaceState.State;

public class Race {
  private ArrayList<Athlete> participants;

  public List<Athlete> getParticipants() {
    return Collections.unmodifiableList(participants);
  }

  private Course course;

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  private RaceState raceState = new RaceState();

  public RaceState getRaceState() {
    return raceState;
  }

  public void setRaceState(RaceState raceState) {
    this.raceState = raceState;
  }

  public Race() {
    this.participants = new ArrayList<>();
    participants.add(new Athlete("A", 1000, 1000, 1000));
    course = new Course("Default");
  };

  public void start() throws InterruptedException {
    raceState.setState(State.GOING);
    raceState.setStage(Stage.SWIMMING);

    Thread race = new Thread(new Runnable() {

      @Override
      public void run() {
        while (true) {
          int stageFinishers = 0;
          for (Athlete participant : participants) {
            if (participant.getStage() == raceState.nextStage()
                || participant.getStage() == RaceState.nextStage(raceState.nextStage())) {
              ++stageFinishers;
            }
          }
          if (stageFinishers == participants.size()) {
            raceState.setStage(raceState.nextStage());
            if (raceState.getStage() == Stage.DEFAULT) {
              raceState.setState(State.FINISHED);
              break;
            }
          }
        }
        // System.out.println(raceState.getState());
        // Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        // // iterating over the threads to get the names of
        // // all the active threads
        // for (Thread x : threadSet) {
        //   System.out.println(x.getName());
        // }
      }

    }, "Race");

    race.start();

    for (int i = 0; i < participants.size(); ++i) {
      participants.get(i).setParticipantOf(this);
      participants.get(i).race();
    }
  }

  public Race(ArrayList<Athlete> participants, String courseName) {
    this.participants = new ArrayList<>(participants);
    course = new Course(courseName);
  };
}
