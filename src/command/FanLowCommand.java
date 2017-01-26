package command;

/**
 * User: gsunderam
 * Date: May 29, 2011
 */
public class FanLowCommand implements Command {
  Fan fan;
  FanStates prevSpeed;
  FanStates low = FanStates.LOW;

  public FanLowCommand(Fan fan) {
    this.fan = fan;
  }

  public void execute() {
    prevSpeed = fan.speed;
    fan.startRotate(low);
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

  public Fan getFan() {
    return fan;
  }

  public FanStates getPrevSpeed() {
    return prevSpeed;
  }
}
