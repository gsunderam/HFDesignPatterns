package singleton;

/**
 * User: gsunderam
 * Date: May 28, 2011
 */
public class EagerSingleton {
  private static final EagerSingleton instance;

  //Slight deviation from classic singleton. EAGER fetch the instance at class loading time
  //and so no need to synchronize the getInstance(), which could be costly
  static {
    instance = new EagerSingleton();
  }

  private EagerSingleton() {}

  public static EagerSingleton getInstance() {
    return instance;
  }

}
