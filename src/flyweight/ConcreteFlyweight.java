package flyweight;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 23, 2011
 */
public class ConcreteFlyweight implements FlyWeight {
  private boolean state;

  public ConcreteFlyweight(State state) {
    this.state = state.isState();
  }

  @Override
	public void action() {
    stdout("Final state of the flyweight: " + state);
		if (state) stdout("True action logic");
		else stdout("False action logic");
  }
}
