package state;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 28, 2011
 */
public class GumballStateMachine {
  private int count;
  State no25State;
  State has25State;
  State soldState;
  State soldOutState;

  State state = soldOutState;

  public GumballStateMachine(int numberOfgumballs) {
    this.count = numberOfgumballs;
    no25State = new No25State(this);
    has25State = new Has25State(this);
    soldState = new SoldState(this);
    soldOutState = new SoldOutState(this);
    state = no25State;
  }

  public void insertQuarter() {
    state.insertQuarter();
  }

  public void ejectQuarter() {
    state.ejectQuarter();
  }

  public void turnCrank() {
    state.turnCrank();
    state.dispense();
  }

  public void releaseBall() {
    stdout("Releasing one gumball...");
    for (int i = 0;i < 10000;i++);
    stdout("SUCCESS. Released!");
    if (count != 0) {
      count -= 1;
    }
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getNo25State() {
    return no25State;
  }

  public void setNo25State(State no25State) {
    this.no25State = no25State;
  }

  public State getHas25State() {
    return has25State;
  }

  public void setHas25State(State has25State) {
    this.has25State = has25State;
  }

  public State getSoldState() {
    return soldState;
  }

  public void setSoldState(State soldState) {
    this.soldState = soldState;
  }

  public State getSoldOutState() {
    return soldOutState;
  }

  public void setSoldOutState(State soldOutState) {
    this.soldOutState = soldOutState;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
