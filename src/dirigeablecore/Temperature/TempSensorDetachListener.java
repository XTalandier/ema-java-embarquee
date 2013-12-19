/* 
 * @autor Manu, Yvan
 */
package dirigeablecore.Temperature;

import com.phidgets.TemperatureSensorPhidget;
import com.phidgets.event.DetachListener;
import com.phidgets.event.DetachEvent;
import dirigeablecore.DirigeableInfo;

public class TempSensorDetachListener implements DetachListener {

    /** Creates a new instance of TempSensorDetachListener */
    public TempSensorDetachListener() {
    }

    public void detached(DetachEvent de) {
        System.out.println("[INFO]Capteur température débranché");
        DirigeableInfo.temperatureSensorStatus.setCode(2);
        DirigeableInfo.temperatureSensorStatus.setMessage("Unplugged");
        TemperatureSensorPhidget detached = (TemperatureSensorPhidget) de.getSource();
    }

}
