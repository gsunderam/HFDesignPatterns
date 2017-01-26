package myducksimulator;

import compound.ObservableHelper;
import compound.QuackObserver;
import compound.Quackable;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 */
public class RubberDuck extends QuackObservable {

  /**
   * Notify the observer after quack event
   */
  public void quack() {
   stdout("Rubber duck Squeak");
   setChanged();
   notifyObservers();
  }

  public String getName() {
    return "rubber";
  }
}
