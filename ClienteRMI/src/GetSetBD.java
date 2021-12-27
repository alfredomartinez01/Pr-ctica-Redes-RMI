import java.util.ArrayList;
import java.util.List;

public class GetSetBD {
   
    private List<InfoServidor> servidores = new ArrayList<>();
    private GetSetArchivos archEncontrado = new GetSetArchivos(); 
    private String archEncontradoServ = new String();

    /* Getters */
    public String getArchivoEncontradoServ() {
        return archEncontradoServ;
    }

    public GetSetArchivos getArchivoEncontrado() {
        return archEncontrado;
    }

    public List<InfoServidor> getServidores() {
        return servidores;
    }
    
    /*Setters */
    public void setArchivoEncontradoServ(String archEncontradoServ) {
        this.archEncontradoServ = archEncontradoServ;
    }
    
    public void setArchivoEncontrado(GetSetArchivos archEncontrado) {
        this.archEncontrado = archEncontrado;
    }
    
    public void setServidores(List<InfoServidor> servidores) {
        this.servidores = servidores;
    }
    
    //Add
    public void addServidor(InfoServidor e) {
        servidores.add(e);
    }
}
