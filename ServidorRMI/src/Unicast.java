
import static colors.colors.ANSI_GREEN;
import static colors.colors.ANSI_RESET;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Unicast extends Thread{
    
    int puerto = 1234;
    ServerSocket server;
    Socket sk_cl;
    
    public void run(){
        try{
            server = new ServerSocket(puerto);
            server.setReuseAddress(true);
            System.out.print( ANSI_GREEN + "[Ok] "+ANSI_RESET+" Servidor Unicast Iniciado. ");
        } catch(Exception e){
            e.printStackTrace();
        }
        
        escucharPeticion();   
    }

    private void escucharPeticion() {
        for(;;){
            try {
                sk_cl = server.accept();
                System.out.println("[ Ok ] Cliente conectado desde: "+ sk_cl.getInetAddress()+":"+ sk_cl.getPort());
                
                //Leemos la entrada
                DataInputStream dis = new DataInputStream(sk_cl.getInputStream());
                String nombre = (String) dis.readUTF();
                
                System.out.println("[ Ok ] Transfieriendo archivo..."); 
                File f = new File(nombre);
                String name = f.getName();
                long tam = f.length();
                String path = f.getAbsolutePath();
                System.out.println("[ Ok ] Enviando archivo: " + name + " que mide " + tam + " bytes\n");
                DataOutputStream dos = new DataOutputStream (sk_cl.getOutputStream());
                DataInputStream disFile = new DataInputStream(new FileInputStream(path));
                
                //Envia primero el nombre
                dos.writeUTF(name); 
                dos.flush();
                
                //Envia segundo el tamaño
                dos.writeLong(tam);
                
                // Enviamos todo el archivo
                byte[] b =new byte[1500];
                long enviados = 0;
                int porciento = 0, n=0;
                while(enviados < tam){
                    n = disFile.read(b);
                    dos.write(b, 0, n);
                    dos.flush();
                    enviados+=n;
                    porciento = (int)((enviados*100)/tam);
                    System.out.println("[ Enviado ] Trasmitido el " + porciento + "%");
                }
                disFile.close();
                dos.close();
                sk_cl.close();
                System.out.println("[ Ok ] Archvio enviado con exito");

                dis.close();
                sk_cl.close(); 
                
            } catch (IOException ex) {
                Logger.getLogger(Unicast.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
