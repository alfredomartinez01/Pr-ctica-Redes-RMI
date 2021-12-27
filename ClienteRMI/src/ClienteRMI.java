import static colors.colors.ANSI_BLUE;
import static colors.colors.ANSI_GREEN;
import static colors.colors.ANSI_RED;
import static colors.colors.ANSI_RESET;
import static colors.colors.ANSI_YELLOW;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;


public class ClienteRMI extends Thread{
    private final GetSetBD db;
    softwareDownload frameSoftware;

    public ClienteRMI(GetSetBD db, softwareDownload frameSoftware) {
        this.frameSoftware = frameSoftware;
        this.db = db;
        System.out.print( ANSI_BLUE + "[ Creado ] "+ANSI_RESET+" Cliente RMI Creado. ");
    }
    
    public void run(){
        System.out.print( ANSI_GREEN + "[ Iniciado ] "+ANSI_RESET+" Cliente RMI Iniciado");
    }

    public void searchFile(String text) {
        try {
            //En teoria se debe de buscar el file en todos los servidores registrados
            
            List<serverData> ServersList = db.getServidores();
            if(ServersList.size() != 0){
                for(int i=0 ; i < ServersList.size() ; i++){
                    System.out.println( ANSI_YELLOW + "[ Estado ] "+ANSI_RESET+" Buscando: "+text+" en servidor: "+ServersList.get(i).getAddress());
                    Registry registry = LocateRegistry.getRegistry(ServersList.get(i).getAddress(),1099);
                    Busqueda stub = (Busqueda) registry.lookup("Busqueda");
                    GetSetArchivos response = stub.buscar(text);
                    if(!response.getNombre().equals("unknown")){
                        System.out.println( ANSI_GREEN + "[ Respuesta ] "+ANSI_RESET+" Busqueda name: "+response.getNombre());
                        System.out.println( ANSI_GREEN + "[ Rspuesta ] "+ANSI_RESET+" Busqueda path: "+response.getRuta());
                        System.out.println( ANSI_GREEN + "[ Respuesta ] "+ANSI_RESET+" Busqueda md5: "+response.getMd5());
                        //Lo guardamos en la bd
                        db.setArchivoEncontrado(response);
                        db.setArchivoEncontradoServ(ServersList.get(i).getAddress());
                        //Activamos opcion de descargar
                        frameSoftware.changeResultLabel(true, "Archivo encontrado");
                        frameSoftware.changeDownload(true);
                       
                    }else{
                        System.out.println( ANSI_RED + "[ Error ] "+ANSI_RESET+" Archivo no encontrado en servidor");
                        frameSoftware.changeResultLabel(false, "No existe el archivo");
                        frameSoftware.changeDownload(false);
                    }
                }
            }

	} catch (Exception e) {
	    System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}
