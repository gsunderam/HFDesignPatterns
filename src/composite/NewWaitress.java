package composite;

import java.util.Iterator;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 14, 2011
 */
public class NewWaitress {
  MenuComponent allMenus;

  public NewWaitress(MenuComponent allMenus) {
    this.allMenus = allMenus;
  }

  public void print() {
    allMenus.print();
  }

  public void printVegetarian() {
    Iterator<MenuComponent> allIt = allMenus.createIterator();
    stdout("----------------Here are the Vegetarian items----------------");
    while (allIt.hasNext()) {
      MenuComponent component = allIt.next();
			//stdout("Size of the STACK: " + ((CompositeIterator)allIt).size());
      try {
				if (component.isVegetarian()) {
					 component.print();
				}
      } catch(UnsupportedOperationException e) {
				  stdout(e);
				 /**
					* In real world this needs to be documented. Why are we catching this
					* and not dealing with it? In this case this is a business rule that
					* was agreed upon by the developers viz. a trade off to support the design
					* of the Menu class heirarchy
					**/
			}
    }
  }
}
