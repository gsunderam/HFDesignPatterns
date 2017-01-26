package compound;

import java.util.ArrayList;
import java.util.List;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 25, 2011
 *
 * This is a Flock (Menu) of ducks implemented as a "Composite". Ducks collection is an array list
 * and these are leaf nodes (corresponding to menu items in our earlier example). This is a type safe
 * class i.e. Only Flock can have add method as adding to a "Duck" doesn't make sense. This is in contrast to the
 * earlier menu example where the clients could treat BOTH the Menu and menuItem transparently
 */
public class Flock implements Quackable {
  List<Quackable> ducks = new ArrayList<Quackable>();
  ObservableHelper helper = new ObservableHelper(this);

  public void add(Quackable duck) {
    ducks.add(duck);
  }

  public void quack() {
    for (Quackable duck : ducks) {
      duck.quack();
    }
  }

  public String getName() {
    return "flock of ducks";
  }

  /**
   * Here we call each of the ducks' addobserver for porpogation
   *
   * @param observer
   */
  public void addObserver(QuackObserver observer) {
    for (Quackable duck : ducks) {
      duck.addObserver(observer);
    }
  }

  /**
   * Notification is done by the ducks themselves 
   */
  public void notifyObservers() {}
    
}
