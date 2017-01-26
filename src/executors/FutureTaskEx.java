package executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Mar 24, 2013
 *
 * Reference implementation to show how to use future task with executors
 */
public class FutureTaskEx {
  public FutureTaskEx() {
    //preferable over other thread pool impls as Caching removes overhead incurred in thread creation 
    ExecutorService service = Executors.newCachedThreadPool();
    List<Future<Integer>> futures = new ArrayList<Future<Integer>>();

    for(int i = 0; i < 15; i++) {
      FibCallable<Integer> task = new FibCallable<Integer>(i);
      Future<Integer> future = service.submit(task); //submit your tasks
      futures.add(future);
    }

    //Do something else...when done...get the results

    try {
      for(Future<Integer> result : futures) {
        Integer integer = result.get(); //get blocks until the results are available
        stdout("Future result: " + integer.intValue());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    service.shutdown(); //Important, otherwise threads may live till  
  }

  /**
   * Fibonocci series with recursion
   *
   * @param n Fibonocci implementation without recursion
   * @return
   */
  private Integer fibonocci(int n) {
    if (n < 0) throw new IllegalArgumentException("n must be >= 1");
    return n < 2 ? 1 : fibonocci(n - 1) + fibonocci(n - 2);
  }

  /**
   * Fibonocci implementation without recursion
   *
   * @param n Number of numbers in the series
   * @return
   */
  private int gsFib(int n) {
    int sum = 1, count = 0;
    int s1 = 0, s2 = 1;
    while(count < n) {
      sum = s1 + s2;
      s1 = s2;
      s2 = sum;
      count++;
    }
    return sum;
  }

  public static void main(String[] args) {
    new FutureTaskEx();
  }

  private class FibCallable<T> implements Callable<T> {
    int n;

    private FibCallable(int n) { 
      this.n = n;
    }

    @Override
    public T call() throws Exception {
      return (T) fibonocci(n);
    }
  }
}
