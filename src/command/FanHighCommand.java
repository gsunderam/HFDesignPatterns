package command;

/**
 * User: gsunderam
 * Date: May 29, 2011
 */
public class FanHighCommand implements Command {
  Fan fan;
  FanStates prevSpeed;
  FanStates high = FanStates.HIGH;

  public FanHighCommand(Fan fan) {
    this.fan = fan;
  }

  public void execute() {
    prevSpeed = fan.speed;
    fan.startRotate(high);
  }

  public void undo() {
     if (prevSpeed == FanStates.HIGH) {
       fan.startRotate(high);
     } else if (prevSpeed == FanStates.MEDIUM) {
       fan.startRotate(FanStates.MEDIUM);
     } else if (prevSpeed == FanStates.LOW) {
       fan.startRotate(FanStates.LOW);
     } else fan.stopRotate();
  }
}
