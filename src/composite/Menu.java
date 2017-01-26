package composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 14, 2011
 */
public class Menu extends MenuComponent {
  List<MenuComponent> components = new ArrayList<MenuComponent>();
  String name;
  String description;
  Iterator<MenuComponent> componentsIterator;

  public Menu(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public void add(MenuComponent menuComponent) {
    components.add(menuComponent);
  }

  public MenuComponent get(int i) {
    return components.get(i);
  }

  public void remove(MenuComponent menuComponent) {
    components.remove(menuComponent);
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  /**
   * This is a different implementation
   */
  public void print() {
    stdout(name + ", " + description);
    stdout("-----------------------------------");
    for (MenuComponent menuComponent : components) {
      menuComponent.print();
    }
    stdout("\n");
  }

  @Override
  public Iterator<MenuComponent> createIterator() {
    stdout("No of components: " + components.size());
    if (componentsIterator == null) {
      this.componentsIterator = new CompositeIterator(components.iterator());
    }

    return componentsIterator;
  }

}
