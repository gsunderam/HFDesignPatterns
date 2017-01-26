package state;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 25, 2011
 *
 * Test harness to exercise the validity of the state machine 101
 */
public class GumballTest {
  private static int i;

  public static void main(String[] args) {
    GumballMachine machine = new GumballMachine(5);
    int i = 1;

    label("Happy path");
    machine.insertQuarter();
    machine.turnCrank();   //1
    printState(machine.toString());

    label("Insert twice");
    machine.insertQuarter();
    machine.insertQuarter();
    machine.turnCrank();//   #2
    printState(machine.toString());

    label("eject quarter");
    machine.insertQuarter();
    machine.ejectQuarter();
    machine.turnCrank(); //turn illegally
    printState(machine.toString());

    label("Get two gumballs");
    machine.insertQuarter();
    machine.turnCrank();//3
    machine.insertQuarter();
    machine.turnCrank();//4
    printState(machine.toString());

    label("No gumball state");
    machine.ejectQuarter();
    machine.insertQuarter();
    machine.turnCrank(); //5
    printState(machine.toString());

    label("sold out case");
    machine.insertQuarter();
    machine.turnCrank();
    printState(machine.toString());

    label("Refilling case");
    machine.refill();
    machine.turnCrank();
    machine.insertQuarter();
    machine.turnCrank();
  }

  private static void printState(String state) {
    stdout("The state of the Gumball machine is " + state);
  }

  private static void label(String caseName) {
     stdout("-------------------------------Case " + ++i + " " + caseName + "------------------------------");
  }

}
