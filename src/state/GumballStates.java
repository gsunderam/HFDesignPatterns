package state;

/**
 * User: gsunderam
 * Date: Jun 25, 2011
 *
 * This enum stores the different states of the gumball machine
 */
public enum GumballStates {
  NO_25("start", "Need customer"), HAS_25("request", "In process"), SOLD_OUT("sold out", "Need refll"),
    AVAILABLE("available", "Atleast one more is available");

  String state;
  String description;

  GumballStates(String state, String description) {
   this.state = state;
   this.description = description;
  }

  public String getState() {
    return state;
  }

  public String getDescription() {
    return description;
  }
}
