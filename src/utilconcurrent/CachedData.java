package utilconcurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: gsunderam
 * Date: Mar 10, 2013
 *
 * This is JUST a code snippet taken from Java docs that shows how to downgrade from writelock to readlock after
 * updating the cache. Refer "Class ReentrantReadWriteLock" from the API doc
 */
class CachedData {
   Object data;
   volatile boolean cacheValid;
   ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

   void processCachedData() {
     rwl.readLock().lock();
     if (!cacheValid) {
        // Must release read lock before acquiring write lock
        rwl.readLock().unlock();
        rwl.writeLock().lock();
        // Recheck state because another thread might have acquired
        //   write lock and changed state before we did.
        if (!cacheValid) {
          data = new Object();
          cacheValid = true;
        }
        // Downgrade by acquiring read lock before releasing write lock
        rwl.readLock().lock();
        rwl.writeLock().unlock(); // Unlock write, still hold read
     }

     use(data);
     rwl.readLock().unlock();
   }

  private void use(Object data) {
  }
}
