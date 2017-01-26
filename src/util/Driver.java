package util;

import java.util.concurrent.CountDownLatch;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: May 28, 2011
 * Code taken from the book Java Concurrency in practice by Brian Goetz. Test in multi processor machine
 */
public class Driver {

  public static long timeTasks(int nThreads, final Runnable task)
            throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        final int N = nThreads;

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        //wait till the threads are created
                        stdout("Awaiting for ALL " + N + "threads to start");
                        startGate.await();
                        stdout("Wait is over");
                        try {
                            task.run();
                        } finally {
                            endGate.countDown(); //ALL Threads s till the LAST thread finishes
                        }
                    } catch (InterruptedException ignored) {
                        stdout("Harness thread is interrupted");
                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        stdout("Time taken for " + nThreads + " threads: " + (end - start));
        return end - start;
    }
}
