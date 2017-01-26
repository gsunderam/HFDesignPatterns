package utilconcurrent.cas;

import utilconcurrent.synchronizers.OneShotLatch;

import java.util.concurrent.CountDownLatch;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Aug 28, 2013
 *
 * Test Driver to test the CAS concept.
 */
public class CasCounterTest {

  public static void main(String[] args) throws InterruptedException {
    Thread [] threads = new Thread[59];               
    new CasCounterTest().test(threads);
  }

  public void test(Thread[] threads) throws InterruptedException {
      final CasCounter casCounter = new CasCounter();
      int i = 0;
			final OneShotLatch start = new OneShotLatch();
		  final CountDownLatch end = new CountDownLatch(threads.length);

      for(Thread thread : threads) {
          thread = new Thread(new Runnable() {

            @Override
            public void run() {
							try {
								start.await();
								stdout(Thread.currentThread().getName() + ": " + casCounter.increment());
							} catch (InterruptedException e) {
								e.printStackTrace();
							} finally {
								end.countDown();
							}
            }
          }, "Thread" + (i++));

         thread.start();
        }

				start.signal();
				end.await();

				stdout("The value of the counter is " + casCounter.getValue());
  }

  class CasCounter {
      private SimulatedCAS<Integer> value = new SimulatedCAS<Integer>(0);

      public int getValue() {
        return value.get();
      }

    /**
     * This method increments the value in a thread safe manner without Synchronization.
     * do while loop takes care of this
     * @return
     */
      public int increment() {
        int v;
        do {
          v = value.get();
        } while(v != value.compareAndSwap(v, v+1));

        return v + 1;
      }
  }
}
