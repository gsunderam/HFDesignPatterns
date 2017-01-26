package chainofresp;

import java.util.EnumSet;
import java.util.Iterator;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 19, 2011
 *
 * Show the chain of responsibility pattern from SCEA book
 */
public class ChainOfResponsibility {
  public static void main(String[] args) {
    stdout("------Showing the chain of responsibity pattern---------");
    //create request
    Request request = Request.EQUITY_ORDER; //use enums where ever possible
    //create handler
    Handler handler = new ConcreteHandlerEquity();
    handler.processRequest(request);
    //create bond order request using the SAME handler reference
    handler = new ConcreteHandlerBond();
    handler.processRequest(Request.BOND_ORDER);

    //As a test, check whether an arbitrary type is a valid request
    String type = "stock";
    stdout("stock is a valid request ? " + (Request.isValidRequest(type) ? "Yes" : "No"));
    stdout("equity is a valid request ? " + (Request.isValidRequest("equity") ? "Yes" : "No"));

  }
}
