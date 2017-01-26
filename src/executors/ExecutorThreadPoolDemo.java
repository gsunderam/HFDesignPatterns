package executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jan 19, 2012
 * Java 6 SDK Thread pool demo
 */
public class ExecutorThreadPoolDemo {

  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(4);

    List<Runnable> jobs = new ArrayList<Runnable>(7);
    jobs.add(new PrintJob("GS", 4));
    jobs.add(new PrintJob("Uma", 2));
    jobs.add(new PrintJob("John", 7));
    jobs.add(new PrintJob("Ken", 1));
    jobs.add(new PrintJob("Michelle", 3));
    jobs.add(new PrintJob("Success", 5));
    jobs.add(new PrintJob("Wells", 8));

    for(Runnable job : jobs) {
      pool.submit(job);
    }

    if (pool != null) {
      pool.shutdown();
    }
  }
}
