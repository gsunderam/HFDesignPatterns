package bridge;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 23, 2011
 *
 * Client for Bridge pattern. Only abstractions are referenced here. NOT the IMPLEMENTORS and that's the benefit
 * of Bridge pattern. This is an excellent pattern! I like this.
 */
public class BridgePattern {
  public static void main(String[] args) {
    if (args.length != 1) {
      stdout("USAGE: java BridgePattern <make>");
    }

     CarAbstraction car = null;

    stdout("----------------------Bridge Pattern demo----------------");
    //create abstractions of functionality. No method is invoked using a class reference
     if (args.length == 1 && args[0].equalsIgnoreCase("Sports")) car = new SportsCar();
     else car = new EconomyCar();
    //invoke actions on the above cars
    stdout("Calling action on the car...");
    car.action(); //This in turn delegates to the sports car implementor
    stdout("----------------------------------------------");
    stdout("---------That completes Design Patterns in SCEA---------");
  }
}
