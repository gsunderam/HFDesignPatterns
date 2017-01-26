package compound;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 *
 * This is a decorator to keep track of the Number of quack counts.
 * Note this implements Quackable. A decorator implements the parent class of the component
 * it tries to decorate. Why? So that we can call methods on the component FROM the decorator
 * polymorphically!
 *
 * This is an observable too. so implement the add and notify methods to delegate calls to the "duck" instance
 * field, so quackalogists can receive notifications of quacks
 */
public class QuackCounter implements Quackable {
  private static int quackCount;
  Quackable duck; //This the reference to the component we decorate i.e Duck

  public QuackCounter(Quackable duck) {
    this.duck = duck;
  }

  /**
   * This delegates to the Actual component - mallard, redhead, duckcall or rubber duck
   */
  public void quack() {
    duck.quack();
    stdout("Incrementing count...");
    quackCount++;
  }

  public static int getQuackCount() {
    return quackCount;
  }

  public String getName() {
    return null;
  }

  /**
   * This gets delegated to the actual ducks.
   *
   * @param observer
   */
  public void addObserver(QuackObserver observer) {
    duck.addObserver(observer);
  }

  /**
   * Not required as the ducks themselves propogate the event to the observers
   */
  public void notifyObservers() {}
}
