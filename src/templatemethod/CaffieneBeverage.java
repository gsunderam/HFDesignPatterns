package templatemethod;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 6, 2011
 *
 * Template method pattern. Prepare is our template method. It defines an algorithm: brew, boild etc in
 * this case. This structure can't be changed and hence it's TEMPLATE method pattern
 */
public abstract class CaffieneBeverage {

  protected String name = "Beverage";
  
  /**
   * Subclasses must not override this and hence finaL
   */
  final void prepare() {
    brew(); //subclass specific
    boilWater(); //same
    pourInCup(); //same
    if (customerWantsCondiments()) {  //Hook methods aid in making a conditional determination as to
                                      //perform a step or not.
      addCondiments();  //subclass specific
    }
    brewComplete();
  }

  private void brewComplete() {
     stdout("---------------Completed brewing " + name + "-----------------------");
  }

  private void pourInCup() {
    stdout("Pouring " + name + " in cup");
  }

  protected abstract void addCondiments();

  /**
   * This is called as "HOOK" method, meaning sub classes can override this if necessary. Optional
   * See any sub class for reference
   * @return
   */
  public boolean customerWantsCondiments() {
    return false;
  }

  protected void boilWater() {
    stdout("Boiling water...");
    sleep();
    stdout("Boiled water");
  }

  private void sleep() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  protected abstract void brew();
}
