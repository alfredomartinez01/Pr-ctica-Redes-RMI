

import static colors.colors.ANSI_YELLOW;
import static colors.colors.ANSI_GREEN;
import static colors.colors.ANSI_RED;
import static colors.colors.ANSI_RESET;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RMI extends Thread implements Busqueda {
   
    File folder = new File("Archivos");
    
    public void run(){
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            System.out.println( ANSI_RED + "[ Error ] "+ANSI_RESET+" Error al inicializar Servidor RMI");
            e.printStackTrace();
        }
	try {
            RMI obj = new RMI();
	    Busqueda stub = (Busqueda) UnicastRemoteObject.exportObject(obj, 0);
	    Registry registry = LocateRegistry.getRegistry();
	    registry.bind("Busqueda", stub);   
            System.out.println( ANSI_GREEN + "[ Ok ] "+ANSI_RESET+" Servidor RMI Iniciado");
	} catch (Exception e) {
            System.out.println( ANSI_RED + "[ Error ] "+ANSI_RESET+"Server exception: " + e.toString());
	    e.printStackTrace();
	}
    }
    
    @Override
    public searchResult buscar(String file) throws RemoteException {
        System.out.println( ANSI_YELLOW + "[ Buscando... ] "+ANSI_RESET+" Buscando: "+file);
        searchResult resultado = buscarArchivo(folder, file);
        return resultado;
    }
    
    
    public searchResult buscarArchivo(File folder, String fileName) {
        searchResult resultado = new searchResult();
        resultado.filename = "unknown";
        
        File[] listOfFiles = this.folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                if(file.getName().toLowerCase().contains(fileName.toLowerCase())){
                    resultado.filename = file.getName();
                    resultado.path = file.getAbsolutePath();
                    resultado.md5 = getMD5(file.getAbsolutePath().toString());
                }
            }
        }
        return resultado;
    }
    
    public String getMD5(String path){
        try {
            MD5Checksum md5 = new MD5Checksum();
            String resultado = md5.getMD5Checksum(path);;
            return resultado;
        } catch (Exception ex) {
            Logger.getLogger(RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
