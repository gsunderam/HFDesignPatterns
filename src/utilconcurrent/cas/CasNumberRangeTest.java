package utilconcurrent.cas;

import utilconcurrent.synchronizers.OneShotLatch;

import java.util.concurrent.CountDownLatch;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Aug 31, 2013
 *
 * Test driver to test the CAS number range program 
 */
public class CasNumberRangeTest extends BaseConcurrentTest {
  public static void main(String[] args) throws InterruptedException {
    CasNumberRangeTest range = new CasNumberRangeTest();
    Thread [] threads = new Thread[10];
    range.test(threads);
  }

  private void test(final Thread[] threads) throws InterruptedException {
    final CasNumberRange numberRange = new CasNumberRange();
    int count = 0;
		final OneShotLatch startLatch = startLatch();
		final CountDownLatch end = end(threads.length);

		for(Thread thread : threads) {
          thread = new Thread(new Runnable() {

            @Override
            public void run() {
						try {
						 startLatch.await();
						 for(int i = 10, j = 20; i < 20 || j > 15; i++, j--) {
//							 stdout("NAME: " + Thread.currentThread().getName());
							 numberRange.setLower(i, Thread.currentThread().getName());
							 numberRange.setUpper(j, Thread.currentThread().getName());
						 }
						} catch(Exception e) {
							e.printStackTrace();
						} finally {
								end.countDown();
						}
						}
          }, "Thread" + (count++));

         thread.start();
     }

		 startLatch.signal();
		 end.await();

     stdout("Range: Lower Value " + numberRange.getLower());
     stdout("Range: Upper Value " + numberRange.getUpper());
  }
}
