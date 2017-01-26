package command;

import static log.Logger.stdout;

/**
 * Fan stop command
 */
public class FanStopCommand  implements Command {
    private Fan fan;
    FanStates prevSpeed;

    public FanStopCommand(Fan fan) {
        this.fan = fan;
    }
    
    public void execute() {
        prevSpeed = fan.speed; //Need to set this BEFORE stopRotate is called
        fan.stopRotate();
    }

  public void undo() {
     stdout("Undoing previous operation");
     fan.startRotate(prevSpeed);
  }
}
