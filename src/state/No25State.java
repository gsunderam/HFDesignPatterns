package state;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 28, 2011
 */
public class No25State implements State {
  private GumballStateMachine gumballStateMachine;

  public No25State(GumballStateMachine gumballStateMachine) {
    this.gumballStateMachine = gumballStateMachine;
  }

  public void insertQuarter() {
    stdout("Quarter inserted. Are you ready for the ball? Turn Crank!");
    gumballStateMachine.setState(gumballStateMachine.getHas25State());
  }

  public void ejectQuarter() {
    stdout("No 25 to eject");
  }

  public void turnCrank() {
    stdout("Please insert a quarter before turning crank");
  }

  public void dispense() {
    stdout("Illegal operation. Insert a quarter");
  }
}
