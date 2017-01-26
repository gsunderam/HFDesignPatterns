package composite;

import java.util.Iterator;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 14, 2011
 */
public abstract class MenuComponent {

  public void add(MenuComponent component) {
    throw new UnsupportedOperationException("Can't add components");
  }

  public void remove() {
    throw new UnsupportedOperationException("Can't add components");
  }

  public MenuComponent get(int i) {
    throw new UnsupportedOperationException("Can't add components");
  }

  public String getName() {
    throw new UnsupportedOperationException("Can't add components");
  }

  public String getDescription() {
    throw new UnsupportedOperationException("Can't add components");
  }

  public boolean isVegetarian() {
    throw new UnsupportedOperationException("Can't add components");
  }

  public double getPrice() {
    throw new UnsupportedOperationException("Can't add components");
  }

  public void print() {
    stdout("Print will be deffered to the sub classes!");
  }

  public abstract Iterator<MenuComponent> createIterator();

}
