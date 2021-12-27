

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Busqueda extends Remote{
       searchResult buscar(String file) throws RemoteException;
}
