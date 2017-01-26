package utilconcurrent.cache;

import java.util.concurrent.ExecutionException;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 6, 2013
 */
public class MemoizerTest {
  /** Good practice to make inner classes static as the below */
  private static final Computable<Integer, String> expensive = new Computable<Integer, String>() {
    public String compute(Integer key, String threadName) throws InterruptedException {
      return key % 2 == 0 ? "even" : "odd"; //This is an expensive calculation in real world
    }
  };

  private static final Computable<Integer, String> computable = new Memoizer<Integer, String>(expensive);

  /**
   * No Synchronization required here because the <code>computable</code> variable does the look up
   * and processing in a thread safe way. If the caching algorithm is NOT documented to be threadsafe,
   * this method needs to be synchronized.
   * 
   * @param key
   * @return String value cached or calculated
   * @throws ExecutionException
   */
  private String getValue(Integer key, String threadName) throws ExecutionException {
    String value = null;

    try {
      value = computable.compute(key, threadName);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return value;
  }

  public static void main(String[] args) throws ExecutionException {
    MemoizerTest test = new MemoizerTest();
    test.createThreadsAndRun();
  }

  private void createThreadsAndRun() {
    Thread [] threads = new Thread[11];
    int i = 0;

    for(Thread thread : threads) {
        thread = new Thread(new Runnable() {

          public void run() {
            for(int i = 0; i < 5; i++) {
              try {
                stdout(i + ": " + getValue(i, getName()));
              } catch (ExecutionException e) {
                e.printStackTrace();
              }
            }
          }
        }, "THREAD " + (i++));

        thread.start();
      }
  }

  private String getName() {
    return Thread.currentThread().getName();
  }
}
