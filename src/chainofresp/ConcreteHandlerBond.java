package chainofresp;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 19, 2011
 */
public class ConcreteHandlerBond implements Handler {
  /**
   * Handle the request if possible, other wise pass it along if else chain.
   *
   * @param request
   */
  public void processRequest(Request request) {
    switch (request) {
       case BOND_ORDER: {
        handleIt(request);
        break;
       }
       default: {
         stdout("OK its not bond order or equity, so passing it to default handler");
         break;
      }
    }
  }

  private void handleIt(Request request) {
    stdout("Ha! haa! request handled by " + this.getClass().getSimpleName());
  }
}
