package iterator;

/**
 * User: gsunderam
 * Date: Jun 12, 2011
 */
public class MenuItem {

  String name;
  String desc;
  boolean isVeg;
  double cost;

  public MenuItem(String name, String desc, boolean veg, double cost) {
    this.name = name;
    this.desc = desc;
    isVeg = veg;
    this.cost = cost;
  }
}
