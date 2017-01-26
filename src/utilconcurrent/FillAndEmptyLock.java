package utilconcurrent;

import java.util.Stack;
import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Mar 25, 2013
 *
 * Reference implemetation that shows how to use read and write locks that was added in java 5 util concurrent package
 * This is an alternative to using synchronized statement.
 * Read Locks are acquired and released before and after reading itemCount. Write locks are acquired and released before
 * and after writing to the itemCount
 */
class FillAndEmptyLock {
  Exchanger<Stack<String>> exchanger = new Exchanger<Stack<String>>();
  Stack<String> initialEmptyBuffer = new Stack<String>();
  Stack<String> initialFullBuffer;
  private volatile int itemCount;
  Thread fillingThread;
  Thread emptyingThread;

  //concurrent package imports
  private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  private Lock readLock = lock.readLock();
  private Lock writeLock = lock.writeLock();

  FillAndEmptyLock(Stack<String> initialFullBuffer) {
    this.initialFullBuffer = initialFullBuffer;
  }

  class FillingLoop implements Runnable { 
       public void run() {
         Stack<String> currentBuffer = initialEmptyBuffer;
         try {
           while (currentBuffer != null && currentBuffer.size() < 5) {
             readLock.lock();
             if (itemCount >= 20) {
               stdout("Buffer capacity reached. Good Bye from the filler");
               break;
             }
             //TODO: In real world do this in the finally block! NOT here
             readLock.unlock();

            /** Acquire the write lock while mutating the state of itemCount */
            writeLock.lock();
            itemCount++;
            writeLock.unlock(); //Release it once mutation is done

            addToBuffer(currentBuffer, itemCount);

           if (currentBuffer.size() == 5)  {
               stdout("Buffer is full: Exchanging...");
//               currentBuffer = exchanger.exchange(currentBuffer, 5, TimeUnit.SECONDS);
               if (itemCount < 20) currentBuffer = exchanger.exchange(currentBuffer);
             stdout("Buffer is full: Exchanged");
           }
           }
         } catch (InterruptedException ex) {
            stdout("FILLING Thread interrupted");
            ex.printStackTrace();
         }
         stdout("FILLING Thread Exiting");
     }
   }

  private void addToBuffer(Stack<String> currentBuffer, int i) {
      String item = currentBuffer.push("Item " + i);
      stdout("FILLING LOOP: Item pushed " + item);
  }

  class EmptyingLoop implements Runnable {
     public void run() {
       Stack<String> currentBuffer = initialFullBuffer;
       try {
         while (currentBuffer != null) {
           readLock.lock();
           if (itemCount >= 20) {
             stdout("Buffer capacity reached. Good Bye from the emptier");
             break;
           }
           //TODO: In real world do this in the finally block! NOT here
           readLock.unlock();

           takeFromBuffer(currentBuffer);

           if (currentBuffer.isEmpty()) {
              if (itemCount < 20) {
                stdout("buffer is empty: Exchanging...");
                currentBuffer = exchanger.exchange(currentBuffer);
                stdout("buffer is empty: Exchanged");
              }
           }
         }
       } catch (InterruptedException ex) {
         stdout("EMPTYING Thread interrupted");
         ex.printStackTrace();
       } catch(Exception e) {
         stdout("Non interrupted exception: " + e.getCause() + " Msg: " + e.getMessage());
       }

       stdout("EMPTYING Thread exiting");
     }
   }

  private void takeFromBuffer(Stack<String> currentBuffer) {
    String item = currentBuffer.pop();
    stdout("EMPTYING LOOP: Item Popped " + item);
  }

  void start() {
     fillingThread = new Thread(new FillingLoop());
     fillingThread.start();
     emptyingThread = new Thread(new EmptyingLoop());
     emptyingThread.start();
   }

   void monitor() {
      Thread monitorThread = new Thread(new MyRunnable(fillingThread, emptyingThread));
      monitorThread.start();
   }

   private boolean isInterrupted() {
     return emptyingThread.isInterrupted();
   }

  public static void main(String[] args) throws InterruptedException {
     Stack buffer = new Stack();
     buffer.push("item1");
     FillAndEmptyLock fillAndEmpty = new FillAndEmptyLock(buffer);
     fillAndEmpty.start();
    
     //Allow the threads to start
     Thread.sleep(1000);

     //This is the monitor thread that keeps track of the end condition
     fillAndEmpty.monitor();
     stdout("Interrupted: " + fillAndEmpty.isInterrupted());
  }

  class MyRunnable implements Runnable {
    Thread filler;
    Thread consumer;

    private MyRunnable(Thread filler, Thread consumer) {
      this.filler = filler;
      this.consumer = consumer;
    }

    @Override
    public void run() {
      while(true) {
        stdout("Monitor Thread: Count: " + itemCount);

        readLock.lock();
        if (itemCount >= 20) {
           stdout("Interrupting Filler and consumer threads...");
           filler.interrupt();
           consumer.interrupt();
           stdout("Interrupted");
           break;
        }
        readLock.unlock();
      }
      stdout("Monitor thread exiting");
    }
  }
}
