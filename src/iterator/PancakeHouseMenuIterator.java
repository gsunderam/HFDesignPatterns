package iterator;

import java.util.List;

/**
 * User: gsunderam
 * Date: Jun 12, 2011
 */
public class PancakeHouseMenuIterator<T> implements Iterator {
  List<T> menuitems;
  int position = 0;

  public PancakeHouseMenuIterator(List<T> menuitems) {
    this.menuitems = menuitems;
  }

  public boolean hasNext() {
    if (position >= menuitems.size()) return false; 
    else return true;
  }

  public T next() {
    T item = menuitems.get(position);
    position += 1;
    return item;
  }

  public void remove() {
    
  }
}
