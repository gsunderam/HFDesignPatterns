package chainofresp;

/**
 * User: gsunderam
 * Date: Jul 19, 2011
 *
 * Enum that encapsulates valid request types
 */
public enum Request {
  EQUITY_ORDER("equity"), BOND_ORDER("bond");

  private String type;
  
  Request(String type) throws NotAvailableTypeException {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public static boolean isValidRequest(String request) {
   for (Request req : Request.values()) {
     if (req.getType().equalsIgnoreCase(request)) {
       return true;
     }
   }
   return false;
  }
}
