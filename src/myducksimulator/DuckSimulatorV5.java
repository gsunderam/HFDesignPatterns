package myducksimulator;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 26, 2011
 *
 * This has ALL the V4 stuff PLUS the Obnserver specific code to register a duck and flocks to be
 * observables USING Java's built in observer pattern. Seems to be a better version than Head First's
 */
public class DuckSimulatorV5 {
  public static void main(String[] args) {
    DuckSimulatorV5 simulator = new DuckSimulatorV5();
    AbstractDuckFactory factory = new DuckFactory();
    simulator.simulate(factory);
  }

  private void simulate(AbstractDuckFactory duckFactory) {
    //same as V2
    Quackable duckcall = duckFactory.createDuckCall();
    Quackable rubberduck = duckFactory.createRubberDuck();

    stdout("BETTER Duck simulator with a Flock and Java's observer: VERSION 5");
    //create a flock and add the above ducks. These are our first four LEAF Nodes with a parent "flockOfDucks"
    Flock flockOfDucks = new Flock();
    flockOfDucks.add(duckcall);
    flockOfDucks.add(rubberduck);

    stdout("--------------------Mallard duck registering an observer----------------");
    Quackable mallardduck = duckFactory.createMallardDuck();
    Quackalogist quackalogist = new Quackalogist(); //Observer
    if (mallardduck instanceof QuackObservable)
      ((QuackObservable) mallardduck).addObserver(quackalogist); //cast required to register the observer to an Observable
    simulate(mallardduck);

    stdout("-----------------A Flock of ducks registering an observer---------------");
    //Now let's have the flock propogate the event
    flockOfDucks.addObserver(quackalogist);
    simulate(flockOfDucks);
    stdout("");
    stdout("Total Number of quacks: " + Quackalogist.getQuackCount());
  }

  private void simulate(Quackable duck) {
    duck.quack();
  }
}
