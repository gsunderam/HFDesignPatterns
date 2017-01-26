package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * User: gsunderam
 * Date: Jul 9, 2011
 *
 * Custom handler to route calls in a "protected" way to the owner object
 * This is like Interceptors in spring framework. I think jboss uses Invocation proxy binding
 * using Java's proxying facilities
 */
public class OwnerInvocationHandler implements InvocationHandler {
  private PersonBean person;

  public OwnerInvocationHandler(PersonBean person) {
    this.person = person;
  }

  /**
   * @param proxy
   * @param method
   * @param args
   * @return
   * @throws IllegalAccessException
   *
   * Protects the access to the ACTUAL person object, which is our SUBJECT, based on the role of the caller viz.
   * owner and NonOwner
   */
  public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
    String methodName = method.getName();
    
    try {
      if (methodName.startsWith("get")) return method.invoke(person, args);
      else if (methodName.startsWith("setHot")) throw new IllegalAccessException("You are not allowed to do this operation");
      else if (methodName.startsWith("set")) return method.invoke(person, args);
    } catch(InvocationTargetException e) {
      e.printStackTrace();
    }
    
    return null;
  }
}
