package utilconcurrent.cas;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 1, 2013
 */
public class AtomicStackTest {
  private ConcurrentStack stack;

  public static void main(String[] args) {
    AtomicStackTest stackTest = new AtomicStackTest();
    stackTest.test();
    
    try {
      Thread.sleep(8000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    /** Uncomment to test that stack is formed correctly */
    stackTest.enumerate();
  }

  private void test() {
    stack = new ConcurrentStack();
    createThreadsAndRun();
  }

  private void createThreadsAndRun() {
    Thread [] threads = new Thread[20];
    int i = 0;
    
    for(Thread thread : threads) {
        thread = new Thread(new Runnable() {

          public void run() {
            for (int j = 0; j < 10; j++) {
              stack.push(j, getName());
            }

            for (int j = 0; j < 10; j++) {
              stdout("Item popped by thread: " + getName() + ": " + stack.pop(getName()));
            }
          }
        }, "THREAD " + (i++));

       thread.start();
      }
  }

  public void enumerate() {
      stack.enumerate();
  }

  private String getName() {
    return Thread.currentThread().getName();
  }
}
