package compound;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 *
 * By this we ensure ALL the ducks are duly DECORATED and reduce the chances of inadvertently
 * forgetting to decorate a duck with QuackCounter
 */
public class CountingDuckFactory implements AbstractDuckFactory {
  
  public Quackable createMallardDuck() {
    return new QuackCounter(new MallardDuck());
  }

  public Quackable createRedheadDuck() {
    return new QuackCounter(new RedheadDuck());
  }

  public Quackable createDuckCall() {
    return new QuackCounter(new DuckCall());
  }

  public Quackable createRubberDuck() {
    return new QuackCounter(new RubberDuck());
  }

  /**
   * This does not have to be decorated with with a counter as we are just counting ducks
   *
   * @return
   */
  public Quackable createGooseAdaptor() {
    return new GooseAdaptor(new Goose());
  }
}
