package command;

/**
 * User: gsunderam
 * Date: May 29, 2011
 */
public enum FanStates {

  HIGH(3), MEDIUM(2), LOW(1), OFF(0);
  
  private int state;

  FanStates(int state) {
    this.state = state;
  }

  public int getState() {
    return state;
  }
}
