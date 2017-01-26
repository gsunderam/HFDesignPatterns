package chainofresp;

/**
 * User: gsunderam
 * Date: Jul 19, 2011
 */
public class NotAvailableTypeException extends RuntimeException {

  public NotAvailableTypeException(String msg) {
    super(msg);
  }

  public NotAvailableTypeException(String msg, Throwable t) {
    super(msg, t);
  }
}
