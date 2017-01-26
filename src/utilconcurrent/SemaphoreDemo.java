package utilconcurrent;

import java.util.concurrent.Semaphore;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Mar 8, 2013
 *
 * Semaphores are used limit execution of a piece of code by PARALLEL or Concurrent threads.
 * permit and acquire methods are used. Any number of semaphores can acquire and release permits
 */
public class SemaphoreDemo {

  private static Semaphore locker;
  private static final String names [] = {"rama", "krishna", "govinda", "narayana", "madhava", "shiva"};

  public static void main(String[] args) {
    locker = new Semaphore(2);
    for(String name : names) {
       //Thread worker = new Thread(new Worker(name, (int)(40 * Math.random())));
       Thread worker = new Thread(new Worker(name, 4));
       worker.start();
    }
  }

  private static class Worker implements Runnable {
    String name;
    int num;

    public Worker(String name, int num) {
      this.name = name;
      this.num = num;
    }

    @Override
    public void run() {
      try {
          locker.acquire();
          //stdout("Num passed " + num);
          for(int i = 0;i < num; i++) {
            stdout("Name " + this.name + " for " + (i+1) + " time");
            Thread.sleep(100);
          }
          locker.release();
      } catch(InterruptedException ie) {
         stdout("Interrupted Exception is: " + ie);
      }
    }
  }
}
