package state;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 28, 2011
 */
public class SoldState implements State {
  private GumballStateMachine gumballStateMachine;

  public SoldState(GumballStateMachine gumballStateMachine) {
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
    
    if (gumballStateMachine.getCount() > 0) {
      gumballStateMachine.setState(gumballStateMachine.getNo25State());
    } else if (gumballStateMachine.getCount() == 0) {
      stdout("Machine ran out of gumballs. Please notify the shop associate");
      gumballStateMachine.setState(gumballStateMachine.getSoldOutState());
    }
  }
}
