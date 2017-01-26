package utilconcurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: gsunderam
 * Date: Mar 9, 2013
 */
public class StackUsingLock {
  private ReentrantLock lock = new ReentrantLock();
  private Condition isNotEmpty = lock.newCondition();
  private Condition isNotFull = lock.newCondition();
  private int counter;
  private Integer [] vec = new Integer[5];

  public Integer pop() {
    Integer obj = null;
    lock.lock();
    try {
      while(counter == 0) {
        isNotEmpty.await();
      }
      obj = vec[--counter]; //to take care of zero-based index we say --counter and NOT counter
      vec[counter] = null;
      isNotFull.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    return obj;
  }

  public Integer push(int n) {
    lock.lock();
    try {
      while(counter == vec.length) {
        isNotFull.await();
      }
      vec[counter] = new Integer(n);
      counter++;
      isNotEmpty.signal();
      return new Integer(n);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    return null;
  }

  public int size() {
    return counter;
  }

  public int capacity() {
    return vec.length;
  }

  public int getHoldCount() {
    return lock.getHoldCount();
  }
}