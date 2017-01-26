package bridge;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 23, 2011
 */
public class EconomyCarImplementor implements CarImplementor {
  private String make;
  private String model;
  private String type;

  public EconomyCarImplementor(String make, String model, String type) {
    this.make = make;
    this.model = model;
    this.type = type;
  }

  public void actionImplemented() {
     stdout("Economy car make: " + make + " model: " + model + " type: " + type);
     stdout("Economy car mileage is: " + getEconMileage());
  }

  private String getEconMileage() {
    return "35";
  }
}
