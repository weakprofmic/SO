package lab_6.reader;
public class Reader implements Runnable{
  IReader reader;
  String bookName;

  public Reader(IReader reader, String bookName) {
    this.reader = reader;
    this.bookName = bookName;
  }
  
  @Override
  public void run() {
    try {
      while(reader.read(bookName)){};
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
