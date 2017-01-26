package myducksimulator;

import java.util.Observable;
import java.util.Observer;

/**
 * User: gsunderam
 * Date: Jul 25, 2011
 *
 * This is a helper Observable class for ducks to be OBSERVABLES. I'm using java's in built
 * observer pattern and making ducks quackables all in one inheritance tree
 *
 */
public abstract class QuackObservable extends Observable implements Quackable {

  public abstract String getName();
  
  public void addObserver(Observer observer) {
   super.addObserver(observer);
  }

}
