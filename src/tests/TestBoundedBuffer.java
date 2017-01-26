package tests;

import junit.framework.TestCase;
import log.Logger;
import utilconcurrent.barriers.BoundedBuffer;
import utilconcurrent.barriers.TestingThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Feb 25, 2015
 */
public class TestBoundedBuffer extends TestCase {
	public void testEmptyBuffer() {
		BoundedBuffer buf = new BoundedBuffer(10);
		assertTrue("Buf is empty now" , buf.isEmpty());
		assertFalse(buf.isFull());
	}

	public void testFullBuffer() throws InterruptedException {
		BoundedBuffer buf = new BoundedBuffer(10);
		for(int i = 0; i < 10; i++) buf.put(i);

		assertFalse("Buf is full now" , buf.isEmpty());
		assertTrue(buf.isFull());
	}

	public void testBlockingCondition() throws InterruptedException {
		final BoundedBuffer<Integer> buf = new BoundedBuffer<Integer>(10);

		Thread testThread = new Thread() {
			@Override
			public void run() {
				try {
					buf.take();
					fail();
				} catch (InterruptedException e) {
					System.out.println("Interrupted as expected");
				}
			}
		};

		testThread.start();
		Thread.sleep(5000);
		testThread.interrupt();
		testThread.join(5000);
		assertFalse(testThread.isAlive());
		System.out.println("Success " +buf.checkPermits());
	}

	public void testForLeak() throws Exception {
		BoundedBuffer<Big> buf = new BoundedBuffer<Big>(10);
		long freemem = Runtime.getRuntime().freeMemory() / 1000;
		stdout("free memory initially is " + freemem);
		for (int i = 0; i < 10; i++) buf.put(new Big());
		stdout("free memory after buffer is filled " + Runtime.getRuntime().freeMemory() / 1000);
		for (int i = 0; i < 10; i++) buf.take();
		System.gc(); // had to call this to see the free memory increase
		stdout("free memory after buffer is emptied " + Runtime.getRuntime().freeMemory() / 1000);
	}

	class Big { double data [] = new double[1000000];}

	public void testPoolExpansion() throws Exception {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		TestingThreadFactory threadFactory = null;

		if (exec instanceof ThreadPoolExecutor) {
			ThreadPoolExecutor ex = (ThreadPoolExecutor) exec;
			threadFactory = new TestingThreadFactory();
			ex.setThreadFactory(threadFactory);
		}

		for(int i = 0; i < 200; i++) {
			exec.execute(new Runnable() {
				@Override
				public void run() {
					try {
						stdout("Task done");
						Thread.sleep(Long.MAX_VALUE);
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
				}
			});
		}

		for(int j = 0; j < 20 && threadFactory.num.get() < 10; j++)
			Thread.sleep(100);

	  System.out.println(threadFactory.num.get());
	}
}
