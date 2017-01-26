package compound;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 */
public class RubberDuck implements Quackable {
  ObservableHelper helper = new ObservableHelper(this);

  public void quack() {
   stdout("Rubber duck Squeak");
   notifyObservers();
  }

  public String getName() {
    return "rubber";
  }

  public void addObserver(QuackObserver observer) {
    helper.addObserver(observer);
  }

  public void notifyObservers() {
    helper.notifyObservers();
  }
}
