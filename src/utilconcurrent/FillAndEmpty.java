package utilconcurrent;

import java.util.List;
import java.util.Stack;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Mar 25, 2013
 */
class FillAndEmpty {
  Exchanger<Stack<String>> exchanger = new Exchanger<Stack<String>>();
  Stack<String> initialEmptyBuffer = new Stack<String>();
  Stack<String> initialFullBuffer;
  private volatile int itemCount;
  Thread fillingThread;
  Thread emptyingThread;
//  private ReentrantLock lock = new ReentrantLock();
//  private Condition canContinue = lock.newCondition();
  private Object lock = new Object();

  FillAndEmpty(Stack<String> initialFullBuffer) {
    this.initialFullBuffer = initialFullBuffer;
  }

  class FillingLoop implements Runnable { 
       public void run() {
         Stack<String> currentBuffer = initialEmptyBuffer;
         try {
           while (currentBuffer != null && currentBuffer.size() < 5) {
               synchronized (lock) {
                 if (itemCount >= 20) {
                   stdout("Buffer capacity reached. Good Bye from the filler");
                   break;
                 }
                 itemCount++;
               }

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
             synchronized (lock) {
               if (itemCount >= 20) {
                 stdout("Buffer capacity reached. Good Bye from the emptier");
                 break;
               }
             }
           
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
     FillAndEmpty fillAndEmpty = new FillAndEmpty(buffer);
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
          synchronized (lock) {
            if (itemCount >= 20) {
               stdout("Interrupting Filler and consumer threads...");
               filler.interrupt();
               consumer.interrupt();
               stdout("Interrupted");
               break;
            }
          }
        }
        stdout("Monitor thread exiting");
    }
  }
}
