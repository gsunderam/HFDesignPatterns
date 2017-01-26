package utilconcurrent;

import java.util.Random;
import java.util.concurrent.*;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Oct 25, 2013
 *
 * This demonstrates the use of CompletionService in the util concurrent api
 * Can be used to execute homogenous tasks in parallel
 */
public class RandomNumbersSum {
  private final Executor exec = Executors.newCachedThreadPool();
  private static final int SIZE = 5;

  public static void main(String[] args) throws Exception {
    new RandomNumbersSum().generateRandomNumbers();
  }

  public <T> void generateRandomNumbers() throws InterruptedException, ExecutionException {
    CompletionService<T> completionService = new ExecutorCompletionService<T>(exec);
    final Random r = new Random(2);

    for (int i = 0; i < SIZE; i++) {
      completionService.submit(new Callable<T>() {
        //Just execute the generation of random numbers in parallel
        public T call() throws Exception {
          int temp = r.nextInt();
          if (temp < 0) temp = Math.abs(r.nextInt());
          return (T) Integer.valueOf(r.nextInt() / 100000000);
        }
      });
    }

    //When ready calculate the sum
    int sum = 0;
    for (int i = 0; i < SIZE; i++) {
      Integer n = (Integer) completionService.take().get();
      sum += n;
      stdout("Random number " + (i+1) + " is " + n);
    }

    stdout("Sum of the random numbers is " + sum);
  }

}
