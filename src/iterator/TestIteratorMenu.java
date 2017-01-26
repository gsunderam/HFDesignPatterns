package iterator;

/**
 * User: gsunderam
 * Date: Jun 12, 2011
 */
public class TestIteratorMenu {
  public static void main(String[] args) {
    DinerMenu diner = new DinerMenu();
    PancakeHouseMenu pancake = new PancakeHouseMenu();

    Waitress waitress = new Waitress(pancake, diner);
    waitress.printMenu();
    //waitress.removeDinerMenuItem();
  }
}
