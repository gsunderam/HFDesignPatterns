package myducksimulator;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 *
 * No Add methods here as adding to a duck doesn't make sense. Add is only appliocable to the
 * composite Flock. Made this diuck to be an Observable
 */
public class DuckCall extends QuackObservable implements Quackable {

  public void quack() {
    stdout("Duck call Kwak");
    setChanged();
    notifyObservers();
  }

  public String getName() {
    return "duck Call";
  }
}
