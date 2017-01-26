package proxy;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 28, 2011
 *
 * Machine state is marked "transient" so that this won't be serialized by the RMI System. So here is the
 * application of Java 5 exam concepts!
 */
public class No25State implements State {
  private transient GumballMachineImpl gumballStateMachine;

  public No25State(GumballMachineImpl gumballStateMachine) {
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

  public String toString() {
    return "Waiting for Quarter";
  }
}
