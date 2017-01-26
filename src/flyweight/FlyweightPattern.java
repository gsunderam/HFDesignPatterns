package flyweight;

import java.util.Map;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 23, 2011
 *
 * Useful if there is a need to create more objects just to update or create new states
 * Here State just needs to be created twice because there are just two states
 */
public class FlyweightPattern {
  public static void main(String[] args) {
    //create fly weight and use the SAME reference to invoke the action on two State objects
    FlyWeight flyWeight = FlyweightFactory.getFlyweight("keytrue"); //Initial state of fly weight true
    FlyWeight flyWeight1 = FlyweightFactory.getFlyweight("keyfalse");
    flyWeight.action(); //sets new states per the requirements
    flyWeight1.action();
  }
}
