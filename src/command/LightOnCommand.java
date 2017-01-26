package command;

import static log.Logger.stdout;

/**
 * Created by IntelliJ IDEA.
 * User: gsunderam
 * Date: Oct 18, 2010
 * Time: 4:37:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

	  /**
		 * It is this call that executes the command. Effective delegation is at this point
		 * and the crux of the command pattern
		 */
    public void execute() {
        light.turnOn();
    }

  	public void undo() {
    	light.turnOff();
  	}
}
