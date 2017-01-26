package command;

import static log.Logger.stdout;

/**
 * Layer 3 - HERE IS THE ACTUAL IMPLEMENTATION FO BUSINESS LOGIC
 * These are vendor provided classes over which we have no control
 */
class Light {
  String location = "Hall";

  public void setLocation(String location) {
    this.location = location;
  }

  public void turnOn( ) {
    stdout("Light is on in the " + location);
  }
    
  public void turnOff( ) {
    stdout("Light is switched off in the " + location);
  }
}

