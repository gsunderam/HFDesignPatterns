package myducksimulator;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 *
 * This is the Abstract factory pattern, different ducks and goose being the family of related products
 * and they are returned as a reference to a Quackable
 */
public interface AbstractDuckFactory {
  Quackable createMallardDuck();
  Quackable createDuckCall();
  Quackable createRubberDuck();
}
