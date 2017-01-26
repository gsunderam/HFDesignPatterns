package proxy;

import java.io.Serializable;

/**
 * User: gsunderam
 * Date: Jun 28, 2011
 */
public interface State extends Serializable {
  public void insertQuarter();
  public void ejectQuarter();
  public void turnCrank();
  public void dispense();
}
