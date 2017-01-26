package templatemethod;

import util.ReadUserInput;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 6, 2011
 */
public class CoffeeWithHook extends CaffieneBeverage {

  public CoffeeWithHook() {
    this.name = "Coffee";
  }
  @Override
  protected void addCondiments() {
    stdout("Adding sugar and milk for " + name);  
  }

  @Override
  protected void brew() {
   stdout("-----------------Brewing " + name + "-----------------------");
  }

  public boolean customerWantsCondiments() {
    if (getUserInput().toLowerCase().equals("y")) {
      return true;
    } else {
      return false;
    }
  }

  private String getUserInput() {
    return ReadUserInput.getUserInput();
  }
}
