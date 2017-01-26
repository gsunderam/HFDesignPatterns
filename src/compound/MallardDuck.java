package compound;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 *
 * no need to change the ducks to keep track of quack counts. That's why decorators are there for
 * See QuackCounter that does this. it decorates individual ducks
 */
public class MallardDuck implements Quackable {
  ObservableHelper helper = new ObservableHelper(this);

  public String getName() {
    return "Mallard";
  }

  public void quack() {
    stdout("Mallard Duck Quacked");
    notifyObservers();
  }

  public void addObserver(QuackObserver observer) {
     helper.addObserver(observer);
  }

  public void notifyObservers() {
    helper.notifyObservers();
  }
}
 