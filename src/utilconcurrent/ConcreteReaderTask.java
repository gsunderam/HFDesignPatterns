package utilconcurrent;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Apr 7, 2013
 */
public class ConcreteReaderTask extends QueueReaderTask {
  @Override
  protected void doAction(WorkUnit<String> task) {
    stdout("Do Action called: Executing work");
    task.executeWork();
  }
}
