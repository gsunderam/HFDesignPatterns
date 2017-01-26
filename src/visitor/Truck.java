package visitor;

/**
 * User: gsunderam
 * Date: Oct 5, 2012
 */
public class Truck extends Automobile {

  public Truck() {
    setType("Truck");
  }
  
   public String getWheelSpecs() {
     return "34244560:DW:Truck";
  }

  @Override
  public String getState() {
    return "8 Stroke Diesel";
  }

  public String getCapacity() {
    return "10 pounds";
  }

	@Override
	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}
}
