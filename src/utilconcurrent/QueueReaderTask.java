package utilconcurrent;

import java.util.concurrent.BlockingQueue;

/**
 * User: gsunderam
 * Date: Apr 7, 2013
 */
public abstract class QueueReaderTask implements Runnable {

  private boolean shutdown = false;
  protected BlockingQueue<WorkUnit<String>> ibq;

  public void run() {
    while (!shutdown) {
      WorkUnit<String> task = ibq.poll();
      if (task != null) doAction(task);
      else shutdown = true;
    }
  }

  protected abstract void doAction(WorkUnit<String> task);

  public void setQueue(BlockingQueue<WorkUnit<String>> ibq) {
    this.ibq = ibq;
  }

}
