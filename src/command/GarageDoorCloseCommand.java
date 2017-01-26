package command;

import command.Command;
import command.GarageDoor;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: May 28, 2011
 */
public class GarageDoorCloseCommand implements Command {

   private GarageDoor garageDoor;

  public GarageDoorCloseCommand(GarageDoor garageDoor) {
    this.garageDoor = garageDoor;
  }

  public void execute() {
    garageDoor.close();
  }

  public void undo() {
    stdout("Undoing previous operation");
    garageDoor.open();
  }
}
