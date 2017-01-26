package myducksimulator;

import java.util.Observable;
import java.util.Observer;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 25, 2011
 *
 * This is the quackalogist and he is an Observer
 */
public class Quackalogist implements Observer {
  private volatile static int quackCount;

  /**
   * Java will call this method when a quack occurs. "Pull" type as the Observer pulls the data
   * it needs from the Observable object
   *
   * @param flockOrDuck
   * @param args
   */
  public void update(Observable flockOrDuck, Object args) {
    if (flockOrDuck instanceof QuackObservable) {
      ++quackCount;
      stdout(getClass().getSimpleName() + " ****Quack ALERT****: Quack by " + ((QuackObservable) flockOrDuck).getName());
    }
  }

  public static int getQuackCount() {
    return quackCount;
  }
}
