package compound;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 24, 2011
 */
public class Goose {

  public String getName() {
    return "Goose";
  }

  public void honk() {
    stdout("This is goose, so I always HONK! (despite being the adaptee)");
  }
}
