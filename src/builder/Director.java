package builder;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 16, 2011
 *
 * This is the orchestrator of the show. NML
 */
public class Director {
  Builder builder;

  public Director(Builder builder) {
    this.builder = builder;
    stdout("Creating director object to orchestrate the show");
  }

  public void construct() {
    builder.buildCustomer();
  }
}
