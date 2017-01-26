package iterator;

import static log.Logger.stderr;

/**
 * User: gsunderam
 * Date: Jun 12, 2011
 * This is the concrete aggregate object
 */
public class DinerMenu implements Menu {
  MenuItem [] menuitems;
  int numberOfItems = 0;

  public DinerMenu() {
    menuitems = new MenuItem[6];

    addItem("Vegetarian BLT", "(Fakin')Bacon with lettuce and tomato on whole wheat", true, 2.99);
    addItem("BLT", "Bacon with lettuce and tomato", false, 2.99);
    addItem("Soup of the day", "Soup with potato salad", false, 3.29);
    addItem("Hot dog", "Hot dog with saurkraut, relist, onions, topped with cheese", false, 3.05);
  }

  private void addItem(String name, String desc, boolean isVeg, double cost) {
    MenuItem item = new MenuItem(name, desc, isVeg, cost);

    if (numberOfItems > 6) {
      stderr("Sorry can't add more than six items");
    } else {
      menuitems[numberOfItems] = item;
      numberOfItems += 1;
    }
  }

  /**
   * This is a factory method! Hence, this pattern resenbles Factory Method pattern
   * 
   * @return Iterator interface reference
   */
  public Iterator createIterator() {
    return new DinerMenuIterator<MenuItem>(menuitems);
  }
}
