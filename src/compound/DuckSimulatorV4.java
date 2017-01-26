package compound;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 26, 2011
 *
 * This has ALL the V3 stuff PLUS the Obnserver specific code to register a duck and flocks to be
 * observables.
 */
public class DuckSimulatorV4 {
  public static void main(String[] args) {
    DuckSimulatorV4 simulator = new DuckSimulatorV4();
    AbstractDuckFactory factory = new CountingDuckFactory();
    simulator.simulate(factory);
  }

  private void simulate(AbstractDuckFactory countingFactory) {
    //same as V2
    Quackable redhead = countingFactory.createRedheadDuck();
    Quackable duckcall = countingFactory.createDuckCall();
    Quackable rubber = countingFactory.createRubberDuck();
    Quackable goose = countingFactory.createGooseAdaptor();

    stdout("Duck simulator with a Flock: VERSION 4");
    stdout("---------------------------------------");
    //create a flock and add the above ducks. These are our first four LEAF Nodes with a parent "flockOfDucks"
    Flock flockOfDucks = new Flock();
    flockOfDucks.add(redhead);
    flockOfDucks.add(duckcall);
    flockOfDucks.add(rubber);

    stdout("--------------------Mallard duck registering an observer--------");

     //Here we are registering the observer of the duck quacks. This is tricky because of the QuackCounter Decorator.
    //Actually QuackCounter delegates the registration and notification process to the DUCKS. So make that as Observable
    //and within it's add and notify methods, have the actual DUCK add the observer and notify the observers
    Quackable duck = countingFactory.createMallardDuck();
    Quackalogist quackalogist = new Quackalogist(); //Observer
    duck.addObserver(quackalogist);
    simulate(duck);

    stdout("-----------------A Flock registering an observer---------------");
    //Now let's have the flock propogate the event
    flockOfDucks.addObserver(quackalogist);
    simulate(flockOfDucks);
    stdout("");
    stdout("Total quacks : " + QuackCounter.getQuackCount());
  }

  private void simulate(Quackable duck) {
    duck.quack();
  }
}
