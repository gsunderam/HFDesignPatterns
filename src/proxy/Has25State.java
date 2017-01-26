package proxy;

import state.GumballStateMachine;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 28, 2011
 */
public class Has25State implements State {
  private transient GumballMachineImpl machine;

  public Has25State(GumballMachineImpl machine) {
    this.machine = machine;
  }

  public void insertQuarter() {
    stdout("You already inserted once. Please wait...");
  }

  public void ejectQuarter() {
    stdout("Ejecting your quarter...Done");
    machine.setState(machine.getNo25State());
  }

  public void turnCrank() {
    stdout("Coin on the way...hang in there");
    machine.setState(machine.getSoldState());
  }

  public void dispense() {
    stdout("Illegal operation. Please shove in a 25");
  }

  public String toString() {
    return "Quarter arrived. Waiting for customer to turn the crank or eject it back!";
  }
}
