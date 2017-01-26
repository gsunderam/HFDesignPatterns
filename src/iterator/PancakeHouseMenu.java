package iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gsunderam
 * Date: Jun 12, 2011
 * This is the concrete aggregate object
 */
public class PancakeHouseMenu implements Menu {
  List<MenuItem> menuitems;

  public PancakeHouseMenu() {
    menuitems = new ArrayList<MenuItem>();
    
    addItem("K & B's Pancake breakfast", "Pancakes with scrambled eggs and toast", true, 2.99);
    addItem("Regular pancake breakfast", "Pancakes with Fried eggs sausage", false, 2.99);
    addItem("Blueberry pancakes", "With fresh blueberries", true, 3.49);
    addItem("Waffles", "Waffles with your choice of fresh blueberries or strawberries", true, 3.59);
  }

  private void addItem(String name, String desc, boolean isVeg, double cost) {
    MenuItem item = new MenuItem(name, desc, isVeg, cost);
    menuitems.add(item);
  }

  public Iterator createIterator() {
    return new PancakeHouseMenuIterator<MenuItem>(menuitems);
  }
}
