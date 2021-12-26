import static colors.colors.ANSI_BLUE;
import static colors.colors.ANSI_GREEN;
import static colors.colors.ANSI_RESET;
import static colors.colors.ANSI_YELLOW;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;



public class Multicast extends Thread{
 
    public static final String MCAST_ADDR  = "228.1.1.1";
    public static final int MCAST_PORT = 9014;
    public static final int DGRAM_BUF_LEN = 1024;
    
    InetAddress grupo = null;

    public void run(){
        System.out.print( ANSI_GREEN + "[Ok] "+ANSI_RESET+" Servidor Multicast Iniciado. ");
        try{
            grupo = InetAddress.getByName(MCAST_ADDR);
            while(true){
           
                enviar("Iniciando servicio...");
                try{
                    Thread.sleep(5000);
                } 
                catch(InterruptedException ex) 
                {
                    Thread.currentThread().interrupt();
                }
                
            }        
        }catch(IOException e){
            e.printStackTrace();
            System.exit(2);
        }
    }
    
    public Boolean enviar(String msg){
        try{
            // Configuramos para escuchar el paquete
            MulticastSocket skt = new MulticastSocket(MCAST_PORT);
            skt.joinGroup(grupo); 
            DatagramPacket packet = new DatagramPacket(msg.getBytes(),msg.length(),grupo,MCAST_PORT);
            skt.send(packet);
            skt.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    
}
