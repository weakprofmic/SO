package lab_6.library;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Library extends AbstractCollection<Book>{
  private Collection<Book> c;

  public Library(Collection<Book> c) {
    this.c = c;
  }

  @Override
  public Iterator<Book> iterator() {
    return c.iterator();
  }

  @Override
  public int size() {
    return c.size();
  }

  @Override
  public boolean add(Book e) {
    return c.add(e);
  }


  public Book getBook(String name) {
    Book book = c.stream()
    .filter( b -> b.getName() == name ).findFirst().orElse(null);
    return book;
  }

  public Book get(int index) {
    try {
      ArrayList<Book> al = (ArrayList<Book>)c;
      return al.get(index);
    } catch(Exception e){
      return null;
    }
  }


}
