package utilconcurrent.semphore;

/**
 * 3 threads are created. each calls first, second and third methods respectively
 * Order is controlled using semaphores. Reqt is to print first, second and then third
 */
public class SemophoreTest {
    public static void main(String[] args) {
        SemphoreDemo demo = new SemphoreDemo();
        Thread thread1 = new Thread(() -> demo.first());
        Thread thread2 = new Thread(() -> demo.second());
        Thread thread3 = new Thread(() -> demo.third());
        thread3.start();
        thread2.start();
        thread1.start();
    }
}
