package lab_6.solutions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

import lab_6.library.Book;
import lab_6.library.Library;
import lab_6.reader.IReader;
import lab_6.writer.IWriter;

public class SolutionB {
  private Semaphore orderMutex;
  private Semaphore read;
  private Semaphore write;
  private int readCount;

  public SolutionB() {
    readCount = 0;
    orderMutex = new Semaphore(1, true);
    read = new Semaphore(1, true);
    write = new Semaphore(1, true);
  }

  public static Library library = new Library(
      new ArrayList<>(
          Arrays.asList(new Book("The Iliad", "1"), 
                        new Book("The Odyssey", "2"), 
                        new Book("Pride and Prejudice", "3"),
                        new Book("One Hundred Years of Solitude", "4"),
                        new Book("The Count of Monte Cristo", "5"),
                        new Book("The Picture of Dorian Gray", "6"),
                        new Book("1984", "7"),
                        new Book("Three Comrades", "8")
                        )));

  public class ReaderImpl implements IReader {
    private int booksRead = 0; 
    public boolean read(String bookName) throws InterruptedException {
        orderMutex.acquire();

        read.acquire();
        try {
          readCount++;
          if (readCount == 1) {
            write.acquire();
          }

          if (orderMutex.hasQueuedThreads()) {
            System.out
                .println("There are "
                    + orderMutex.getQueueLength()
                    + " waiting Threads on the orderMutex semaphore during reading ");
          }
        } finally {
          orderMutex.release();
          read.release();
        }

        Book book;

        if(booksRead != 0) {
          int nextBook = (new Random()).nextInt(library.size());
          book = library.get(nextBook);
        }
        else {
          book = library.getBook(bookName);
        }

        Thread.sleep(1000);

        System.out.println("Reads " + book.getName() + " book text:"
            + book.getText() + ", read by "
            + Thread.currentThread().getName());

        read.acquire();
        try {
          readCount--;
          if (readCount == 0) {
            write.release();
          }
        } finally {
          read.release();
        }
      return ++booksRead < library.size();
    }
  }

  public class WriterImpl implements IWriter {
    private int booksWritten = 0;
    public boolean write(String bookName, String text) throws InterruptedException {
      orderMutex.acquire();
      try {
        write.acquire();

        if (orderMutex.hasQueuedThreads()) {
          System.out
              .println("There are "
                  + orderMutex.getQueueLength()
                  + " waiting Threads on the orderMutex semaphore during writing ");
        }
      } finally {
        orderMutex.release();
      }

      try {
        Thread.sleep(1000);
        
        Book book;

        if(booksWritten != 0) {
          int nextBook = (new Random()).nextInt(library.size());
          book = library.get(nextBook);
        }
        else {
          book = library.getBook(bookName);
        }

        book.addText(text);

        System.out.println("Writes to " + book.getName() + " text: " + text
            + ", Writer Thread: " + Thread.currentThread().getName());
      } finally {
        write.release();
      }
      return ++booksWritten < library.size();
    }
  }

  public void test() {
    System.out.print(library);
    ;
  }
}
