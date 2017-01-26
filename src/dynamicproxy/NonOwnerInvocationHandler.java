package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * User: gsunderam
 * Date: Jul 9, 2011
 *
 * This invocation handler handles cases for Non owners and controls access to the person
 */
public class NonOwnerInvocationHandler implements InvocationHandler {
  private PersonBean person;

  public NonOwnerInvocationHandler(PersonBean person) {
    this.person = person;
  }

  /**
   * Protects access to owners by disallowing non owners to invoke "setters" and allowing to invoke ONLY setHotOrNot()
   * method on the actual person object
   */
  public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
    String methodName = method.getName();

    try {
      if (methodName.startsWith("get")) return method.invoke(person, args);
      else if (methodName.startsWith("setHot")) return method.invoke(person, args);
      else if (methodName.startsWith("set"))
        throw new IllegalAccessException("You are not allowed to SET another person's private information");
    } catch(InvocationTargetException e) {
      e.printStackTrace();
    }
    
    return null;
  }
}
