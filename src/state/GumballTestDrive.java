package state;

/**
 * User: gsunderam
 * Date: Jun 28, 2011
 *
 * Test drive for the state pattern
 */
public class GumballTestDrive {
  public static void main(String[] args) {
    GumballStateMachine machine = new GumballStateMachine(5);

    machine.insertQuarter();
    machine.ejectQuarter();
    machine.insertQuarter();
    machine.insertQuarter();
    machine.turnCrank();
  }
}
