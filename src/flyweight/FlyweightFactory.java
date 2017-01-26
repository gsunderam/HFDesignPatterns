package flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * User: gsunderam
 * Date: Jul 23, 2011
 *
 * This is crux of theis pattern. Cache the objects in a hashmap and retrieve it and keep changing states
 */
public class FlyweightFactory {
  private static Map<String, FlyWeight> map = new HashMap<String, FlyWeight>();

  public static FlyWeight getFlyweight(String key) {
    FlyWeight flyWeight = null;

    //return the fly weight if exists.
    if (map.containsKey(key)) {
      flyWeight = map.get(key);
    } else {       //put in the map and return new fly weight
      flyWeight = new ConcreteFlyweight(key.indexOf("true") != -1 ? new State(true) : new State(false));
      map.put(key, flyWeight);
    }

    return flyWeight;
  }
}
