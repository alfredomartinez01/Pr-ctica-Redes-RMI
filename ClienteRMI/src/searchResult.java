import java.io.Serializable;

public class searchResult implements Serializable{
    String direccion;
    int puerto;
    int temp;
    
    /* Constructor */
    public searchResult(String direccion, int puerto, int temp) {
        this.direccion = direccion;
        this.puerto = puerto;
        this.temp = temp;
    }
    
    public searchResult() {}

    /* Getters */
    public String getDireccion() {
        return direccion;
    }

    public int getPuerto() {
        return puerto;
    }
    
    public int getTemp() {
        return temp;
    }
    
    /* Setters */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }    
}
