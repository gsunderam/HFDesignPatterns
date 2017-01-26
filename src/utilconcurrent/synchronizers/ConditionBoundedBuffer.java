package utilconcurrent.synchronizers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static log.Logger.stderr;
import static log.Logger.stdout;

/**
 *  User: gsunderam
 *  Date: Mar 13, 2015
 *
 *  Algorithm for locks, condition predicates and condition queues
 *
 *  1) Acquire Lock
 *  2) while(predicate not true)
 * 	  	wait() //for predicate to become true
 *  3) perform action
 *  4) notify other waiting thread
 *  5) Relinquish lock
 */
public class ConditionBoundedBuffer<T> {
	private Lock lock = new ReentrantLock();

	private Condition notFull = lock.newCondition(); //count < items.length
	private Condition notEmpty = lock.newCondition();  //count > 0

	private final T items [] = (T[]) new Object[20];
	private int count, tail, head;

	public boolean put(T t) {
	 lock.lock(); //Step 1 above
	 boolean flag = true;

	 try {
		 while (count == items.length) { //Step 2 - two lines
			 	stdout("count is " + count + " awaiting");
			  flag = notFull.await(5, TimeUnit.SECONDS); //false if timeout is exceeded
			 	if (!flag) return flag;
		 }

		 items[tail] = t;  //Step 3 - 3 lines
		 tail = (tail + 1) % items.length;
		 ++count;
		 notEmpty.signal(); //Step 4
	 } catch (Exception e) {
		 	e.printStackTrace();
	 } finally {
		 	lock.unlock(); //Step 5
	 }

	 return flag;
	}

 public T take() throws InterruptedException {
	 T t = null;
	 lock.lock();

	 try {
		while(count == 0)
			notEmpty.await();
		t = items[head];
		items[head] = null;
		 --count;
		head = (head + 1) % items.length;

		notFull.signal();
	 } catch (InterruptedException e) {
		 stderr("Exception is " + e);
		 throw e;
	 } finally {
		 lock.unlock();
	 }

	 return t;
 }

 public int size() {
	 return count;
 }
}
