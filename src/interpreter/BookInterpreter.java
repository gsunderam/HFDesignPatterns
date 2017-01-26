package interpreter;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 20, 2011
 */
public class BookInterpreter {

  private BookInterpreterContext ctx;

  public BookInterpreter(BookInterpreterContext ctx) {
    this.ctx = ctx;
  }

  /**
   * Interpret the expression and get the result from the ctx. Basically expressions can be one of
   *
   * list titles
   * list authors
   * list authors for title <author-name>
   * 
   * This function will interpret the above three inputs and fetch the result from the functions in the
   * context. Basically this instantitiates the correct interpreter based on the input grammer
   *
   * @param userInput
   * @return
   */
  public String interpret(String userInput) {
    char mainQuery = ' ';
    char subquery = ' ';
    boolean forUsed = false;
    String searchString = null;
    boolean searchStarted = false;
    boolean searchEnded = false;
    
    String [] expressions = userInput.split("\\s");

    for (String token : expressions) {
      if (token.equals("list")) continue;
      else if (token.startsWith("title")) {
        if (mainQuery == ' ') mainQuery = 'T';
        else if (subquery == ' ' && forUsed) subquery = 'T';

      } else if (token.startsWith("author")) {
         if (mainQuery == ' ') mainQuery = 'A';
         else if (subquery == ' ' && forUsed) subquery = 'A';

      } else if (token.equalsIgnoreCase("for")) {
        forUsed = true;

      } else if (searchString == null && subquery != ' ' && token.startsWith("<")) {
        searchStarted = true;    //for title
        searchString = token;
        if (token.endsWith(">")) {
          searchEnded = true;
        }

      } else if (searchStarted && !searchEnded) { //For Firstname Lastname
        searchString += " " + token;
        if (token.endsWith(">")) {
          searchEnded = true;
        }
      }

    }
    stdout("Main Query: " + mainQuery + " Sub Query: " + subquery);
    if (searchString != null) {
      searchString = searchString.substring(1, searchString.length() - 1); //remove <>
    }

    BookAbstractExpression expression = null;
    //write case statements to branch based on main and sub query
    switch(mainQuery) {
      case 'A' : {
        switch(subquery) {
          case 'T': {
            expression = new BookAuthorTitleExpression(searchString); //Terminal expression
            break;
          }
          default: expression = new BookAuthorExpression(); //Non terminal expression 
        }
      }
      break;
      case 'T': {
        switch(subquery) {
          case 'A': {
             expression = new BookTitleAuthorExpression(searchString);
             break;
          }
          default: expression = new BookTitleExpression();
        }

      }
      break;
      default: expression = new InvalidBookQueryExpression();
    }

    stdout("Search string for the interpreter is : " + searchString);

    StringBuilder result = new StringBuilder(100);
    result.append(expression.interpret(ctx)); //Delegates to the correct interpreter
    return result.toString();
  }
}
