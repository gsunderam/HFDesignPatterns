package compound;

import java.util.Observable;
import java.util.Observer;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 25, 2011
 *
 * This is the quackalogist and he is an Observer
 */
public class Quackalogist implements QuackObserver {
  public void update(QuackObservable flockOrDuck) {
    if (flockOrDuck instanceof QuackObservable) {
      stdout("Quakalogist: ****Quack ALERT****: Quack by " + flockOrDuck.getName());
    }
  }
}
