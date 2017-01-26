package interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import static log.Logger.print;
import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 19, 2011
 *
 * This is the context class that stores the information that will be "INTERPRETED" by means of a "GRAMMER"
 */
public class BookInterpreterContext {
  private List<String> titles = new ArrayList<String>();
  private List<String> authors = new ArrayList<String>();
  private List<TitleAndAuthor> titleAndAuthors = new ArrayList<TitleAndAuthor>();

  public void addTitle(String title) {
    titles.add(title);
  }

  public void addAuthor(String author) {
    authors.add(author);
  }

  public void addTitleAndAuthor(String title, String author) {
    titleAndAuthors.add(new TitleAndAuthor(title, author));
  }

  public List<String> getTitles() {
    return titles;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public List<TitleAndAuthor> getTitleAndAuthors() {
    return titleAndAuthors;
  }

  public List<String> getAuthorsForTitle(String title) {
    List<String> authorsForTitle = Collections.emptyList();
    //TODO: ListIterator is specific to Lists. Read about this from the Sun java 6 (aka. 1.6) API Docs
    ListIterator<TitleAndAuthor> itList = titleAndAuthors.listIterator();
    if (itList.hasNext()) authorsForTitle = new ArrayList<String>(); //hasNext doesn't move the cursor

    TitleAndAuthor titleAndAuthor;
    while (itList.hasNext()) {
      titleAndAuthor = itList.next(); //ONLY next moves the pointer to the next cursor position
      if (titleAndAuthor.getTitle().equalsIgnoreCase(title)) {
        authorsForTitle.add(titleAndAuthor.getAuthor());
      }
    }
    return authorsForTitle;
  }

   public List<String> getTitlesForAuthor(String author) {
    List<String> titleForAuthors = Collections.emptyList();
    //TODO: ListIterator is specific to Lists. Read about this from the Sun java 6 (aka. 1.6) API Docs
    ListIterator<TitleAndAuthor> itList = titleAndAuthors.listIterator();
    if (itList.hasNext()) titleForAuthors = new ArrayList<String>(); //hasNext doesn't move the cursor

    TitleAndAuthor titleAndAuthor;
    while (itList.hasNext()) {
      titleAndAuthor = itList.next(); //ONLY next moves the pointer to the next cursor position
      if (titleAndAuthor.getAuthor().equalsIgnoreCase(author)) {
        titleForAuthors.add(titleAndAuthor.getTitle());
      }
    }
    return titleForAuthors;
  }

  /**'
   * Print the author iterator in reverse order using previous
   */
  public void funWithListIterators() {
    stdout("Let's learn about the new ListIterator in Java 5. We can do previous as well");
    ListIterator<String> it = authors.listIterator();
    while (it.hasNext()) {
       //Add logic
      it.next();
    }
    stdout("--------------Printing the iterator in the REVERSE order!--------------------------");
    boolean first = true;
    while (it.hasPrevious()) {
      if (!first) print(", ");
      else first = false;
      print(it.previous());
    }
  }
}
