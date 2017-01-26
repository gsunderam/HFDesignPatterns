package command;

import static log.Logger.stdout;

/**
 * Created by IntelliJ IDEA.
 * User: gsunderam
 * Date: Apr 5, 2011
 * Time: 10:27:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class FanStartCommand implements Command {
    private Fan fan;

    public FanStartCommand(Fan fan) {
        this.fan = fan;
    }

    public void execute() {
        fan.startRotate(FanStates.HIGH);
    }

  public void undo() {
     stdout("Undoing previous operation");
    fan.stopRotate();
  }
}
