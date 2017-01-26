package command;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: May 28, 2011
 */
public class GarageDoorOpenCommand implements Command {
  private GarageDoor garageDoor;

  public GarageDoorOpenCommand(GarageDoor garageDoor) {
    this.garageDoor = garageDoor;
  }

  public void execute() {
    garageDoor.open();
  }

  public void undo() {
    stdout("Undoing previous operation");
    garageDoor.close();
  }
}
