package builder;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 16, 2011
 *
 * NSB
 */
public class BuilderImpl implements Builder {
  Customer customer;

  public BuilderImpl() {
    stdout("Created builder object for object assembly");
  }

  public Customer getCustomer() {
    return customer;
  }

  /**
   * In reality this could be a complex object composed of other objects that may need to be assembled.
   * for instance this could represent a complicated swing ui component amalgam. That would be assembled here
   * using a common interface to all  the sub components. This is rhe crux of the "builder" pattern
   */
  public void buildCustomer() {
     customer = new CustomerImpl("Test Builder Pattern");
  }
}
