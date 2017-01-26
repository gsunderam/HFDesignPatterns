package proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jul 6, 2011
 */
public class GumballMonitor {

  GumballMachineRemote machine;

  public GumballMonitor(GumballMachineRemote machine) {
    this.machine = machine;
  }

  /**
   * @throws MalformedURLException
   * @throws NotBoundException
   * @throws RemoteException
   *
   * These calls get DELEGATED to the stub that in turn calls the actual object "remotely"
   */
  public void report() throws MalformedURLException, NotBoundException, RemoteException {
    stdout("INVENTORY in the gumball machine: " + machine.getCount());
    stdout("\n");
    stdout("STATE of the machine: " + machine.getState());
  }
}
