package utilconcurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 27, 2015
 */
public class Heartbeat {
	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		
    Runnable pinger = new Runnable() {
      public void run() {
        System.out.println("PING!");
      }
    };

		ScheduledFuture<?> f = ses.scheduleAtFixedRate(pinger, 1, 1, TimeUnit.SECONDS);
		Thread.sleep(5000);
		boolean b = f.cancel(true);
		stdout("Is task cancelled " + b);
		stdout("Is task done " + f.isDone());
		System.exit(0);
	}
}
