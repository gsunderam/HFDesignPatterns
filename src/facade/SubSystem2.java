package facade;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 17, 2011
 *
 * These subsystems get their calls via the Facade. No client can call them directly
 */
public class SubSystem2 {
  private String name;
  
  SubSystem2(String name) {
    this.name = name;
  }

  boolean checkComponents() {
     stdout("Checking the connectivity to the supplier systems from " + name);
     return true;
  }

  void sendJmsMessage() {
    stdout("Constructing msg for processinf your order...sent done");
  }
}
