package command;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: May 28, 2011
 */
public class GarageDoor {

  public void open() {
    stdout("Garage Door is opening");
  }

  public void close() {
    stdout("Garage Door is Closing");
  }

  public void stop() {
    stdout("Garage Door is STOPPING");
  }

  public void lightOn() {
    stdout("Garage Door light is ON!");
  }

  public void lightOff() {
    stdout("Garage Door LIGHT os OFF");
  }
}
