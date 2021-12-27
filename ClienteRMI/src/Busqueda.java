

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Busqueda extends Remote{
       GetSetArchivos buscar(String file) throws RemoteException;
}
