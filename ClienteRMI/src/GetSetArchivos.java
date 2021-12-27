import java.io.Serializable;

public class GetSetArchivos implements Serializable{
    String filename;
    String path;
    String md5;

    /* Getter */
    public String getNombre() {
        return filename;
    }

    public String getRuta() {
        return path;
    }
    
    public String getMd5() {
        return md5;
    }
    
    /*Setter */
    public void setNombre(String filename) {
        this.filename = filename;
    }

    public void setRuta(String path) {
        this.path = path;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
