/**
 *
 * @author Manu, Yvan
 */
package dirigeablecore;

import java.util.Date;

public class DirigeableInfo {

    public DirigeableInfo()
    {
        //Temp
        measuredTemperature = (double)(Math.random() * (200-10)) + 10;
    }
    public static String dirigeableId = ""; //On prévoit le cas où plus tard le client dise "Oui mais si je veux pouvoir controler plusieur dirigeables..."
    public static Date date = new Date(); //Heure où les mesures ont été faites
    public static HashMapDirigeable message = new HashMapDirigeable();// Dans le cas ou j'envoie une commande, l'objet de retour me renvoie: OK, ERR#N: Moteur 2 HS, ....
    public static HashMapDirigeable gpsStatus = new HashMapDirigeable();
    public static HashMapDirigeable temperatureSensorStatus = new HashMapDirigeable();
    public static double altitude = 0;
    public static double longitude = 0;
    public static double latitude = 0;
    public static double velocity = 0;
    public static double heading = 0;
    public static double measuredTemperature = 0; //Si le capteur est un peu plus loin, au bout d'un cable...
}
