package interpreter;

import java.util.List;

/**
 * User: gsunderam
 * Date: Jul 20, 2011
 */
public class BookAuthorExpression implements BookAbstractExpression {

  public String interpret(BookInterpreterContext ctx) {
    List<String> authors = ctx.getAuthors();
    StringBuilder authorBuffer = new StringBuilder(100);

    boolean first = true;
    for (String author : authors) {
      if (!first) authorBuffer.append(", ");
      else first = false;
      authorBuffer.append(author);
    }

    return authorBuffer.toString();
  }
}
