package tests;

import junit.framework.TestCase;
import utilconcurrent.synchronizers.ConditionBoundedBuffer;

import static log.Logger.stderr;
import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Mar 14, 2015
 */
public class TestConditionBoundedBuffer extends TestCase {

	public void testPutAndTake() throws InterruptedException {
		ConditionBoundedBuffer<String> buf = new ConditionBoundedBuffer<String>();

		for(int i = 0; i < 20; i++) {
		 buf.put(Integer.toString(i));
		}

		stdout("Number of elements " + buf.size());

		for(int i = 0; i < 11; i++) {
		 buf.take();
		}

		stdout("Number of elements " + buf.size());
		assertTrue("Count is 9", 9 == buf.size());
		buf = null;
	}

	public void testTimeOut() {
		ConditionBoundedBuffer<String> buf = new ConditionBoundedBuffer<String>();
		boolean flag = true;

		try {
			for(int i = 0; i < 21; i++) {
			 flag = buf.put(Integer.toString(i));
			}
			
			assertTrue("Test passed", !flag);
		} catch (Exception e) {
			stderr("Exception thrown " + e.getCause().getMessage());
		} finally {
			buf = null;
		}
	}

	public void testInterrupt() throws InterruptedException {
		final ConditionBoundedBuffer buf = new ConditionBoundedBuffer();

		Thread testThread = new Thread() {
			public void run() {
				try {
					buf.take();
					fail();
				} catch(Exception e) {
					stderr("Exception is " + e);
				}
			}
		};

		testThread.start();
		Thread.sleep(5000);
		testThread.interrupt();
		testThread.join();
		assertFalse(testThread.isAlive());
	}
}
