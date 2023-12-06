package lab_6.solutions.test;

import lab_6.reader.Reader;
import lab_6.solutions.SolutionB;
import lab_6.writer.Writer;

public class SolutionBTest {
  static final int X = 3;
	static final int Y = 8;
	static final int Z = 8;

  public static void test() {
		Thread[] users = new Thread[X + Y];

    SolutionB s = new SolutionB();

		users[1] = new Thread(new Reader(s.new ReaderImpl(), SolutionB.library.get(0).getName()));
		users[2] = new Thread(new Writer(s.new WriterImpl(), SolutionB.library.get(7).getName(), "Hello"), "Marcel Proust");
		users[0] = new Thread(new Writer(s.new WriterImpl(), SolutionB.library.get(3).getName(), "World"), "Mark Twen");
		users[3] = new Thread(new Reader(s.new ReaderImpl(), SolutionB.library.get(1).getName()));
		users[4] = new Thread(new Reader(s.new ReaderImpl(), SolutionB.library.get(0).getName()));
		users[6] = new Thread(new Reader(s.new ReaderImpl(), SolutionB.library.get(0).getName()));
		users[8] = new Thread(new Reader(s.new ReaderImpl(), SolutionB.library.get(1).getName()));
		users[7] = new Thread(new Reader(s.new ReaderImpl(), SolutionB.library.get(1).getName()));
		users[5] = new Thread(new Writer(s.new WriterImpl(), SolutionB.library.get(0).getName(), "of"), "Jack London");
		users[9] = new Thread(new Reader(s.new ReaderImpl(), SolutionB.library.get(3).getName()));
		users[10] = new Thread(new Reader(s.new ReaderImpl(), SolutionB.library.get(3).getName()));

		for (int i = 0; i < X + Y; i++) {
			users[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

		for (int i = 0; i < X + Y; i++) {
			try {
				users[i].join();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		System.out.println("Completed...");
	}
}
