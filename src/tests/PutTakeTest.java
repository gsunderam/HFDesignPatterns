package tests;

import junit.framework.Assert;
import log.Logger;
import util.RandomNumberGenerator;
import utilconcurrent.barriers.BarrierTimer;
import utilconcurrent.barriers.BoundedBuffer;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static junit.framework.Assert.fail;
import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Feb 27, 2015
 */
public class PutTakeTest {
	private final AtomicInteger putSum = new AtomicInteger(0);
	private final AtomicInteger takeSum = new AtomicInteger(0);
	private BoundedBuffer<Integer> buf;
	private static final ExecutorService pool = Executors.newCachedThreadPool();
	private int nthreads, ntrials;
	private CyclicBarrier barrier;
	private BarrierTimer timer = new BarrierTimer();

	public PutTakeTest(int capacity, int nthreads, int ntrials) {
		buf = new BoundedBuffer<Integer>(capacity);
		this.nthreads = nthreads;
		this.ntrials = ntrials;
		barrier = new CyclicBarrier(nthreads * 2 + 1, timer);
	}

	public static void main(String[] args) {
		new PutTakeTest(1000, 25, 100000).testBoundedBuffer();
		pool.shutdown(); //shuts the service down faster
	}

	private void testBoundedBuffer() {
		timer.clear();
		for(int i = 0; i < nthreads; i++) {
			pool.execute(new Producer());
			pool.execute(new Consumer());
		}
		
		try {
			barrier.await(); //barrier pair 1 - all threads to start
			barrier.await(); //barrier pair 2  -- all to finish
			long throughput = timer.getTime() / (nthreads * ntrials);
			System.out.println("Putsum: " + putSum.get() + "   TakeSum: " + takeSum.get());
			Assert.assertEquals(putSum.get(), takeSum.get());
			stdout("Through put (Time taken to process one unit of work) " + throughput);
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	class Producer implements Runnable {
		@Override
		public void run() {
			try {
				int sum = 0;
				int seed = (this.hashCode() ^ (int) System.nanoTime());
				barrier.await(); //barrier pair 1
				
			  for(int i = 0; i < ntrials; i++) {
					buf.put(seed);
					sum += seed;
					seed = RandomNumberGenerator.xorShift(seed);
				}
				putSum.getAndAdd(sum); //Use atomic variables to do compound actions in a thread safe way
				barrier.await(); //barrier pair 2
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}

	class Consumer implements Runnable{
		@Override
		public void run() {
	  	try {
				int sum = 0;
				barrier.await();  //barrier pair 1
				
			  for(int i = 0; i < ntrials; i++) {
					sum += buf.take();
				}

				takeSum.getAndAdd(sum);
				barrier.await(); //barrier pair 2
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}
}
