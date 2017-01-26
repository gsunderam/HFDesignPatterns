package utilconcurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 21, 2013
 *
 * Use Semaphores to create blocking bounded collections
 */
public class BoundedHashset<T> {
  //Favour composition over inheritance
  private final Set<T> set;
  private final Semaphore sem;

  public BoundedHashset(int bound) {
    this.set = Collections.synchronizedSet(new HashSet<T>());
    this.sem = new Semaphore(bound);
  }

  public boolean add(T t) throws InterruptedException {
    stdout("Trying to add " + t + " Permits avaialble: " + sem.availablePermits());
    sem.acquire();
    boolean isAdded = false;
    try {
      isAdded = set.add(t);
      stdout("Added " + t);
      return isAdded;
    } finally {
      if (!isAdded) sem.release();
    }
  }

  public boolean remove(T t) {
    boolean isRemoved = set.remove(t);
    if (isRemoved) sem.release();
    stdout("Removed " + t);
    return isRemoved;
  }
}
