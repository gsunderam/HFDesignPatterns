package utilconcurrent;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Mar 10, 2013
 */
public class ReadWriteLockEx {
  public static void main(String[] args) {
    RWDictionary dict = new RWDictionary();
    dict.put("rama", dict.new Data("rama", 1));
    dict.put("krishna", dict.new Data("krishna", 2));
    dict.put("govinda", dict.new Data("govinda", 3));
    dict.put("narayana", dict.new Data("narayana", 4));

    String [] keys = {"rama", "krishna", "govinda", "narayana"};

    for(String key : keys) {
      stdout("Key: " + key + " Value: " + dict.get(key).id);
    }
  }
}
