package compound;

/**
 * User: gsunderam
 * Date: Oct 25, 2012
 *
 * This is a different implementation of the methods in the Abstract class
 */
public class NonCountingDuckFactory implements AbstractDuckFactory {

  @Override
  public Quackable createMallardDuck() {
    return new MallardDuck();
  }

  @Override
  public Quackable createRedheadDuck() {
    return new RedheadDuck();
  }

  @Override
  public Quackable createDuckCall() {
    return new DuckCall();
  }

  @Override
  public Quackable createRubberDuck() {
    return new RubberDuck();
  }

  @Override
  public Quackable createGooseAdaptor() {
    return new GooseAdaptor(new Goose());
  }
}
