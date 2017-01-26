package executors;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jan 19, 2012
 *
 * WorkUnit that can be submitted for execution
 */
public class PrintJob implements Runnable {
  String name;
  int length;

  public PrintJob(String name, int length) {
    this.name = name;
    this.length = length;
  }

  @Override
  public void run() {
    stdout("Starting print job " + this.name);

    for(int i = 0;i<length;i++) {
      try {
        Thread.sleep(700);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
   stdout("Job " + this.name + " Ended");
  }
}
