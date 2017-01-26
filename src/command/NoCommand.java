package command;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: May 28, 2011
 * Just a dummy class to take care of Nulls
 */
public class NoCommand implements Command {
  public void execute() {
    stdout("Just a NO command to take care of null objects");
    return;
  }

  public void undo() {
    return;
  }
}
