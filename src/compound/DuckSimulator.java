package compound;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 */
public class DuckSimulator {
  public static void main(String[] args) {
    DuckSimulator simulator = new DuckSimulator();
    simulator.simulate();
  }

  /**
   * We decorate the instances of ducks with a decorator viz. Counter. So the decorator is able
   * to resolve to our actual component - duck - and this is facilitated by having a common
   * superclass / interface
   */
  private void simulate() {
    Quackable mallard = new QuackCounter(new MallardDuck()); //Assign the decorator to be Quackable
    Quackable redhead = new QuackCounter(new RedheadDuck());
    Quackable duckcall = new QuackCounter(new DuckCall());
    Quackable rubber = new QuackCounter(new RubberDuck());
    Quackable goose = new GooseAdaptor(new Goose()); //Assign the adaptor to be Quackable

    stdout("Duck Simulator with Goose adaptor and a quack counter: Version 1");
    stdout("----------------------------------------------------------------");
    simulate(mallard);
    simulate(redhead);
    simulate(duckcall);
    simulate(rubber);
    simulate(goose); //Here we're able to do this because our adaptor also IS A Quackable! This goes to honk
    stdout("");
    stdout("Number of quacks by all the ducks: " + QuackCounter.getQuackCount());
  }

  private void simulate(Quackable duck) {
    duck.quack();
  }
}
