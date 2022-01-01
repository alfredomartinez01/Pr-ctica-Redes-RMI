
import static colors.colors.ANSI_GREEN;
import static colors.colors.ANSI_RESET;
import static colors.colors.ANSI_YELLOW;


public class Servidor extends Thread{

    Multicast s_multicast = new Multicast();
    RMI s_rmi = new RMI();
    Unicast s_unicast = new Unicast();
    
    public Servidor() {
        System.out.println( ANSI_GREEN + "[ Inicia ] "+ANSI_RESET+" initServidores Iniciando...");
        System.out.print( ANSI_YELLOW + "[ Info ] "+ANSI_RESET+" Iniciando Servidor Multicast. ");
        System.out.print( ANSI_YELLOW + "[ Info ] "+ANSI_RESET+" Iniciando Servidor RMI. ");
        System.out.println( ANSI_YELLOW + "[ Info ] "+ANSI_RESET+" Iniciando Servidor Unicast. ");
        s_multicast.start();
        s_rmi.start();
        s_unicast.start();
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
