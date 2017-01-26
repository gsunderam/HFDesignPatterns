package interpreter;

/**
 * User: gsunderam
 * Date: Jul 21, 2011
 */
public class InvalidBookQueryExpression implements BookAbstractExpression {
  public String interpret(BookInterpreterContext ctx) {
    return "No interpretations available for this query in our context. Let the librarian know";
  }
}
