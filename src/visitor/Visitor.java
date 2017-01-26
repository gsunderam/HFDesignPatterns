package visitor;

/**
 * User: gsunderam
 * Date: Oct 5, 2012
 * To add behavior in the future to Car or Truck, Just modify the visitor class(es), without touching Car/Truck.
 */
public class Visitor implements VisitorIF {
	private String spec;
	private String mileage;

	/**
	 * Keep adding functions here instead of in the car or truck.
	 * @param auto
	 */
	public void visit(Automobile auto) {
		 calculateSpecs(auto);
		 calculateMileage(auto);
  }

	private void calculateMileage(Automobile auto) {
		 if (auto instanceof Car) mileage = "30";
		 if (auto instanceof Truck) mileage = "50";
	}

	/**
	 * This is the info that the client is looking for
	 * @return
	 */
	public String getSpec() {
		return spec;
	}

	/**
	 * Modify this instead of actual classes, which is were Visitor pattern comes in
	 * This method access the individual objects to fetch the required state information so that
	 * the client's data can be derived
	 * 
	 * @param c
	 * @return
	 */
	private String calculateSpecs(Automobile c) {
		if (c instanceof Car) {
			//do car specific stuff, return result
    	spec = c.getState() + ":" + c.getType(); //specific to car
		}

		//Do truck specific stuff, return result
		if (c instanceof Truck)
			spec = c.getState() + ":" + ((Truck) c).getCapacity();//specific to truck

		return spec;
  }

	public String getMileage() {
		return mileage;
	}
}
