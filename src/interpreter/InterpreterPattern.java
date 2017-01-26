package interpreter;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 20, 2011
 */
public class InterpreterPattern {
  //Authors
  private static final String MYNAME = "G Sunderam";
  private static final String RSAGAR = "Ramanand Sagar";
  private static final String MGANDHI = "Mahatma Gandhi";
  private static final String EHEMMING = "Ernest Hemmingway";
  //titles
  private static final String TITLE1 = "Power of self";
  private static final String TITLE2 = "Doyen of literature";
  private static final String TITLE3 = "Discovery of india";
  private static final String TITLE4 = "Soul and peace";
  private static final String TITLE5 = "Doyen of wisdom";
  private static final String TITLE6 = "God and universe";
  private static final String TITLE8 = "Triumph of love" ;
  private static final String TITLE7 = "Love for life";

  public static void main(String[] args) {
    stdout("---------Interpreter pattern---------------------");
    BookInterpreterContext ctx = new BookInterpreterContext();
    ctx.addAuthor(MYNAME);
    ctx.addAuthor(RSAGAR);
    ctx.addAuthor(MGANDHI);
    ctx.addAuthor(EHEMMING);
    //add titles
    ctx.addTitle(TITLE1);
    ctx.addTitle(TITLE2);
    ctx.addTitle(TITLE3);
    ctx.addTitle(TITLE4);
    ctx.addTitle(TITLE5);
    ctx.addTitle(TITLE6);
    ctx.addTitle(TITLE7);
    ctx.addTitle(TITLE8);
    //associate both
    ctx.addTitleAndAuthor(TITLE1, MYNAME);
    ctx.addTitleAndAuthor(TITLE2, MYNAME);
    ctx.addTitleAndAuthor(TITLE3, RSAGAR);
    ctx.addTitleAndAuthor(TITLE4, RSAGAR);
    ctx.addTitleAndAuthor(TITLE5, MGANDHI);
    ctx.addTitleAndAuthor(TITLE6, MGANDHI);
    ctx.addTitleAndAuthor(TITLE7, EHEMMING);
    ctx.addTitleAndAuthor(TITLE8, EHEMMING);

    BookInterpreter interpreter = new BookInterpreter(ctx);

    //The input grammar gets translated to objects along the way! One of the four possible interpretations
    String result = interpreter.interpret("list titles for authors <" + MYNAME +">");

    stdout("Title(s) for " + MYNAME + " are : " + result);
    stdout("------------------------------------------------");
    stdout("All titles in the library: " + interpreter.interpret("list titles"));
    stdout("------------------------------------------------");
    stdout("Available collection of Authors in the library: " + interpreter.interpret("list authors"));
    stdout("------------------------------------------------");
    stdout("Authors of title " + TITLE6 + " is " + interpreter.interpret("list authors for title <" + TITLE6 + ">"));
    stdout("------------------------------------------------");
    stdout("Alien expression test!: " + interpreter.interpret("list books"));
    stdout("-------------------Interpreter pattern completed------------------------\n");

    ctx.funWithListIterators();

  }
}
