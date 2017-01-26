package proxy;

import state.GumballStateMachine;

import java.rmi.RemoteException;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 28, 2011
 */
public class SoldState implements State {
  private transient GumballMachineImpl gumballStateMachine;

  public SoldState(GumballMachineImpl gumballStateMachine) {
    this.gumballStateMachine = gumballStateMachine;
  }

  public void insertQuarter() {
    stdout("Gumball on the way. Quarters not accepted at this point");
  }

  public void ejectQuarter() {
    stdout("Not possible to eject now");
  }

  public void turnCrank() {
    stdout("Tuning twice doesn't make a difference. Hold on please");
  }

  public void dispense() {
    gumballStateMachine.releaseBall();

    try {
      if (gumballStateMachine.getCount() > 0) {
        gumballStateMachine.setState(gumballStateMachine.getNo25State());
      } else if (gumballStateMachine.getCount() == 0) {
        stdout("Machine ran out of gumballs. Please notify the shop associate");
        gumballStateMachine.setState(gumballStateMachine.getSoldOutState());
      }
    } catch (RemoteException e) {
      stdout("Remote Exception");
    }
  }

  public String toString() {
    return "Sold a ball to the customer";
  }
}
