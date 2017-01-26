package command;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: May 28, 2011
 */
public class StereoOnCommand implements Command {

  private Stereo stereo;

  public StereoOnCommand(Stereo stereo) {
    this.stereo = stereo;
  }

  public void execute() {
    stereo.setCd("load");
    stereo.setVolume(11);
    stereo.on();
  }

  public void undo() {
    stereo.off();
  }
}
