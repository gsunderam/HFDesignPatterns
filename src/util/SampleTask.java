package util;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Oct 25, 2012
 */
public class SampleTask implements Runnable {

  private String name;

  public SampleTask(String name) {
    this.name = name;
  }

  @Override
   public void run() {
     stdout("Running job " + name);
     try {
       Thread.sleep(1000);
     } catch (InterruptedException e) {
          e.printStackTrace();
     } finally {
      stdout("Completed " + name);
    }
  }
}
