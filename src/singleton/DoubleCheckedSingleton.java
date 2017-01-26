package singleton;

/**
 * User: gsunderam
 * Date: May 28, 2011
 * Improved version of Thread safe EagerSingleton using a double check lock pattern
 */
public class DoubleCheckedSingleton {
  //Volatile ensure VISIBILITY when two threads access this instance concurrently
  private static volatile DoubleCheckedSingleton instance;

  private DoubleCheckedSingleton() {}

  /**
   * This is the double checked locking pattern meant to be used ONLY with java version > 5
   * use class level locking as learnt in Java 310 055 SCJP Test
   * @return
   */
  public static DoubleCheckedSingleton getInstance() {
    if (instance == null) {
      synchronized(DoubleCheckedSingleton.class) {
        if (instance == null) {
          instance = new DoubleCheckedSingleton();
        }
      }
    }
    return instance;
  }
}
