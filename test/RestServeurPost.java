/**
 *
 * @author yvanbarbaria
 */
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class RestServeurPost implements Runnable {

    @Override
    public void run() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 10);
            server.createContext("/", new dirigeablecore.HttpServer());
            server.setExecutor(null); // creates a default executor
            System.out.println("Lancement du serveur Http...");
            server.start();
            System.out.println("Serveur Http lancé");

        } catch (Exception e) {
        }
    }
}
