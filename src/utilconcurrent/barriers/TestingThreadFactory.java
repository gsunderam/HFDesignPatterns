package utilconcurrent.barriers;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: gsunderam
 * Date: Feb 28, 2015
 */
public class TestingThreadFactory implements ThreadFactory {
	public final AtomicInteger num = new AtomicInteger();
	private final ThreadFactory fact = Executors.defaultThreadFactory();

	@Override
	public Thread newThread(Runnable r) {
		num.incrementAndGet();
		return fact.newThread(r);
	}
}
