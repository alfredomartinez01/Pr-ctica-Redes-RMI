import static colors.colors.ANSI_BLUE;
import static colors.colors.ANSI_GREEN;
import static colors.colors.ANSI_RED;

import java.util.List;

public class MulticastVerifServidor extends Thread{

    private final GetSetBD BD;
    
    /* Constructor */
    public MulticastVerifServidor(GetSetBD BD){
        this.BD = BD;
        System.out.print( ANSI_BLUE + "Creado. Tipo: Multicast. Funcion: Verfica servidor ");
    }
    
    public void run(){
        System.out.print( ANSI_GREEN + " Iniciado. Tipo: Multicast. Funcion: Verfica servidor ");
        while(true){
        System.out.print( ANSI_GREEN + " Iniciado. Tipo: Multicast. Funcion: Verfica servidor ");
            try{
                List<InfoServidor> servidores = BD.getServidores();
                if(servidores.size() != 0){
                    for(int i=0 ; i < servidores.size() ; i++){
                        // Verifica servidores activos para mantener o eliminar
                        if(servidores.get(i).getTemp() == 0){
                            servidores.remove(i);
                            System.out.println( ANSI_RED + " Eliminado. Tipo Multicast. Función: Elimina servidor inactivo ");
                        }else{
                            servidores.get(i).setTemp(servidores.get(i).getTemp()-1);
                        }   
                    }
                }
                Thread.sleep(1000);
            } 
            catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
