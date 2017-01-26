package bridge;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 23, 2011
 *
 * This is COMPOSED of the implentor pointing to Sports car impl. composition makes us do
 * magic. Similar to Strategy pattern in that Composition is used, but the intent in different
 */
public class SportsCar implements CarAbstraction {
  CarImplementor implementor = new SportsCarImplementor("Honda", "Civic", "sports");

  public void action() {
     stdout("Doing Sports car specific initializations...");
     implementor.actionImplemented();
  }
}
