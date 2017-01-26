package command;

import java.util.List;

/**
 * User: gsunderam
 * Date: May 29, 2011
 */
public class MacroCommand implements Command {
  List<Command> commands;

  public MacroCommand(List<Command> commands) {
    this.commands = commands;
  }

  public void execute() {
    for (Command cmd : commands) {
      cmd.execute();
    }
  }

  public void undo() {
    for (Command cmd : commands) {
      cmd.undo();
    }
  }
}
