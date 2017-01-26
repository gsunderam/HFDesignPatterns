package utilconcurrent;

import java.util.concurrent.*;

/**
 * User: gsunderam
 * Date: Apr 7, 2013
 */
public class ThreadPoolManager {
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  private final BlockingQueue<WorkUnit<String>> lbq = new LinkedBlockingQueue<WorkUnit<String>>();

  public ScheduledFuture<?> run(QueueReaderTask task) {
    task.setQueue(lbq);
    ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(task, 3, 5, TimeUnit.SECONDS);
    cancel(scheduledFuture);
    return scheduledFuture;
  }

  private void cancel(final ScheduledFuture<?> handle) {
    scheduler.schedule(new Runnable() {
      public void run() {
         handle.cancel(true);
         scheduler.shutdown();
      }
    }, 40, TimeUnit.SECONDS);
  }

  public void addTask(WorkUnit<String> task) {
    lbq.add(task);
  }
  
}
