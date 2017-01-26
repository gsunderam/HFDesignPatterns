package command;

/**
 * User: gsunderam
 * Date: May 29, 2011
 */
public class FanMediumCommand implements Command {

  Fan fan;
  FanStates prevSpeed;
  FanStates medium = FanStates.MEDIUM;

  public FanMediumCommand(Fan fan) {
    this.fan = fan;
  }

  public void execute() {
    prevSpeed = fan.speed;
    fan.startRotate(FanStates.MEDIUM);
  }

  public void undo() {
     if (prevSpeed == FanStates.HIGH) {
       fan.startRotate(FanStates.HIGH);
     } else if (prevSpeed == FanStates.MEDIUM) {
       fan.startRotate(FanStates.MEDIUM);
     } else if (prevSpeed == FanStates.LOW) {
       fan.startRotate(FanStates.LOW);
     } else fan.stopRotate();
  }
}
