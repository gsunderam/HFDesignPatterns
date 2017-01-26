package facade;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 17, 2011
 *
 * Main class to show how FACADE works.
 */
public class FacadeClient {
  public static void main(String[] args) {
    Facade facade = new Facade();
    facade.processOrder();
    stdout("Simple Illustration of Facade pattern complete");
  }
}
