package interpreter;

import java.util.List;

/**
 * User: gsunderam
 * Date: Jul 20, 2011
 *
 * These are the kinds of expression an user can possibly ask to be interpreted
 */
public class BookTitleExpression implements BookAbstractExpression {
  
  public String interpret(BookInterpreterContext ctx) {
    List<String> titles = ctx.getTitles();
    StringBuilder titleBuffer = new StringBuilder(100);

    //Nice way to put a comma
    boolean first = true;
    for (String author : titles) {
      if (!first) titleBuffer.append(", ");
      else first = false;
      titleBuffer.append(author);
    }

    return titleBuffer.toString();
  }
}
