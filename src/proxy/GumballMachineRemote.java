package proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * User: gsunderam
 * Date: Jul 6, 2011
 */
public interface GumballMachineRemote extends Remote {

  public State getState() throws RemoteException;
  public int getCount() throws RemoteException;
}
