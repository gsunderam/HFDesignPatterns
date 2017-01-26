package chainofresp;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 19, 2011
 */
public class ConcreteHandlerEquity implements Handler {
  public void processRequest(Request request) {
    switch (request) {
      case EQUITY_ORDER: {
        handleIt(request);
        break;
      }
      case BOND_ORDER: {
        stdout("Creating handler for bond");
        new ConcreteHandlerBond().processRequest(request);
        break;
      }
    }
  }

  private void handleIt(Request request) {
    stdout("Request handled by " + this.getClass().getSimpleName());
  }
}
