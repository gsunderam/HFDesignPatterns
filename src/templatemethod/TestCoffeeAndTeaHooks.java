package templatemethod;

/**
 * User: gsunderam
 * Date: Jun 7, 2011
 */
public class TestCoffeeAndTeaHooks {
  public static void main(String[] args) {
    CaffieneBeverage coffee = new CoffeeWithHook();
    coffee.prepare();

    CaffieneBeverage tea = new TeaWithHook();
    tea.prepare();
  }
}
