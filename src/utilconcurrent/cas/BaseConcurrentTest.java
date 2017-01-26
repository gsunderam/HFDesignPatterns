package utilconcurrent.cas;

import utilconcurrent.synchronizers.OneShotLatch;

import java.util.concurrent.CountDownLatch;

/**
 * User: gsunderam
 * Date: Mar 19, 2015
 */
public class BaseConcurrentTest {
	public OneShotLatch startLatch() {
		return new OneShotLatch();
	}

	public CountDownLatch end(int n) {
		return new CountDownLatch(n);
	}
}
