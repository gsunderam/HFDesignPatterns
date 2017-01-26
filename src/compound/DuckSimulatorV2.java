package compound;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 */
public class DuckSimulatorV2 {
  public static void main(String[] args) {
    DuckSimulatorV2 simulator = new DuckSimulatorV2();
    AbstractDuckFactory countingFactory = new CountingDuckFactory();
    simulator.simulate(countingFactory, true);

    AbstractDuckFactory nonCountingFactory = new NonCountingDuckFactory();
    simulator.simulate(nonCountingFactory, false);
  }

  /**
   * This version uses the abstract factory to create the ducks rather then inline instantiation
   * 
   * @param duckFactory
   */
  private void simulate(AbstractDuckFactory duckFactory, boolean countFlag) {
    Quackable mallard = duckFactory.createMallardDuck(); //create two mallard ducks this time
    Quackable mallard2 = duckFactory.createMallardDuck();
    Quackable redhead = duckFactory.createRedheadDuck();
    Quackable duckcall = duckFactory.createDuckCall();
    Quackable rubber = duckFactory.createRubberDuck();
    Quackable goose = duckFactory.createGooseAdaptor();

    stdout("Duck Simulator using abstract factory of ducks: Version 2");
    stdout("-----------------------------------------------------------");
    simulate(mallard);
    simulate(mallard2);
    simulate(redhead);
    simulate(duckcall);
    simulate(rubber);
    simulate(goose);

    if (countFlag) {
      stdout("");
      stdout("Abstract factory ensures ALL duck quacks are counted. Let's see if the count is FIVE");
      int count = QuackCounter.getQuackCount();
      stdout("No of duck quacks by ducks with factory: " + count);
      assert count == 5: "Abstract factory seems to be incorrectly counting. Check the logic";
    }
  }

  private void simulate(Quackable duck) {
    duck.quack();
  }
}
