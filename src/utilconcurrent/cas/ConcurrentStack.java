package utilconcurrent.cas;

import java.util.concurrent.atomic.AtomicReference;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Sep 1, 2013
 */
public class ConcurrentStack<E> {
  AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

  public void enumerate() {
    Node<E> node = top.get();
    
    if (node == null) {
        stdout("Stack has no elements");
        return;
    }

    do {
      stdout("Item: " + node.item);
      node = node.next;
    } while (node != null);
  }

  public void push(E item, String threadName) {
    Node<E> newhead = new Node<E>(item);
    Node<E> oldhead;

    do {
      oldhead = top.get();
      if (oldhead != null) stdout(threadName + ": Top is " + oldhead.item);
      newhead.next = oldhead;
    } while(!top.compareAndSet(oldhead, newhead));
  }

  public E pop(String threadName) {
    Node<E> newhead;
    Node<E> oldhead;
    int count = 0;

    do {
      if (count > 0)
        stdout(threadName + ": CAS returned false. Contention encountered. Retrying...attempt: " + count);
      oldhead = top.get();
      if (oldhead == null) return null;

      newhead = oldhead.next;
      count++;
    } while(!top.compareAndSet(oldhead, newhead));

    stdout("Ok. CAS true. popped");

    return oldhead.item;
  }

  /**
   * Always make inner classes such as these static unless the instance fields of the inner class
   * are dependent on the "per instance" level of outer classes
   *
   * @param <E>
   */
  private static class Node<E> {
    public final E item;
    public Node<E> next;

    public Node(E item) {
      this.item = item;
    }
  }
}
