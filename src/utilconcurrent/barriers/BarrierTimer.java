package utilconcurrent.barriers;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Feb 28, 2015
 */
public class BarrierTimer implements Runnable {
	private boolean isStarted;
	private long startTime, endTime;

	@Override
	public synchronized void run() {
		stdout("Calling barrier action..." + isStarted);
		long t = System.nanoTime();
		if (!isStarted) {
			startTime = t;
			isStarted = true;
		} else endTime = t;
	}

	public synchronized long getTime() {
		return endTime - startTime;
	}

	public void clear() {
		isStarted = false;
	}
}
