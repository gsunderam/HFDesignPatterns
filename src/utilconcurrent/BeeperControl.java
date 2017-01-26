package utilconcurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * User: gsunderam
 * Date: Apr 7, 2013
 *
 * Taken from Java API docs, this example demonstrates a simple use of ScheduledExecutorService.
 * This can be used to replace TimerTask. Also note that TimerTask has a subtle bug, per Brian Goetz
 * Concurrency in practice
 */
public class BeeperControl {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void beepForAnHour() {
        /** Create you task. Good idea to make this final as it protects the task from being mutated */
        final Runnable beeper = new Runnable() {
                public void run() { System.out.println("beep"); }
        };

        /** provide the task as input to the scheduler. "final" because the anonymous method local inner class needs this reference */
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 10, TimeUnit.SECONDS);

        // Cancel the task after a fixed period. 40 sec in this case
        scheduler.schedule(new Runnable() {
                public void run() {
                  beeperHandle.cancel(true);
                  /** Needed to STOP the executor service and hence exit the program */
                  scheduler.shutdown();
                }
            }, 40 , TimeUnit.SECONDS);
    }

  public static void main(String[] args) {
    new BeeperControl().beepForAnHour();
  }
}
