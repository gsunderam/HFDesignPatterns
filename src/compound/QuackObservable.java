package compound;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * User: gsunderam
 * Date: Jul 25, 2011
 *
 * This is the Observable interface for ducks to be OBSERVABLE
 */
public interface QuackObservable {
  public String getName();
  public void addObserver(QuackObserver observer);
  public void notifyObservers();
}
