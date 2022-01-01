import java.util.ArrayList;
import java.util.List;

public class GetSetBD {
   
    private List<serverData> servidores = new ArrayList<>();
    private searchResult archEncontrado = new searchResult(); 
    private String archEncontradoServ = new String();

    /* Getters */
    public String getArchivoEncontradoServ() {
        return archEncontradoServ;
    }

    public searchResult getArchivoEncontrado() {
        return archEncontrado;
    }

    public List<serverData> getServidores() {
        return servidores;
    }
    
    /*Setters */
    public void setArchivoEncontradoServ(String archEncontradoServ) {
        this.archEncontradoServ = archEncontradoServ;
    }
    
    public void setArchivoEncontrado(searchResult archEncontrado) {
        this.archEncontrado = archEncontrado;
    }
    
    public void setServidores(List<serverData> servidores) {
        this.servidores = servidores;
    }
    
    //Add
    public void addServidor(serverData e) {
        servidores.add(e);
    }
}
