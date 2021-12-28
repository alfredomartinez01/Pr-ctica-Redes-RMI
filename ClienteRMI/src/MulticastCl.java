import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MulticastCl extends Thread{
    public static final String dirMulticast  = "228.1.1.1";
    public static final int ptoMulticast = 9014;
    public static final int tamBuffer=1024;
    private final GetSetBD BD;
    private List<searchResult> servidores = new ArrayList<>();
    InetAddress dirGrupo =null;


    /* Constructor  */
    public MulticastCl(GetSetBD BD){
        this.BD = BD;
        System.out.print(" Creado -> Tipo: Multicast. √ \n");
        try{
            dirGrupo = InetAddress.getByName(dirMulticast);
        }catch(UnknownHostException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public void run(){        
        System.out.print(" Iniciado -> Tipo: Multicast. ↑\n");
        try{
            MulticastSocket socket = new MulticastSocket(ptoMulticast); 
            socket.joinGroup(dirGrupo);
            String mensaje="";
            while(true){
                byte[] buf = new byte[tamBuffer];
                DatagramPacket recv = new DatagramPacket(buf,buf.length);
                socket.receive(recv);
                byte [] data = recv.getData();
                mensaje = new String(data);
                mensaje = mensaje.trim();
                
                // Verficiación de servidor nuevo o conocido
                searchResult ActualServer = new searchResult(recv.getAddress().toString().substring(1), recv.getPort(), 6);
                servidores = BD.getServidores();
                int bandera = traerServidor(servidores, ActualServer);
                if ( bandera == -1){
                    BD.addServidor(ActualServer);
                    System.out.println(" Nuevo -> Servidor añadido: "+recv.getAddress().toString().substring(1));
                }else{
                    servidores.get(bandera).setTemp(6);
                    BD.setServidores(servidores);
                }
            }        
        }catch(IOException e){
            e.printStackTrace();
            System.exit(2);
        }
    }
    

    public int traerServidor(List<searchResult> lista, searchResult e){
        for(int i = 0 ; i < lista.size() ; i++){
            if(lista.get(i).getDireccion().equals(e.getDireccion())){
                return i;
            }
        }
        return -1;
    }    
}
