package utilconcurrent.cache;

import java.util.concurrent.ExecutionException;

/**
 * User: gsunderam
 * Date: Sep 6, 2013
 */
public interface Computable<K, V> {
  /**
   * Does an expensive computation
   *
   * @param key  for which value is desired
   * @param threadName This is just to log the thread that does the compute at time=t. For debugging purposes
   * @return the computed value
   * @throws InterruptedException
   * @throws ExecutionException
   */
  public V compute(K key, String threadName) throws InterruptedException, ExecutionException;
}
