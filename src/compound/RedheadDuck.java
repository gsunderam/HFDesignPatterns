package compound;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 */
public class RedheadDuck implements Quackable {
  ObservableHelper helper = new ObservableHelper(this);

  public void quack() {
    stdout("Read head duck quacked");
    notifyObservers();
  }

  public String getName() {
    return "Red Head";
  }

  public void addObserver(QuackObserver observer) {
   helper.addObserver(observer);
  }

  public void notifyObservers() {
   helper.notifyObservers();
  }
}
