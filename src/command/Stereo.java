package command;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: May 28, 2011
 */
public class Stereo {

  public void on() {
    stdout("Stereo is ON");
  }

  public void off() {
    stdout("Stereo is OFF");
  }

  public void setCd(String mode) {
    stdout("Stereo is on CD mode " + mode);
  }

  public void setVolume(int volume) {
    stdout("Stereo's volume is set to " + volume);
  }
}
