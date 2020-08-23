package tests;

import log.Logger;

import java.util.function.Function;

/**
 * Classic fizzBuzz interview question done using 4 threads. See {@link FizzBuzzTest}
 */
public class FizzBuzzThread extends Thread {
    Function<Integer, String> function;
    int current = 1;
    Object lock = new Object();
    int max;

    public FizzBuzzThread(Function<Integer, String> function, int max) {
        this.function = function;
        this.max = max;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (current <= max) {
                String result = function.apply(current);
                if (result != null) Logger.stdout("[" + current + "] " + result);
                current++;
            }
        }
    }
}
