package utilconcurrent.semphore;

import log.Logger;

import java.util.concurrent.Semaphore;

/**
 * class to demo semaphores. Semaphores are used here to control the order of method
 * invokations by different threads. Always first, second and then third, regardless of how the threads are created.
 * See the Test Driver class. {@link SemophoreTest}.
 */
public class SemphoreDemo {
    Semaphore one, two;

    public SemphoreDemo() {
        one = new Semaphore(1);
        two = new Semaphore(1);
        try {
            one.acquire();
            two.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void first() {
        try {
            Logger.stdout("First");
        } catch (Exception e) {
            e.printStackTrace();
        }

        one.release();
    }

    public void second() {
        try {
            one.acquire();
            Logger.stdout("second");
        } catch (Exception e) {
            e.printStackTrace();
        }

        one.release();
        two.release();
    }

    public void third() {
        try {
            two.acquire();
            Logger.stdout("third");
        } catch (Exception e) {
            e.printStackTrace();
        }

        two.release();
    }
}
