package adaptor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 1, 2011
 * Test class to demonstrate the concept of Adaptor Pattern using Iterator and enumeration
 * Ordinarily lists don't support enumeration. Our adaptor ADAPTS the ArrayList's iterator
 * to a client expecting enumeration
 */
public class AdaptorTest {
  public static void main(String[] args) {
    List<String> l = new ArrayList<String>(); //Vendor class
    l.add("GS");
    l.add("will always");
    l.add("Succeed");
    
    Enumeration<String> e = new IteratorEnumeration<String>(l.iterator()); //Adaptee is iterator

    /**
     * Enumeration is the Adapted
     */
    while (e.hasMoreElements()) {
      stdout(e.nextElement());
    }
  }
}
