package interpreter;

import java.util.List;

/**
 * User: gsunderam
 * Date: Jul 20, 2011
 */
public class BookAuthorTitleExpression implements BookAbstractExpression {

  private String title;

  public BookAuthorTitleExpression(String title) {
    this.title = title;
  }

  public String interpret(BookInterpreterContext ctx) {
    List<String> authorsForTitle = ctx.getAuthorsForTitle(title);
    StringBuilder authorsForTitleBuffer = new StringBuilder(100);

    boolean first = true;
    for (String author : authorsForTitle) {
      if (!first) authorsForTitleBuffer.append(", ");
      else first = false;
      authorsForTitleBuffer.append(author);
    }

    return authorsForTitleBuffer.toString();
  }
}
