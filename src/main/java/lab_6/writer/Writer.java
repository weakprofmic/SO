package lab_6.writer;
public class Writer implements Runnable{
  IWriter writer;
  String bookName;
  String text;

  public Writer(IWriter writer, String bookName, String text) {
    this.writer = writer;
    this.bookName = bookName;
    this.text = text;
  }
  @Override
  public void run() {
    try {
      while(writer.write(bookName, text)){};
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
