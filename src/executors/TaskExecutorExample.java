package executors;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Dec 10, 2012
 */

public class TaskExecutorExample {
  private static final String CONFIG_LOCATION = "../resources/spring-context.xml";

  public static void main(String[] args) throws BeansException {
    stdout("User dir" + System.getProperty("user.dir"));
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
    TaskExecutorExample executorExample = context.getBean(TaskExecutorExample.class);
    executorExample.printMessages();
  }

  private class MessagePrinterTask implements Runnable {

    private String message;

    public MessagePrinterTask(String message) {
      this.message = message;
    }

    public void run() {
      System.out.println(message);
    }

  }

  private TaskExecutor taskExecutor;

  public TaskExecutorExample(TaskExecutor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }

  public void printMessages() {
    for(int i = 0; i < 11; i++) {
      taskExecutor.execute(new MessagePrinterTask("Message" + i));
    }
  }

  public void destroy() {
    ((ThreadPoolTaskExecutor) taskExecutor).destroy();
  }
}
