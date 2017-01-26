package visitor;

/**
 * User: gsunderam
 * Date: Jan 16, 2015
 */
public interface Visitable {
	public void accept(Visitor visitor);
}
