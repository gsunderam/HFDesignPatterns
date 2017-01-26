package interpreter;

import java.util.List;

/**
 * User: gsunderam
 * Date: Jul 21, 2011
 */
public class BookTitleAuthorExpression implements BookAbstractExpression {
  private String author;

  public BookTitleAuthorExpression(String author) {
    this.author = author;
  }

  public String interpret(BookInterpreterContext ctx) {
    List<String> titlesForAuthor = ctx.getTitlesForAuthor(author);
    StringBuilder titlesForAuthorBuffer = new StringBuilder(100);

    boolean first = true;
    for (String title : titlesForAuthor) {
      if (!first) titlesForAuthorBuffer.append(", ");
      else first = false;
      
      titlesForAuthorBuffer.append(title);
    }
    return titlesForAuthorBuffer.toString();
  }
}
