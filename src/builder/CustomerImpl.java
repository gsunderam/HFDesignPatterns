package builder;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 16, 2011
 *
 * In real world this is a complex object composed of possibly several other objects.
 */
public class CustomerImpl implements Customer {
  private String name = "GS";

  public CustomerImpl(String name) {
    this.name = name;
    stdout("Customer " + name + " created");  
  }

  public void action() {
    stdout("CustomerImpl called " + name);
    stdout("Action completed...Success");
  }

  public String toString() {
    return name;
  }
}
