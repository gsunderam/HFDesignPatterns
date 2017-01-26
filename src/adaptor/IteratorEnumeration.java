package adaptor;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * User: gsunderam
 * Date: Jun 1, 2011
 * Adaptor class to adapt an ITERATOR to an ENUMERATION. When clients expect an enumeration
 * use this adaptor
 */
public class IteratorEnumeration<E> implements Enumeration {
  Iterator<E> it; //This is composition or HAS-A. ADAPTEE

  public IteratorEnumeration(Iterator<E> it) {
    this.it = it;
  }

  /**
   * delegate the hasMoreElements call to the ADAPTEE's method.
   * This is the crux of the pattern
   * @return
   */
  public boolean hasMoreElements() {
     return it.hasNext();
  }

  /**
   * delegate to the ADAPTEE's next() method
   * @return
   */
  public E nextElement() {
      return it.next();
  }
}
