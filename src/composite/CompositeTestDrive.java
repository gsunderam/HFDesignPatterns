package composite;

/**
 * User: gsunderam
 * Date: Jun 14, 2011
 *
 * Test to execercise the Composite pattern
 */
public class CompositeTestDrive {
  public static void main(String[] args) {
    MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "BreakFast");
    MenuComponent dinerMenu = new Menu("DINER MENU", "Lunch");
    MenuComponent cafeMenu = new Menu("CAFE MENU", "Dinner");

    addMenuItem(cafeMenu, "Veggie Burger", "Burgur with cabbage and cheese", true, 5.23);
    //Add a tree node to the cafe menu, add a leaf to the dessert menu
    MenuComponent cafeDessertMenu = new Menu("DESSERT MENU", "Dessert obviously!");
    addMenuItem(cafeDessertMenu, "Ice Cream", "Strawberry ice cream", true, 4.99);
    cafeMenu.add(cafeDessertMenu); //This is the major change for which we needed composite pattern

    addMenuItem(pancakeHouseMenu, "K & B's Pancake breakfast", "Pancakes with scrambled eggs and toast", true, 2.99);
    addMenuItem(pancakeHouseMenu, "Regular pancake breakfast", "Pancakes with Fried eggs sausage", false, 2.99);
    addMenuItem(pancakeHouseMenu, "Blueberry pancakes", "With fresh blueberries", true, 3.49);
    addMenuItem(pancakeHouseMenu, "Waffles", "Waffles with your choice of fresh blueberries or strawberries", true, 3.59);

    addMenuItem(dinerMenu, "Vegetarian BLT", "(Fakin')Bacon with lettuce and tomato on whole wheat", true, 2.99);
    addMenuItem(dinerMenu, "BLT", "Bacon with lettuce and tomato", false, 2.99);
    addMenuItem(dinerMenu, "Soup of the day", "Soup with potato salad", false, 3.29);
    addMenuItem(dinerMenu, "Hot dog", "Hot dog with saurkraut, relist, onions, topped with cheese", false, 3.05);

    MenuComponent allMenus = new Menu("All Menus", "All Menu's in three different categories");
    allMenus.add(pancakeHouseMenu);
    allMenus.add(dinerMenu);
    allMenus.add(cafeMenu);

    NewWaitress waitress = new NewWaitress(allMenus);
    //waitress.print();
    waitress.printVegetarian();
  }

  private static void addMenuItem(MenuComponent menu, String name, String description, boolean isVeg, double cost) {
    MenuComponent item = new NewMenuItem(name, description, isVeg, cost);
    menu.add(item);
  }


}
