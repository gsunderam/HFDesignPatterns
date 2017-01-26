package builder;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 16, 2011
 */
public class BuilderPattern {
  public static void main(String[] args) {
    //create the director
    stdout("------------Builder pattern demonstration---------------");
    Builder builder = new BuilderImpl();  //create builder
    Director director = new Director(builder);
    //construct the "supposedly" complex customer object
    director.construct();
    Customer customer = builder.getCustomer();
    stdout("Got the complex Customer object via the builder: " + customer);
    stdout("Calling the business method: \"action\"...");
    customer.action();
  }
}
