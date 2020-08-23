package tests;

import log.Logger;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * Classic fizzBuzz interview question done using 4 threads. See {@link FizzBuzzTest}
 */
public class FizzBuzzThread extends Thread {
    private final Function<Integer, String> function;
    private final AtomicInteger current = new AtomicInteger(1);
    private int max;

    public FizzBuzzThread(Function<Integer, String> function, int max) {
        this.function = function;
        this.max = max;
    }

    @Override
    public void run() {
        while (current.get() <= max) {
            String result = function.apply(current.intValue());
            if (result != null) Logger.stdout("[" + current.intValue() + "] " + result);
            current.incrementAndGet();
        }
    }
}
