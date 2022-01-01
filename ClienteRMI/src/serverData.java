


import java.io.Serializable;


public class serverData implements Serializable{
    String direccion;
    int puerto;
    int temp;
    
    public serverData(String address, int port, int temp) {
        this.direccion = address;
        this.puerto = port;
        this.temp = temp;
    }
    
    public serverData() {}

    public String getDireccion() {
        return direccion;
    }

    public void setAddress(String address) {
        this.direccion = address;
    }

    public int getPort() {
        return puerto;
    }

    public void setPort(int port) {
        this.puerto = port;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    
}
