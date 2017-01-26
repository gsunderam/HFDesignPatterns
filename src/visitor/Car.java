package visitor;

/**
 * User: gsunderam
 * Date: Oct 4, 2012
 */
public class Car extends Automobile {

  public Car() {
    setType("Car");
  }

  public String getWheelSpecs() {
     return "244560:DW";
  }

  @Override
  public String getState() {
    return "4 Stroke Gas";
  }

	@Override
	public void accept(Visitor visitor) {
	 visitor.visit(this);
	}
}
