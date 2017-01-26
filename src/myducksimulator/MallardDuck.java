package myducksimulator;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 *
 * This version of the duck simulator uses a different kind of inheritance. These ducks extend the QuackObservable
 * and so they are BOTH observable AND quackable
 */
public class MallardDuck extends QuackObservable {
  
  public String getName() {
    return "Mallard";
  }

  /**
   * Java's observer will notify the Quackalogist as soon as a quack event occurs
   */
  public void quack() {
    stdout("Mallard Duck Quacked");
    setChanged(); //required per Java docs
    notifyObservers();
  }
}
 