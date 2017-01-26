package bridge;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 23, 2011
 *
 * Action delegates to the correct impl. "Economy" is a "function" and so this class is a FUNCTIONAL
 * ABSTRACTION. Implementation of economy car specific actions will be performed by the economy
 * implementor.
 */
public class EconomyCar implements CarAbstraction {
  CarImplementor implementor = new EconomyCarImplementor("Nissan", "sentra", "Economy");

  public void action() {
   stdout("Do economy specific stuff before invoking impl");
   implementor.actionImplemented();
  }
}
