// package lab_6.solutions;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.HashSet;
// import java.util.LinkedList;
// import java.util.concurrent.Semaphore;

// import lab_6.library.Book;
// import lab_6.library.Library;

// public class SolutionA {
//   private Semaphore orderMutex;
//   private Semaphore read;
//   private Semaphore write;
//   private int readCount;

//   public SolutionA() {
//     readCount = 0;
//     orderMutex = new Semaphore(1, true);
//     read = new Semaphore(1, true);
//     write = new Semaphore(1, true);
//   }

//   Library library = new Library(
//       new HashSet<>(Arrays.asList(new Book("Anna Karenina", "1"), new Book("The Dune", "2"), new Book("Odyssey", "3"))));

//     public class ReaderImpl implements IReader {
//     public void read(String bookName) throws InterruptedException {
//       orderMutex.acquire();

//       read.acquire();
//       try {
//         readCount++;
//         if (readCount == 1) {
//           write.acquire();
//         }

//         if (orderMutex.hasQueuedThreads()) {
//           System.out
//               .println("There are "
//                   + orderMutex.getQueueLength()
//                   + " waiting Threads on the orderMutex semaphore during reading ");
//         }
//       } finally {
//         orderMutex.release();
//         read.release();
//       }

//       // Read the schedules...

//       Thread.sleep(1000);

//       System.out.println("Reads a " + bookName + " book text:"
//           + library.getBook(bookName).getText() + " read by "
//           + Thread.currentThread().getName());

//       // Reading done

//       read.acquire();
//       try {
//         readCount--;
//         if (readCount == 0) {
//           write.release();
//         }
//       } finally {
//         read.release();
//       }
//     }
//   }

//   class WriterImpl implements IWriter {
//     public void write(String bookName, String text) throws InterruptedException {
//       orderMutex.acquire();
//       try {
//         write.acquire();

//         if (orderMutex.hasQueuedThreads()) {
//           System.out
//               .println("There are "
//                   + orderMutex.getQueueLength()
//                   + " waiting Threads on the orderMutex semaphore during writing ");
//         }
//       } finally {
//         orderMutex.release();
//       }
//       // Writing/Updating new/existing schedules

//       try {
//         Thread.sleep(1000);

//         Book book = library.getBook(bookName);
//         book.addText(text);

//         System.out.println("Writes a " + bookName + " text: " + text
//             + " by Writer Thread: " + Thread.currentThread().getName());

//         // Writing done
//       } finally {
//         write.release();
//       }
//     }
//   }

//   public void test() {
//     System.out.print(library);
//     ;
//   }
// }
