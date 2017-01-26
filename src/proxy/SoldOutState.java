package proxy;

import state.GumballStateMachine;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 28, 2011
 *
 * I like the transient key word
 */
public class SoldOutState implements State {
  private transient GumballMachineImpl gumballStateMachine;

  public SoldOutState(GumballMachineImpl gumballStateMachine) {
    this.gumballStateMachine = gumballStateMachine;
  }

  public void insertQuarter() {
    stdout("Machine ran out of stock. Please notify the owner");
  }

  public void ejectQuarter() {
    stdout("No quarter to eject");
  }

  public void turnCrank() {
    stdout("Machine ran out of gumballs! Ask for refill before turning crank");
  }

  public void dispense() {
    stdout("Illegal operation at this point");
  }

  public String toString() {
    return "All gumballs Sold out";
  }
}
