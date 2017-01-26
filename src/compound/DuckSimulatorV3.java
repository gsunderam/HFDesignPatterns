package compound;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 25, 2011
 *
 * This is our simulator with a Flock of ducks as a Composite. Create ducks using the factory as earlier
 * V2 and then use the Flock class to add ducks to the Flock
 */
public class DuckSimulatorV3 {
  public static void main(String[] args) {
    DuckSimulatorV3 simulator = new DuckSimulatorV3();
    AbstractDuckFactory factory = new CountingDuckFactory();
    simulator.simulate(factory);
  }

  /**
   * Refactored to use the new Flock class
   * 
   * @param countingFactory
   */
  private void simulate(AbstractDuckFactory countingFactory) {
    //same as V2
    Quackable redhead = countingFactory.createRedheadDuck();
    Quackable duckcall = countingFactory.createDuckCall();
    Quackable rubber = countingFactory.createRubberDuck();
    Quackable goose = countingFactory.createGooseAdaptor();

    stdout("Duck simulator with a Flock: VERSION 3");
    stdout("---------------------------------------");
    //create a flock and add the above ducks. These are our first four LEAF Nodes with a parent "flockOfDucks"
    Flock flockOfDucks = new Flock();
    flockOfDucks.add(redhead);
    flockOfDucks.add(duckcall);
    flockOfDucks.add(rubber);
    flockOfDucks.add(goose);

    //create  a flock of mallards
    Flock flockOfMallards = new Flock();
    flockOfMallards.add(countingFactory.createMallardDuck());
    flockOfMallards.add(countingFactory.createMallardDuck());
    flockOfMallards.add(countingFactory.createMallardDuck());
    flockOfMallards.add(countingFactory.createMallardDuck());

    //Add it to the TOP level node. So we have 5 children = 4 Leaf and 1 Mallard Composite
    flockOfDucks.add(flockOfMallards); //Flock IS A Quackable, so this is OK

    simulate(flockOfDucks); //simulate BOTH the top level flock AND just the mallards
    stdout("----------------Now doing just the Mallard ducks-------------------");
    simulate(flockOfMallards);
    stdout("");
    stdout("The entire flock, including Mallards individually the second time, quacked " + QuackCounter.getQuackCount() + " times");

    //Here we are registering the observer of the duck quacks. This is tricky because of the QuackCounter Decorator.
    //Actually QuackCounter delegates the registration and notification process to the DUCKS. So make that as Observable
    //and within it's add and notify methods, have the actual DUCK add the observer and notify the observers
    Quackable duck = countingFactory.createMallardDuck();
    duck.addObserver(new Quackalogist());
    simulate(duck);
  }

  private void simulate(Quackable duck) {
    duck.quack();
  }
}
