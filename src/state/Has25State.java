package state;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 28, 2011
 */
public class Has25State implements State {
  private GumballStateMachine gumballStateMachine;

  public Has25State(GumballStateMachine gumballStateMachine) {
    this.gumballStateMachine = gumballStateMachine;
  }

  public void insertQuarter() {
    stdout("You already inserted once. Please wait...");
  }

  public void ejectQuarter() {
    stdout("Ejecting your quarter...Done");
    gumballStateMachine.setState(gumballStateMachine.getNo25State());
  }

  public void turnCrank() {
    stdout("Coin on the way...hang in there");
    gumballStateMachine.setState(gumballStateMachine.getSoldState());
  }

  public void dispense() {
    stdout("Illegal operation. Please shove in a 25");
  }
}
