package myducksimulator;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 *
 */
public class DuckFactory implements AbstractDuckFactory {
  
  public Quackable createMallardDuck() {
    return new MallardDuck();
  }

  public Quackable createDuckCall() {
    return new DuckCall();
  }

  public Quackable createRubberDuck() {
    return new RubberDuck();
  }


}
