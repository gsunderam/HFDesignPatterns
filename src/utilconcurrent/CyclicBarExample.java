package utilconcurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * User: gsunderam
 * Date: Feb 24, 2015
 */
public class CyclicBarExample {
	// create a cyclic barrier that will wait for three calls before
    // unlocking
    private static final int NO_OF_THREADS = 3;
    private CyclicBarrier barrier = new CyclicBarrier(NO_OF_THREADS);

    public static void main(String[] args) throws InterruptedException {
        CyclicBarExample instance = new CyclicBarExample();
        instance.init();
    }

    public void init() throws InterruptedException {
        // create three threads that will call await on the cyclic barrier
        for (int i=0; i<NO_OF_THREADS; ++i) {
            Thread th = new Thread(new MyWorkerThread(), "Worker" + i);
            th.start();

            // to help visualise I add a delay between creation
            Thread.currentThread().sleep(1000);
        }

    }

    /**
     * Here is the runnable used by the above threads, it just waits for the
     * barrier before proceeding to log a line and exit. Notice that the
     * barrier unlocked log line only occurs after all three threads reach
     * the barrier.
     */
    private class MyWorkerThread implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread started. Waiting in the barrier for other to join..." + barrier.getNumberWaiting());

            try {
                barrier.await();
            } catch (InterruptedException e){
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
							e.printStackTrace();
						}

						System.out.println("Barrier unlocked");
					  System.out.println("Work completed success");

					try {
						barrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}

					 System.out.println("Work completed success again! " + barrier.getParties());
				}
    }
}
