import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import static javax.swing.JOptionPane.showMessageDialog;


public class UnicastCl extends Thread{
    private final GetSetBD BD;
    String local = "Archivos";
    
    Cliente frameSoftware;
    Socket cliente;
    
    /* Constructor */
    public UnicastCl(GetSetBD BD, Cliente frameSoftware){
        this.frameSoftware = frameSoftware;
        this.BD = BD;
        System.out.print(" Creado -> Tipo: Cliente Unicast. √ \n ");
    }
    
    public void run(){
        System.out.println(" Iniciado -> Tipo: Cliente Unicast.  ↑\n ");
    }
    
    /* Conexion al servidor local. */
    public void conectaServidor() {
        try{
            cliente = new Socket (BD.getArchivoEncontradoServ(), 1234);
            System.out.println("Conexion con Servidor correcta. √\n");
        }catch(Exception e){
            showMessageDialog(null, "Problemas con el servidor. ¤\n");
        }
    }
    
    /*Descarga de archvos */
    public void descargaArchivo(){
        if(cliente != null){
        try {
            // Solicitud de archivo
            System.out.println("\n Atendiendo petición: " + BD.getArchivoEncontrado().getPath().toString());
            // Creacion flujo de datos de salida
            DataOutputStream dos = new DataOutputStream (cliente.getOutputStream());
            dos.writeUTF(BD.getArchivoEncontrado().getPath().toString());
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
                System.out.println(" Progreso: " + porciento_recibido + "%\n");
                }
            // Cierra flujos de datos
            dos.close();
            dosFile.close();
            dis.close();
            cliente.close();  
            System.out.println(" Archivo recibido exitosamente :D\n");
            showMessageDialog(null, " Archivo recibido exitosamente :D");

        } catch (IOException ex) {
            ex.printStackTrace();
            showMessageDialog(null, "No se ha podido completar la funcion. ¤\n");
            }
        }
    }
}
