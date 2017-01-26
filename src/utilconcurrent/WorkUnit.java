package utilconcurrent;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Apr 7, 2013
 */
public class WorkUnit<T> {
  T t;

  public WorkUnit(T t) {
    this.t = t;
  }

  public T getData() {
    return t;
  }

  public void executeWork() {
    stdout("WorkUnit received " + t);
    if (!(t instanceof String)) stdout("Wrong type. Not my Task -:)");
    
    stdout("Reversed String " + ((String) t).toUpperCase());
  }

}
