package iterator;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 12, 2011
 */
public class Waitress {
  private Menu pancakeHouseMenu;
  private Menu dinerMenu;

  public Waitress(Menu pancakeHouseMenu, Menu dinerMenu) {
    this.pancakeHouseMenu = pancakeHouseMenu;
    this.dinerMenu = dinerMenu;
  }

  public void printMenu() {
     Iterator<MenuItem> dinerIt = dinerMenu.createIterator();
     Iterator<MenuItem> pancakeIt = pancakeHouseMenu.createIterator();
     stdout("----------------Lunch Menu------------------");
     printMenu(dinerIt);
     stdout("------------Breakfast Menu------------------");
    // printMenu(pancakeIt);
    removeDinerMenuItem(dinerIt);
  }

  private void printMenu(Iterator<MenuItem> it) {
    int i = 0;
    while (it.hasNext()) {
      MenuItem item = it.next();
      stdout("----Recipe---- " + ++i);
      stdout("NAME: " + item.name);
      stdout("DESCRIPTION: " + item.desc);
      stdout("VEGETARIAN ? " + (item.isVeg ? "Yes" : "No"));
      stdout("PRICE: " + item.cost);
    }
  }

  public void removeDinerMenuItem(Iterator<MenuItem> it) {
    it.remove();
    printMenu(it);
  }
}
