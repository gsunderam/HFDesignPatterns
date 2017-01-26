package singleton;

import util.Driver;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: May 28, 2011
 */
public class SingletonTest {
  
  public static void main(String[] args) {
    long begin = System.currentTimeMillis();
    EagerSingleton s = EagerSingleton.getInstance();
    long end = System.currentTimeMillis();
    stdout("Time taken to eagerly fetched instance: " + (end - begin) + " ms");

    begin = System.currentTimeMillis();
    DoubleCheckedSingleton dSingleton = DoubleCheckedSingleton.getInstance();
    end = System.currentTimeMillis();
    stdout("Time for fetching double checked instance " + (end - begin) + " ms");

    //Use the test harness code to invoke the getInstance method concurrently with 5 threads. This needs to be tested
    //in multicore processor
    Runnable task = new Runnable() {
      public void run() {
        ClassicSingleton instance = ClassicSingleton.getInstance();
        stdout("Instance name " + instance.getName() + " Hash Code: " + instance.hashCode());
      }
    };

    Driver twoThreads = new Driver();
    try {
      twoThreads.timeTasks(5, task);
    } catch (InterruptedException e) {
      stdout("The Thread is interrupted");
      Thread.interrupted();
    }
  }
}
