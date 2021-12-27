import java.util.ArrayList;
import java.util.List;

public class GetSetBD {
   
    private List<serverData> ServersList = new ArrayList<>();
    private GetSetArchivos fileFound = new GetSetArchivos(); 
    private String serverFileFound = new String();

    /* Getters */
    public String getArchivoEncontradoServ() {
        return serverFileFound;
    }

    public GetSetArchivos getArchivoEncontrado() {
        return fileFound;
    }

    public List<serverData> getServidores() {
        return ServersList;
    }
    
    /*Setters */
    public void setArchivoEncontradoServ(String serverFileFound) {
        this.serverFileFound = serverFileFound;
    }
    
    public void setArchivoEncontrado(GetSetArchivos fileFound) {
        this.fileFound = fileFound;
    }
    
    public void setServidores(List<serverData> ServersList) {
        this.ServersList = ServersList;
    }
    
    //Add
    public void addServidor(serverData e) {
        ServersList.add(e);
    }
}
