import java.io.Serializable;

public class GetSetArchivos implements Serializable{
    String nomArchivo;
    String ruta;
    String md5;

    /* Getter */
    public String getNombre() {
        return nomArchivo;
    }

    public String getRuta() {
        return ruta;
    }
    
    public String getMd5() {
        return md5;
    }
    
    /*Setter */
    public void setNombre(String nomArchivo) {
        this.nomArchivo = nomArchivo;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
