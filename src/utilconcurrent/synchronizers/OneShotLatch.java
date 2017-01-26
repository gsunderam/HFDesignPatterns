package utilconcurrent.synchronizers;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Mar 15, 2015
 */
public class OneShotLatch {
	private final Sync sync = new Sync();

	public void signal() {
		sync.releaseShared(0);
	}

	public void await() throws InterruptedException {
		sync.acquireSharedInterruptibly(0);
	}
	
	private class Sync extends AbstractQueuedSynchronizer {

		/** Seems like this can return any integer other than 0 or 1 */
		protected int tryAcquireShared(int ignored) {
			return getState() == 1 ? 1 : -1;
		}

		protected boolean tryReleaseShared(int ignored) {
			setState(1);
			return true;
		}
	}
}
