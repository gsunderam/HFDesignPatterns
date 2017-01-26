package utilconcurrent;

/**
 * User: gsunderam
 * Date: Sep 21, 2013
 */
public class BoundedSetTest {
  public static void main(String[] args) throws InterruptedException {
    BoundedHashset<Integer> set = new BoundedHashset<Integer>(5);
    boolean isAdded = false;
    createThreadsAndRun(set);

    //Sleep so that the set can exhaust permits
    Thread.sleep(6000);

    //Create space by removing. The sem.acquire call will get this permit, allowing 5 to be added. Cool!
    set.remove(3);
  }

  private static void createThreadsAndRun(final BoundedHashset<Integer> set) {
    Thread [] threads = new Thread[1];
    int i = 0;

    for(Thread thread : threads) {
        thread = new Thread(new Runnable() {

          public void run() {
            for(int i = 0; i < 6; i++) {
              try {
                set.add(i);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
          }
        }, "THREAD " + (i++));

       thread.start();
      }
  }
}
