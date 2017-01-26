package state;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 25, 2011
 *
 * State machine 101
 * This is a classic example of state change application. This is a state machine with transitions insert25, eject25,
 * turnCrank and dispense. Each of these methods CHANGE the state of THIS MACHINE. The methods are transitions and the states
 * are start, request, available and sold out. Each action causes a specific change to the state of this state machine
 *
 * BUT, this is a procedural way of programming NOT object oriented! Next version of this is the STATE Pattern!
 */
public class GumballMachine {
  //Initialize the state
  GumballStates state = GumballStates.SOLD_OUT;
  int count;

  public GumballMachine(int count) {
    this.count = count;
    if (count > 0) {
      state = GumballStates.NO_25;
    }
  }

  /**
   * These are the actions that can cause a change in the STATE that SCEA talked about
   * makes state has_25
   */
  public void insertQuarter() {
    if (state == GumballStates.NO_25) {
      stdout("You inserted a quarter");
      state = GumballStates.HAS_25;
    } else if (state == GumballStates.HAS_25) {
      stdout("You already inserted a quarter. please wait");
    } else if (state == GumballStates.SOLD_OUT) {
      stdout("Gumballs got sold out");
    } else if (state == GumballStates.AVAILABLE) {
      stdout("The machine is still getting your bounty");
    }
  }

  /**
   * makes state = NO_QUARTER
   */
  public void ejectQuarter() {
    if (state == GumballStates.NO_25) {
      stdout("You have NOT inserted a quarter");
    } else if (state == GumballStates.HAS_25) {
      state = GumballStates.NO_25;
      stdout("Quarter returned");
    } else if (state == GumballStates.SOLD_OUT) {
      stdout("Can't eject sold out already");
    } else if (state == GumballStates.AVAILABLE) {
      stdout("You already turned the crank. Can't eject now");
    }
  }

  /**
   * makes it AVAILABLE
   */
  public void turnCrank() {
    if (state == GumballStates.NO_25) {
      stdout("Please insert a quarter first");
    } else if (state == GumballStates.HAS_25) {
      state = GumballStates.AVAILABLE;
      stdout("Success! Please collect your gumball in a minute");
      dispense();
    } else if (state == GumballStates.SOLD_OUT) {
      stdout("you turned, but Gumballs got sold out");
    } else if (state == GumballStates.AVAILABLE) {
      stdout("You turned, but the machine is still getting your bounty. hold on!");
    }
  }

  /**
   * This should be private as we don't want any unruliness here -:)
   * state = NO_25 or state = SOLD_OUT
   */
  private void dispense() {
    if (state == GumballStates.AVAILABLE) {
      stdout("dispensed: Get your gumball from the slot please. Thank you for coming");
      count -= 1;
      if (count == 0) {
         state = GumballStates.SOLD_OUT;
         stdout("-------------------------------------------------------------------------------------------------");
         stdout("WARNING: No dispense untill refill. All the gumballs are sold out. Please notify the shop owner");
      } else {
        state = GumballStates.NO_25;
      }
    } else if (state == GumballStates.NO_25) { //These three would never happen. 
      stdout("You have to pay first. Hit insertQuarter()");
    } else if (state == GumballStates.SOLD_OUT) {
        stdout("No gumball dispensed");
    } else if (state == GumballStates.HAS_25) {
       stdout("No gumball dispensed");
    }
  }

  /**
   * Refill the gumball machine. set the state back t0 start 
   */
  public void refill() {
    state = GumballStates.NO_25;
    this.count = 5;
  }

  public String toString() {
     return "State: " + state.getState().toUpperCase();
  }
}
