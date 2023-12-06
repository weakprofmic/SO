package lab_6.library;
public class Book {
  private String name;
  private String id;
  private String text;

  public Book(String name, String id) {
    this.name = name;
    this.id = id;
    this.text = "";
  }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    Book book = (Book) obj;
    if (book.id == this.id) {
      // book.setName(this.name); collision of objs
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "Book [name=" + name + ", id=" + id + "]";
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void addText(String text) {
    if (this.text != null)
      this.text += " " + text;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
