package command;

/**
 * User: gsunderam
 * Date: May 29, 2011
 */
public class TVOnCommand implements Command {
  TV tv;

  public TVOnCommand(TV tv) {
    this.tv = tv;
  }

  public void execute() {
      tv.on();
  }

  public void undo() {
    tv.off();
  }
}
