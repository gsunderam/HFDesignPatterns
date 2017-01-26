package utilconcurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Oct 25, 2013
 */
public class InvokeAllDemo {
  private ExecutorService service = Executors.newFixedThreadPool(5);

  class PrintTask<T> implements Callable<T> {
    private String name;

    public PrintTask(String name) {
      this.name = name;
    }

    @Override
    public T call() throws Exception {
      return (T) (Double)(Math.random() * 1000);
    }
  }

  List<PrintTask<Double>> tasks = new ArrayList<PrintTask<Double>>(5);

  public void invokeAll() throws InterruptedException, ExecutionException {
    for (int i = 0; i < 5; i++) {
        tasks.add(new PrintTask<Double>("Task " + i));
    }

    List<Future<Double>> futures = service.invokeAll(tasks);
    Iterator<PrintTask<Double>> it = tasks.iterator();
    int s = 0;

    for(int i = 0; i < tasks.size(); i++) {
      int result = futures.get(i).get().intValue();
      stdout("Result corresponding to " + it.next().name + " is " + result);
      s += result;
    }

    stdout("Sum of the numbers " + s);

    service.shutdown();
  }

  public static void main(String[] args) {
    try {
      new InvokeAllDemo().invokeAll();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
