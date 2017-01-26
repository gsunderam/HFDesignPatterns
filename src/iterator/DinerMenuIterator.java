package iterator;

import java.util.Arrays;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 12, 2011
 * This is the concrete iterator implementation
 */
public class DinerMenuIterator<T> implements Iterator {
  T [] menuitems;
  int position = 0;

  public DinerMenuIterator(T [] menuitems) {
    this.menuitems = menuitems;
  }

  public boolean hasNext() {
    if (position >= menuitems.length || menuitems[position] == null)
      return false;
    else
      return true;
  }

  public T next() {
    T item = menuitems[position];
    position += 1;
    stdout("Current position: " + position);
    return item;
  }
                        
  /**
   * Need to provide our own implementation as java iterator has a remove
   * This removes then LAST element
   */
  public void remove() {
     if (position <= 0) {
       throw new IllegalStateException("Invalid position in the array " + position);
     }

    if (menuitems[position - 1] != null) {
      for (int i = position-1;i < menuitems.length-1;i++) {
       menuitems[i] = menuitems[i+1]; //Copy over items to ONE position ahead
      }
      menuitems[menuitems.length - 1] = null; //Remove the last element as in stack.pop()
      resetPosition();
    }
  }

   private void resetPosition() {
     position = 0;
   }
}
