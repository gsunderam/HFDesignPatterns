package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static log.Logger.stderr;
import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 9, 2011
 *
 * Test class to demonstrate dynamic proxy pattern. I like this way proxying using java's reflect!
 */
public class MatchMakingDrive {
  
  public static void main(String[] args) {
    PersonBean joe = new PersonBeanImpl();
    PersonBean ownerProxy = getProxy(joe, new OwnerInvocationHandler(joe));

    //These calls should be allowed by the proxy and the handlers
    ownerProxy.setName("Joe Smith");
    ownerProxy.setGender("Male");
    ownerProxy.setInterests("Sports and studies");

    //This should not be allowed.
    try {
      ownerProxy.setHotOrNot(32);
    } catch(Exception e) {
      stdout("Exception: " + e.getMessage() + " | Cause: " + e.getCause());
      stderr("Illegal Access attempt by " + ownerProxy.getName() + " to set his own rating");
      stdout("First owner use case Succeeded!");
    }
    //print info
    stdout("---------------------Printing owner details------------------------------");
    stdout("Owner name: " + ownerProxy.getName() + " | Gender: " + ownerProxy.getGender() + " | Interests: " +
            ownerProxy.getInterests());
    stdout("Rating: " + ownerProxy.getHotOrNotRating()); //This MUST be ZERO
    stdout("-------------------------End---------------------------------------------");
    PersonBean nonOwnerProxy = getProxy(joe, new NonOwnerInvocationHandler(joe));
    //This must be allowed.
    nonOwnerProxy.setHotOrNot(23);
    nonOwnerProxy.setHotOrNot(33);
    //This must not be allowed.
    try {
      nonOwnerProxy.setInterests("Golf and library");
    } catch(Exception e) {
      stdout("Exception message: " + e.getMessage() + " | Cause: " + e.getCause());
      stderr("Illegal attempt to set someone else's interests by " + nonOwnerProxy.getName());
      stdout("Non owner use case also succeeded!");
    }
    //print rating
    stdout("--------------Print owner rating and interests and make sure they are OK--------------");
    stdout("Rating for " + nonOwnerProxy.getName() + " is " + nonOwnerProxy.getHotOrNotRating());
    stdout("Interests: " + nonOwnerProxy.getInterests());    //This MUST be sports and studies
    stdout("------------------------Success-------------------------------------------------------");

    stdout("One more tidbit. Let's check whether a class is a proxy or a regular class..."); //Check whether a class is a proxy or not
    //isProxyClass is a utility method in the Proxy Class
    stdout("IS Object JOE a proxy ? " + Proxy.isProxyClass(joe.getClass()));
    stdout("IS Object ownerProxy a proxy ? " + Proxy.isProxyClass(ownerProxy.getClass()));
    stdout("That's ALL about dynamic proxies using Java lang's reflect. Have a Good Day");
  }

  /**
   * @param t
   * @param handler
   * @return PersonBean
   *
   * Proxy creator for BOTH owner and Non owner using the reflect package.
   * Get a proxy for a generic type
   */
  private static <T> T getProxy(T t, InvocationHandler handler) {
    return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(),
                        t.getClass().getInterfaces(), handler);
  }
}
