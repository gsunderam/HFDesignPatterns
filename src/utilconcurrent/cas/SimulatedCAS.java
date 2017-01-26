package utilconcurrent.cas;

/**
 * User: gsunderam
 * Date: Aug 28, 2013
 *
 * This class illustrates the basic idea of how the new multi core processors leverage the CAS
 * - compareAndSwap - technique to obviate the need to "synchronize" access to shared variables.
 * Under moderate contention, this increases the performance by 50% approximately
 */
public class SimulatedCAS<V> {
  private V value;

  SimulatedCAS(V value) {
    this.value = value;
  }

  public synchronized V get() { return value; }

  public synchronized V compareAndSwap(V expectedValue, V newValue) {
      V oldValue = value;
      if (oldValue.equals(expectedValue))
        value = newValue;
    
      return oldValue;
  }

  public synchronized boolean compareAndSet(V expectedValue, V newValue) {
    return expectedValue.equals(compareAndSwap(expectedValue, newValue));
  }
}
