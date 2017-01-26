package composite;

import composite.MenuComponent;

import java.util.Iterator;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 14, 2011
 */
public class NewMenuItem extends MenuComponent {

  String name;
  String desc;
  boolean isVeg;
  double cost;

  public NewMenuItem(String name, String desc, boolean veg, double cost) {
    this.name = name;
    this.desc = desc;
    isVeg = veg;
    this.cost = cost;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public boolean isVegetarian() {
    return isVeg;
  }

  public void setisVegetarian(boolean veg) {
    isVeg = veg;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  /**
   * This method is as usual as the MenuItem in iterator pattern
   */
  public void print() {
    stdout("Name: " + name + " Description: " + desc + " Vegetarian " + isVeg);
    stdout("Price: " + cost);
  }

  @Override
  public Iterator<MenuComponent> createIterator() {
    throw new UnsupportedOperationException("Iteration doesn't make sense");
  }
}
