package proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 7, 2011
 */
public class GumballRmiClient {
  public static void main(String[] args) {
    try {
      //Actually machine is a reference to a stub. 1099 is default. So no need to specify
      GumballMachineRemote machine = (GumballMachineRemote) Naming.lookup("rmi://localhost:1099/GumballMachine");
      GumballMonitor monitor = new GumballMonitor(machine);
      stdout("----------Printing report-------------------------------\n");
      monitor.report();
      stdout("------------Successful with RMI one more time----------------------");

    } catch (NotBoundException e) {
      e.printStackTrace();   
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
}
