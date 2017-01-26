package proxy;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 6, 2011
 */
public class GumballRmiServer {

  public static void main(String[] args) {
    try {
      //do some operations on the machine
      GumballMachineImpl gumballMachine = new GumballMachineImpl(5);
      gumballMachine.insertQuarter();
      gumballMachine.turnCrank();

      gumballMachine.insertQuarter();
      gumballMachine.turnCrank();
      
      BindGumballMachine(gumballMachine);
    } catch (RemoteException e) {
      e.printStackTrace();
      System.exit(1);
    }
    stdout("Rmi Server start up successful");

  }

  private static void BindGumballMachine(GumballMachineRemote machine) throws RemoteException {
    //Naming.rebind("gumballMachine", machine);
    //Favoured this over the "rmiregistry" because of classpath issues with rmi registry
    Registry r = LocateRegistry.createRegistry(1099);
    r.rebind("GumballMachine", machine);   //Actually the RMI System binds the STUB to the String

    stdout("Bound object to the String: GumballMachine");
  }
}
