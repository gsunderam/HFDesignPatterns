package iterator;

/**
 * User: gsunderam
 * Date: Jun 12, 2011
 * This is the iterator interface
 */
public interface Iterator<T> {
  public boolean hasNext();
  public T next();
  public void remove();
}
