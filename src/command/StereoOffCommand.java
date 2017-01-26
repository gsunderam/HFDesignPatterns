package command;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: May 28, 2011
 */
public class StereoOffCommand implements Command {
  private Stereo stereo;

  public StereoOffCommand(Stereo stereo) {
    this.stereo = stereo;
  }

  public void execute() {
    stereo.setVolume(0);
    stereo.setCd("eject");
    stereo.off();
  }

  public void undo() {
    stereo.on();
  }
}
