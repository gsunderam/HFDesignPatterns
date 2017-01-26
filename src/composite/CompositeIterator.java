package composite;

import java.util.Iterator;
import java.util.Stack;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 16, 2011
 *
 * This is an INTERNAL ITERATOR. Iterator itself controls the position of elements using next and hasNext when required.
 * That's why we had to use stack to maintain the iterator elements in stack - popping off when required
 */
public class CompositeIterator implements Iterator {
	Stack<Iterator<MenuComponent>> stack = new Stack<Iterator<MenuComponent>>();
  
  public CompositeIterator(Iterator<MenuComponent> iterator) {
    stack.push(iterator);
  }

  /**
   * This gets called recursively for composites WITH IN the parent component
   * @return
   */
  public boolean hasNext() {
     if (stack.empty()) {
       stdout("Stack is Empty! We are Done. Success");
       return false;
     } else {
       Iterator<MenuComponent> it = stack.peek();
			 
       if (!it.hasNext()) {
				 /** Just clean the stack once done with a Menu. Nice way as GC can happen for this object */
         Iterator<MenuComponent> popIt = stack.pop();
         return hasNext();
       } else {
         return true;
       }
     }
  }

  /**
   * This gets called recursively for composites WITH IN the parent component
   * @return
   */
  public MenuComponent next() {
    if (hasNext()) {
      Iterator<MenuComponent> it = stack.peek();
      MenuComponent component = it.next();
			
      if (component instanceof Menu) { /** Both Menu and NewMenuItem can be found in the collection */
        Iterator<MenuComponent> temp = component.createIterator(); /** pushes an iterator of this component's children */
        stack.push(temp);  //push this composite ALSO in to the stack
				stdout("Size of the stack: " + size());
      }
			
      return component;
    } else {
      return null;
    }
  }

  public void remove() {
    throw new UnsupportedOperationException("Remove not supported");
  }

	public int size() {
		return stack.size();
	}
}
