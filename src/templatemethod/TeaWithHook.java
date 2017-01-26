package templatemethod;

import util.ReadUserInput;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 7, 2011
 */
public class TeaWithHook extends CaffieneBeverage {

  public TeaWithHook() {
    this.name = "Tea";
  }

  @Override
  protected void addCondiments() {
    stdout("Adding Condiments lemon");
  }

  @Override
  protected void brew() {
     stdout("----------------Brewing " + name + "-------------------");
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
