import java.util.List;

public class MulticastVerifServidor extends Thread{
    private final GetSetBD BD;
    
    /* Constructor */
    public MulticastVerifServidor(GetSetBD BD){
        this.BD = BD;
        System.out.print("Creado -> Tipo: Multicast. Funcion: Verfica servidor. √ \n");
    }
    
    public void run(){
        System.out.print(" Iniciado -> Tipo: Multicast. Funcion: Verfica servidor. ↑\n");
        while(true){
            try{
                List<serverData> servidores = BD.getServidores();
                if(servidores.size() != 0){
                    for(int i=0 ; i < servidores.size() ; i++){
                        // Verifica servidores activos para mantener o eliminar
                        if(servidores.get(i).getTemp() == 0){
                            servidores.remove(i);
                            System.out.println(" Eliminado -> Tipo Multicast. Función: Elimina servidor inactivo. ¤\n ");
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
