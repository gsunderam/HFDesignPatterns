package visitor;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Oct 5, 2012
 *
 * Revised this on Jan 16, 2015
 *
 * Instead of calling the Engine specs information from the Object, Client will need to only deal
 * with Visitor object. 
 */
public class VisitorClient {

  public static void main(String[] args) {
    Visitor visitor = new Visitor();

    Visitable car = new Car();
		car.accept(visitor);
    stdout("Car spec " + visitor.getSpec());
		//Now client wants another info, say mileage info
		stdout("Mileage car: " + visitor.getMileage());
    stdout("");

    Visitable truck = new Truck();
		truck.accept(visitor);
    stdout("Truck spec " + visitor.getSpec());
		stdout("Mileage truck: " + visitor.getMileage());
  }
}
