package compound;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 *
 * This is an adaptor. Adapting Goose to be a duck. So duck = Adapted; Goose = adaptee
 * duck = target interface or client interface;
 */
public class GooseAdaptor implements Quackable {
  Goose goose;

  public GooseAdaptor(Goose goose) {
    this.goose = goose;
  }

  public void quack() {
    stdout("This is Goose. I'm now an adaptee and pseudo quacking");
    goose.honk();
  }

  public String getName() {
    return goose.getName();
  }

  public void addObserver(QuackObserver observer) {

  }

  public void notifyObservers() {

  }
}
