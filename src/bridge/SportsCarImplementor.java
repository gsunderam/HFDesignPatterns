package bridge;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 23, 2011
 *
 * Actual Car specific implementation
 */
public class SportsCarImplementor implements CarImplementor {
  private String model, make, type;

  public SportsCarImplementor(String model, String make, String type) {
    this.model = model;
    this.make = make;
    this.type = type;
  }

  /**
   * This is the actual implementation we are hiding from the client using
   * bridge pattern
   */
  public void actionImplemented() {
     stdout("The mileage for " + type + " car will be calculated using a different algorithm");
  }
}
