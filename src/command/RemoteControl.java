package command;

import java.util.ArrayList;
import java.util.List;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: May 28, 2011
 * This is the INVOKER object
 */
public class RemoteControl {
   private List<Command> onCommands;
   private List<Command> offCommands;
   private Command undoCommand; //To undo the previous command

    public RemoteControl() {
       onCommands = new ArrayList<Command>(7); // concrete command.Command registers itself with the invoker
       offCommands = new ArrayList<Command>(7);

       Command noCommand = new NoCommand(); //Good programming practice to create dummy objects like this
       for (int i = 0;i < 7;i++) {
           onCommands.add(noCommand);
       }

      //Initialize the off command objects
       for (int i = 0;i < 7;i++) {
           offCommands.add(noCommand);
       }

       undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCmd, Command offCmd) {
      onCommands.set(slot, onCmd);
      offCommands.set(slot, offCmd);
    }

    public void onButtonPressed(int slot) { // invoker calls back concrete command.Command, which executes the command.Command on the receiver
       onCommands.get(slot).execute() ; //NPE is eliminated by setting all values to a default NoCommand! Good practice.
       undoCommand = onCommands.get(slot); //stash this command into this reference
    }

    public void offButtonPressed(int slot) {
       offCommands.get(slot).execute(); //NPE is eliminated by setting all values to a default NoCommand! Good practice.
       undoCommand = offCommands.get(slot); //stash the last command in the reference
    }

    public void undoButtonPressed() {
       undoCommand.undo();
    }

  /**
   * Convenience to string method to print slot details
   * @return
   */
    public String toString() {
      StringBuilder sb = new StringBuilder(100);
      for (int i = 0;i < onCommands.size();i++) {
        sb.append("SLOT " + i + " for ON Command = " + onCommands.get(i).getClass().getSimpleName() + "\n");
      }
      return sb.toString();
    }

    public String toStringASlot(int slot) {
      StringBuilder sb = new StringBuilder(50);
      sb.append("SLOT " + slot + " is for " + onCommands.get(slot).getClass().getSimpleName() + "\n");
      return sb.toString();
    }
}
