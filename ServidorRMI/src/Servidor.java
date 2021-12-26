
import static colors.colors.ANSI_GREEN;
import static colors.colors.ANSI_RESET;
import static colors.colors.ANSI_YELLOW;


public class Servidor extends Thread{

    Multicast ServidorMulticast = new Multicast();
    RMI ServidorRMI = new RMI();
    Unicast ServidorUnicast = new Unicast();
    
    public Servidor() {
        System.out.println( ANSI_GREEN + "[ Inicia ] "+ANSI_RESET+" initServidores Iniciando...");
        System.out.print( ANSI_YELLOW + "[ Info ] "+ANSI_RESET+" Iniciando Servidor Multicast. ");
        System.out.print( ANSI_YELLOW + "[ Info ] "+ANSI_RESET+" Iniciando Servidor RMI. ");
        System.out.println( ANSI_YELLOW + "[ Info ] "+ANSI_RESET+" Iniciando Servidor Unicast. ");
        ServidorMulticast.start();
        ServidorRMI.start();
        ServidorUnicast.start();
    }
    
    public static void main(String[] args) {
        try{
	    Servidor servidores = new Servidor();
	    servidores.start();
	}catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
