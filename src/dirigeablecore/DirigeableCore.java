/**
 *
 * @author Manu, Yvan
 */
package dirigeablecore;

import dirigeablecore.Temperature.TempSensorErrorListener;
import dirigeablecore.Temperature.TempSensorTemperatureChangeListener;
import dirigeablecore.Temperature.TempSensorDetachListener;
import dirigeablecore.Temperature.TempSensorAttachListener;
import dirigeablecore.GPS.GPositionChangeListener;
import dirigeablecore.GPS.GDetachListener;
import dirigeablecore.GPS.GAttachListener;
import dirigeablecore.GPS.GErrorListener;
import com.phidgets.GPSPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.TemperatureSensorPhidget;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class DirigeableCore
{        
  
    // GPS
    public static GPositionChangeListener positionListenerGPS;
    public static GAttachListener attachListenerGPS;
    public static GDetachListener detachListenerGPS;
    public static GErrorListener errorListenerListenerGPS;
    
    // Temperature
    public static TempSensorTemperatureChangeListener positionListenerTemp;
    public static TempSensorAttachListener attachListenerTemp;
    public static TempSensorDetachListener detachListenerTemp;
    public static TempSensorErrorListener errorListenerListenerTemp;

    public static void main(String[] args) throws IOException, PhidgetException 
    {
        int port = 8080;
        if(args.length != 0)
        {
            port = Integer.parseInt(args[0]);
        }       
        
        /////////////////Initialize Dirigeable Id with mac adress///////////////////
        System.out.println("[INIT]Initialisation de l'id du Dirireable...");
        DirigeableInfo.dirigeableId = java.util.UUID.randomUUID().toString();
        
        System.out.println("[INIT]Initialisation faite avec la valeur : " + DirigeableInfo.dirigeableId);

        DirigeableInfo.temperatureSensorStatus.setCode(2);
        DirigeableInfo.temperatureSensorStatus.setMessage("Unplugged");

        /////////////////////////Serveur HTTP///////////////////////////////////
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new dirigeablecore.HttpServer());
        server.setExecutor(null); // creates a default executor
        System.out.println("[INIT]Lancement du serveur Http...");
        server.start();
        System.out.println("[INIT]Serveur Http lancé sur le port " + port);

        /////////////////////////GPS///////////////////////////////
        final GPSPhidget gps = new GPSPhidget();
        // On lance l'ouverture du gps pour activer les événements
        gps.openAny();
        // On instancie les listeners 
        positionListenerGPS = new GPositionChangeListener();
        attachListenerGPS = new GAttachListener();
        detachListenerGPS = new GDetachListener();
        errorListenerListenerGPS = new GErrorListener(null);



        //////////////////INIT GPS////////////////////////
        System.out.println("[INIT]Initialisation du GPS...");

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

        if (!gps.isAttached()) {
            System.out.println("[INFO]GPS débranché");
            DirigeableInfo.gpsStatus.setCode(2);
            DirigeableInfo.gpsStatus.setMessage("Unplugged");
        } else {
            System.out.println("[INFO]GPS branché");
            DirigeableInfo.gpsStatus.setCode(0);
        DirigeableInfo.gpsStatus.setMessage("Plugged");
        }
        // On attache les listener au gps
        gps.addGPSPositionChangeListener(positionListenerGPS);
        gps.addAttachListener(attachListenerGPS);
        gps.addDetachListener(detachListenerGPS);
        gps.addErrorListener(errorListenerListenerGPS);



        ////////////////////////Temperature sensor///////////////////
        final TemperatureSensorPhidget temperatureSensor = new TemperatureSensorPhidget();
        // On lance l'ouverture du gps pour activer les événements
        temperatureSensor.openAny();

        //////////////////INIT TEMP////////////////////////
        System.out.println("[INIT]Initialisation du capteur de température...");

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

        if (!temperatureSensor.isAttached()) {
            System.out.println("[INFO]Capteur température débranché");
            DirigeableInfo.temperatureSensorStatus.setCode(2);
            DirigeableInfo.temperatureSensorStatus.setMessage("Unplugged");
        } else {
            System.out.println("[INFO]Capteur température branché");
            DirigeableInfo.temperatureSensorStatus.setCode(0);
            DirigeableInfo.temperatureSensorStatus.setMessage("Plugged");
        }

        // On instancie les listeners 
        positionListenerTemp = new TempSensorTemperatureChangeListener();
        attachListenerTemp = new TempSensorAttachListener();
        detachListenerTemp = new TempSensorDetachListener();
        errorListenerListenerTemp = new TempSensorErrorListener(null);

        // On attache les listeners 
        temperatureSensor.addTemperatureChangeListener(positionListenerTemp);
        temperatureSensor.addAttachListener(attachListenerGPS);
        temperatureSensor.addDetachListener(detachListenerGPS);
        temperatureSensor.addErrorListener(errorListenerListenerGPS);
    }
}