package compound;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 *
 * No Add methods here as adding to a duck doesn't make sense. Add is only appliocable to the
 * composite Flock. Made this diuck to be an Observable
 */
public class DuckCall implements Quackable {
  ObservableHelper helper = new ObservableHelper(this);

  public void quack() {
    stdout("Duck call Kwak");
    notifyObservers();
  }

  public String getName() {
    return "duck Call";
  }

  public void addObserver(QuackObserver observer) {
    helper.addObserver(observer);
  }

  public void notifyObservers() {
    helper.notifyObservers();
  }
}
