import static colors.colors.ANSI_BLUE;
import static colors.colors.ANSI_GREEN;
import static colors.colors.ANSI_RESET;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import static javax.swing.JOptionPane.showMessageDialog;


public class UnicastCl extends Thread{
    private final GetSetBD BD;
    String local = "src\\Archivos";
    
    softwareDownload frameSoftware;
    Socket cliente;
    
    /* Constructor */
    public UnicastCl(GetSetBD BD, softwareDownload frameSoftware){
        this.frameSoftware = frameSoftware;
        this.BD = BD;
        System.out.print( ANSI_BLUE + " Creado. Tipo: Unicast ");
    }
    
    public void run(){
        System.out.println( ANSI_GREEN + " Iniciado. Tipo: Unicast ");
    }
    
    /* Conexion al servidor local. */
    public void conectaServidor() {
        try{
            cliente = new Socket (BD.getArchivoEncontradoServ(), 1234); //socket bloquante
            System.out.println(" Conectado ");
        }catch(Exception e){
            showMessageDialog(null, "Error: Conexión con el servidor");
        }
    }
    
    /*Descarga de archvos */
    public void descargaArchivo(){
        if(cliente != null){
        try {
            // Solicitud de archivo
            System.out.println(" Atendiendo petición: " + BD.getArchivoEncontrado().getRuta().toString());
            // Creacion flujo de datos de salida
            DataOutputStream dos = new DataOutputStream (cliente.getOutputStream());
            dos.writeUTF(BD.getArchivoEncontrado().getRuta().toString());
            dos.flush();
            
            // Respuesta: archivo solicitado
            DataInputStream dis = new DataInputStream(cliente.getInputStream());
            String nombre = (String) dis.readUTF();
            long tam = (long) dis.readLong();
            DataOutputStream dosFile = new DataOutputStream (new FileOutputStream(local+"/"+nombre));
            
            // Porcentaje de descarga
            byte [] b = new byte [1500];
            long recibidos = 0;
            int porciento_recibido = 0, n=0;
            while (recibidos < tam){
                n = dis.read(b);
                dosFile.write(b, 0, n);
                dosFile.flush();
                recibidos +=n;
                porciento_recibido = (int)((recibidos*100)/tam);
                System.out.println(" Progreso: " + porciento_recibido + "%");
                frameSoftware.setProgressBar(porciento_recibido);
            }
            // Cierra flujos de datos
            dos.close();
            dosFile.close();
            dis.close();
            cliente.close();  
            System.out.println(" Archivo recibido exitosamente :D");
            showMessageDialog(null, " Archivo recibido exitosamente :D");

        } catch (IOException ex) {
            showMessageDialog(null, " Error: Carga del archivo ");
            }
        }
    }
}
