package facade;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 17, 2011
 *
 * These subsystems ALL have package level access. So no clients outside of the package can call them directly
 */
public class SubSystem1 {
  SubSystem1(String name) {
    stdout("Beginning processing subsystem: " + name);
  }

  void processQuantity() {
    stdout("Available quantity OK");
  }

  void checkPrices() {
    stdout("Checking prices from session bean...Done");
  }
}
