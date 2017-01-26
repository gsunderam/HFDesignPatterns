package utilconcurrent.cache;

import java.util.concurrent.*;

import static log.Logger.stderr;
import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 6, 2013
 */
public class Memoizer<K, V> implements Computable<K,V> {
  private ConcurrentMap<K, Future<V>> map = new ConcurrentHashMap<K,Future<V>>();
  private Computable<K,V> computable;

  public Memoizer(Computable<K, V> computable) {
    this.computable = computable;
  }

  @Override
  public V compute(final K key, final String threadName) throws InterruptedException, ExecutionException {
      Future<V> future = map.get(key);
      V value = null;

      if (future == null) {
        stdout("Calculating...and...caching");
        Callable<V> eval = new Callable<V>() {
          public V call() throws Exception {
            return computable.compute(key, threadName); //actual computation is done here
          }
        };

        FutureTask<V> ft = new FutureTask<V>(eval);
        future = map.putIfAbsent(key, ft);
        if (future == null) {
          stdout("No close timing calls encountered");
          future = ft;
          ft.run();
        } else {
          stdout("Already run. Truly concurrent situation encountered for key! " + key + " by " + threadName);
        }

        try {
          value = future.get(5, TimeUnit.SECONDS);  //Good practice to use time outs
        } catch (CancellationException e) {
          map.remove(key);
        } catch (ExecutionException e) {
          launderThrowable(e.getCause());
        } catch (TimeoutException e) {
          stderr("Time out exception");
          e.printStackTrace();
        }
      } else {
        value = future.get();
      }

      return value;
  }

  /**
   * This is how the excecution exception must be handled while doing Task execution
   * @param cause
   * @return
   */
  private RuntimeException launderThrowable(Throwable cause) {
    if (cause instanceof RuntimeException)
      return (RuntimeException) cause;
    else if (cause instanceof Error)
      throw (Error) cause;
    else
      throw new IllegalStateException("Not unchecked", cause);
  }
}