package state;

/**
 * User: gsunderam
 * Date: Jun 28, 2011
 */
public interface State {
  public void insertQuarter();
  public void ejectQuarter();
  public void turnCrank();
  public void dispense();
}
