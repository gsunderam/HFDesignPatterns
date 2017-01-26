package utilconcurrent;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: gsunderam
 * Date: Mar 10, 2013
 *
 * This is a simple reference map implementation that uses java 5 util concurrent package's lock mechanism in lieu
 * of the synchronized monitor. ReadWrite lock is leveraged to provide locking for the get and put operations
 */
class RWDictionary {
    private final Map<String, Data> m = new TreeMap<String, Data>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public Data get(String key) {
        r.lock();
        try {
          return m.get(key);
        } finally {
          r.unlock();
        }
    }
  
    public String[] allKeys() {
        r.lock();
        try {
          return (String[]) m.keySet().toArray();
        } finally {
          r.unlock();
        }
    }
  
    public Data put(String key, Data value) {
        w.lock();
        try {
          return m.put(key, value);
        } finally {
          w.unlock();
        }
    }

    public void clear() {
        w.lock();
        try {
          m.clear();
        } finally {
          w.unlock();
        }
    }

    public class Data {
      String name;
      int id;

      public Data(String name, int id) {
        this.name = name;
        this.id = id;
      }
    }
 }
