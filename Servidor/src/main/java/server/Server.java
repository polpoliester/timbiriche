package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brawun
 */
public class Server {

    static volatile List<ServerHilo> threads = new ArrayList<>();
    static int MAX = 4;
    private ServerProtocolo ssp = new ServerProtocolo();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(9999);
        
        System.out.println("Servidor inicializado en el puerto 9999");
        System.out.println("Soportando un máximo de " + MAX + " jugadores");

        Socket s;

        while (true) {
            s = ss.accept();

            if (threads.size() < MAX) {
                System.out.println("Nueva conexion por parte de cliente: " + s);

                ServerHilo sst = new ServerHilo(s, threads, MAX);

                Thread t = new Thread(sst);

                threads.add(sst);

                t.start();
            } else {
                System.out.println("Servidor lleno. Máximo número de conexiones simultaneas");
            }
        }
    }
}
