package tests;

import utilconcurrent.synchronizers.OneShotLatch;
import java.util.concurrent.CountDownLatch;
import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Mar 15, 2015
 */
public class TestOneShotLatch {
	private static final OneShotLatch startGate = new OneShotLatch();
	private static final CountDownLatch endGate = new CountDownLatch(5);

	public static void main(String[] args) throws InterruptedException {
		Thread threads [] = new Thread[5];

		for(int i = 0; i < 5; i++) {
			threads[i] = new Thread(new Runnable() {
				public void run() {
					//startGate to start concurrently
					try {
						startGate.await();
						stdout(doWork() + " by " + Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
//						stdout(Thread.currentThread().getName() + " Completed");
						endGate.countDown(); //wait for all tasks to complete
					}
				}
			}, "Thread" + i);
		}

		for(int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		stdout("Starting all tasks...concurrently");
		startGate.signal();
		stdout("Waiting to complete");
		endGate.await();
		stdout("End gate opened...Done");
	}

	public static String doWork() {
		return "Success";
	}
}
