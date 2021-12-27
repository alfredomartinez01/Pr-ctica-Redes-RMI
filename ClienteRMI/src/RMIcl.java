import static colors.colors.ANSI_BLUE;
import static colors.colors.ANSI_GREEN;
import static colors.colors.ANSI_RED;
import static colors.colors.ANSI_RESET;
import static colors.colors.ANSI_YELLOW;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;


public class RMIcl extends Thread{
    private final GetSetBD BD;
    softwareDownload frameSoftware;

    public RMIcl(GetSetBD BD, softwareDownload frameSoftware) {
        this.frameSoftware = frameSoftware;
        this.BD = BD;
        System.out.print( ANSI_BLUE + " Creado. Tipo: Cliente RMI ");
    }
    
    public void run(){
        System.out.print( ANSI_GREEN + " Iniciado: Tipo: Cliente RMI");
    }
    
    /* Búsqueda del archivo en los servidores almacenados*/
    public void buscaArchivo(String text) {
        try {            
            List<InfoServidor> servidores = BD.getServidores();
            if(servidores.size() != 0){
                for(int i=0 ; i < servidores.size() ; i++){
                    System.out.println( ANSI_YELLOW + " Buscando "+text+" en servidor: "+servidores.get(i).getDireccion());
                    Registry registry = LocateRegistry.getRegistry(servidores.get(i).getDireccion(),1099);
                    Busqueda stub = (Busqueda) registry.lookup("Busqueda");
                    GetSetArchivos response = stub.buscar(text);
                    
                    // Almacenamiento del archivo
                    if(!response.getNombre().equals("unknown")){
                        System.out.println( ANSI_GREEN + "R.- "+ANSI_RESET+" Búsqueda: "+response.getMd5());
                        System.out.println( ANSI_GREEN + "R.- "+ANSI_RESET+" Nombre: "+response.getNombre());
                        System.out.println( ANSI_GREEN + "R.- "+ANSI_RESET+" Ruta: "+response.getRuta());
                        BD.setArchivoEncontrado(response);
                        BD.setArchivoEncontradoServ(servidores.get(i).getDireccion());
                        
                        // Opcion de descarga
                        frameSoftware.changeResultLabel(true, "Archivo encontrado");
                        frameSoftware.changeDownload(true);

                    }else{
                        System.out.println( ANSI_RED + " Error: "+ANSI_RESET+" Archivo no encontrado ");
                        frameSoftware.changeResultLabel(false, " Error: Búsqueda del archivo ");
                        frameSoftware.changeDownload(false);
                    }
                }
            }
	} catch (Exception e) {
	    System.err.println("Error: Búsqueda del archivo " + e.toString());
	    e.printStackTrace();
	}
    }
}
