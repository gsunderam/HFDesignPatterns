package composite;

import java.util.Iterator;
import java.util.Stack;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 16, 2011
 *
 * Class to test the composite iterator alone
 */
public class TestCompositeIterator {
  
  public void testCompositeIterator(MenuComponent component) {
    Iterator<MenuComponent> compositeIterator = component.createIterator();

    while (compositeIterator.hasNext()) {
      MenuComponent comp = compositeIterator.next();
      stdout("The name of the component is " + comp.getName());
    }
  }

  public static void main(String[] args) {
    TestCompositeIterator test = new TestCompositeIterator();
    
    MenuComponent cafeMenu = new Menu("CAFE MENU", "Dinner");
    addMenuItem(cafeMenu, "Veggie Burger", "Burgur with cabbage and cheese", true, 5.23);
    addMenuItem(cafeMenu, "Veggie Cheese burger", "Burgur with meat", false, 5.99);
    //Add a tree node to the cafe menu, add a leaf to the dessert menu
    MenuComponent cafeDessertMenu = new Menu("DESSERT MENU", "Dessert obviously!");
    addMenuItem(cafeDessertMenu, "Ice Cream", "Strawberry ice cream", true, 4.99);
    addMenuItem(cafeDessertMenu, "Vanilla Ice Cream", "Vanilla", true, 2.99);
    addMenuItem(cafeDessertMenu, "Gulob jam", "Sweety indian stuff", true, 6.99);
    cafeMenu.add(cafeDessertMenu);

    test.testCompositeIterator(cafeMenu);
  }

  private static void addMenuItem(MenuComponent menu, String name, String description, boolean isVeg, double cost) {
    MenuComponent item = new NewMenuItem(name, description, isVeg, cost);
    menu.add(item);
  }
}
