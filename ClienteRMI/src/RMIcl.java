import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;


public class RMIcl extends Thread{
    private final GetSetBD BD;
    softwareDownload frameSoftware;

    public RMIcl(GetSetBD BD, softwareDownload frameSoftware) {
        this.frameSoftware = frameSoftware;
        this.BD = BD;
        System.out.print(" Creado. Tipo: Cliente RMI \n");
    }
    
    public void run(){
        System.out.print(" Iniciado: Tipo: Cliente RMI\n");
    }
    
    /* Búsqueda del archivo en los servidores almacenados*/
    public void buscaArchivo(String text) {
        try {            
            List<searchResult> servidores = BD.getServidores();
            if(servidores.size() != 0){
                for(int i=0 ; i < servidores.size() ; i++){
                    System.out.println( " Buscando "+text+" en servidor: "+servidores.get(i).getDireccion());
                    Registry registry = LocateRegistry.getRegistry(servidores.get(i).getDireccion(),1099);
                    Busqueda stub = (Busqueda) registry.lookup("Busqueda");
                    GetSetArchivos response = stub.buscar(text);
                    
                    // Almacenamiento del archivo
                    if(!response.getNombre().equals("unknown")){
                        System.out.println("R.- Búsqueda: "+response.getMd5());
                        System.out.println("R.- Nombre: "+response.getNombre());
                        System.out.println("R.- Ruta: "+response.getRuta());
                        BD.setArchivoEncontrado(response);
                        BD.setArchivoEncontradoServ(servidores.get(i).getDireccion());
                        
                        // Opcion de descarga
                        frameSoftware.changeResultLabel(true, "Archivo encontrado");
                        frameSoftware.changeDownload(true);

                    }else{
                        System.out.println(" Error: Archivo no encontrado \n ");
                        frameSoftware.changeResultLabel(false, " Error: Búsqueda del archivo \n");
                        frameSoftware.changeDownload(false);
                    }
                }
            }
	} catch (Exception e) {
	    System.err.println("Error: Búsqueda del archivo \n" + e.toString());
	    e.printStackTrace();
	}
    }
}
