package compound;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * User: gsunderam
 * Date: Jul 25, 2011
 *
 * This is a helper class designed to aid with adding and notifying observers
 */
public class ObservableHelper implements QuackObservable {
  List<QuackObserver> observers = new ArrayList<QuackObserver>();
  QuackObservable duck;

  public ObservableHelper(QuackObservable duck) {
    this.duck = duck;
  }

  public String getName() {
    return null;
  }

  public void addObserver(QuackObserver observer) {
    observers.add(observer);
  }

  public void notifyObservers() {
    for (QuackObserver observer : observers) {
      observer.update(duck);
    }
  }
}
