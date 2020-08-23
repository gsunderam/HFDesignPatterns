package tests;

/**
 * FizzzBuzz using 4 threads and java 8 methods references. Parallel processing
 * improves performance
 */
public class FizzBuzzTest {
    public static void main(String[] args) {
        FizzBuzzTest test = new FizzBuzzTest();
        FizzBuzzThread thread1 = new FizzBuzzThread(test::checkDiv3, 20);
        FizzBuzzThread thread2 = new FizzBuzzThread(test::checkDiv5, 20);
        FizzBuzzThread thread3 = new FizzBuzzThread(test::checkDiv15, 20);
        FizzBuzzThread thread4 = new FizzBuzzThread(test::checkDiv, 20);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    public String checkDiv3(int i) {
        if (i % 3 == 0 && i % 5 != 0) return "Fizz";
        return null;
    }

    public String checkDiv5(int i) {
        if (i % 5 == 0 && i % 3 != 0) return "Buzz";
        return null;
    }

    public String checkDiv15(int i) {
        if (i % 3 == 0 && i % 5 == 0) return "FizzBuzz";
        return null;
    }

    public String checkDiv(int i) {
        if (i % 3 != 0 && i % 5 != 0) return Integer.toString(i);
        return null;
    }
}
