package visitor;

/**
 * User: gsunderam
 * Date: Oct 4, 2012
 */
public abstract class Automobile implements Visitable {
  protected String type = "Automobile";

  public String getWheelSpecs() {
    return "Generic Wheel spec";
  }

  public abstract String getState();

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }
}
