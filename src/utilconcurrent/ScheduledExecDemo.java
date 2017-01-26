package utilconcurrent;

/**
 * User: gsunderam
 * Date: Apr 7, 2013
 */
public class ScheduledExecDemo {
  public static void main(String[] args) {
    ThreadPoolManager tpm = new ThreadPoolManager();
    WorkUnit<String> task = new WorkUnit<String>("gs");
    WorkUnit<String> task1 = new WorkUnit<String>("is");
    WorkUnit<String> task2 = new WorkUnit<String>("succeeding");
    WorkUnit<String> task3 = new WorkUnit<String>("in his pursuits");

    tpm.addTask(task);
    tpm.addTask(task1);
    tpm.addTask(task2);
    tpm.addTask(task3);

    QueueReaderTask queueReaderTask = new ConcreteReaderTask();
    tpm.run(queueReaderTask);
  }
}
