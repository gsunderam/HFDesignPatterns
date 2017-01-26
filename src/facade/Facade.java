package facade;

/**
 * User: gsunderam
 * Date: Jul 17, 2011
 *
 * This is facade. Its just a LAYER OVER A COMPLEX set of sub systems. An example is an order processing
 * workflow system over the web like Amazon
 */
public class Facade {

  /**
   * Common method to process the order from an external website or a web service.
   */
  public void processOrder() {
    //Call sub system 1 components. These can be interface references too
    SubSystem1 orderStorage = new SubSystem1("Order Storage");
    orderStorage.processQuantity();
    orderStorage.checkPrices();
    //Begin Sub system 2. Interface reference in real time
    SubSystem2 despatch = new SubSystem2("Despatch");
    if (despatch.checkComponents()) {
      despatch.sendJmsMessage();
    }
  }

}
