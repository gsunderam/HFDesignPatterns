package singleton;

/**
 * User: gsunderam
 * Date: May 28, 2011
 */
public class ClassicSingleton {

  private static ClassicSingleton instance;
  private String name;

  private ClassicSingleton() {
    this("Classic");
  }

  private ClassicSingleton(String name) {
    this.name = name;
  }

  /**
   * First improvemnet over the classical singleton: synchronize for thread safe access
   * @return
   */
  public static synchronized ClassicSingleton getInstance() {
    if (instance == null) {
      instance = new ClassicSingleton();
    }
    return instance;
  }

  public String getName() {
    return this.name;
  }
}
