/**
 *
 * @author Manu, Yvan
 * Listener qui permet de suivre le changement de position des coordonnées GPS
 */
package dirigeablecore.GPS;

import com.phidgets.GPSPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.GPSPositionChangeEvent;
import com.phidgets.event.GPSPositionChangeListener;
import dirigeablecore.DirigeableInfo;

public class GPositionChangeListener implements GPSPositionChangeListener {

    private double pasLongitude = 0.000009;
    private double pasLatitude = 0.000005;
    private double pasAltitude = 10;

    public GPositionChangeListener() {
        //Constructeur vide pour le moment à voir si dans le futur si nous avons besoin d'attributs
        // Permet d'instancier le listener et d'ajouter l'évenement positionChanged
    }

    // Evenement qui permet de suivre le changement de position GPS
    @Override
    public void gpsPositionChanged(GPSPositionChangeEvent gpsPositionChangeEvent) {

        GPSPhidget gps = (GPSPhidget) gpsPositionChangeEvent.getSource();

        try {
            // On récupère les coordonnées GPS courante
            if(HasGap(gps.getLongitude(),  gps.getLatitude(), gps.getAltitude()))
            {
                DirigeableInfo.latitude = gps.getLatitude();
                DirigeableInfo.longitude = gps.getLongitude();
                DirigeableInfo.altitude = gps.getAltitude();
                DirigeableInfo.velocity = gps.getVelocity();
                DirigeableInfo.heading = gps.getHeading();                        

                System.out.println("[INFO]" + gps.toString()
                        + ", Velocity: " + gps.getVelocity() + "km/h"
                        + ", Heading: " + gps.getHeading() + " degrees"
                        + "\n" + ", Latitude: " + gps.getLatitude() + "°"
                        + ", Altitude: " + gps.getAltitude() + "m"
                        + ", Longitude: " + gps.getLongitude() + "°" + "\n");
            }

        } catch (PhidgetException ex) {
            System.out.println("\n--->Error: " + ex.getDescription());
        }
    }

    private boolean HasGap(double longitude, double latitude, double altitude) {
        if (DirigeableInfo.latitude == 0 && DirigeableInfo.longitude == 0 && DirigeableInfo.altitude == 0) {
            return true;
        }
         
        if ((latitude - DirigeableInfo.latitude )> this.pasLatitude) {
             return true;
        }
        if ((longitude - DirigeableInfo.longitude )> this.pasLongitude) {
            return true;
        }
        //if (altitude - DirigeableCore.lastAltitude > this.pasAltitude) {
        //    return true;
        //}

        return false;
    }
}
