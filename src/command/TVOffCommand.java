package command;

/**
 * User: gsunderam
 * Date: May 29, 2011
 */
public class TVOffCommand implements Command {
  TV tv;

  public TVOffCommand(TV tv) {
    this.tv = tv;
  }

  public void execute() {
     tv.off();
  }

  public void undo() {
    tv.on();
  }
}
