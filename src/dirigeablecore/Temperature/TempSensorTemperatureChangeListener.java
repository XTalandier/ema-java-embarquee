/* 
 * @author Manu, Yvan
 */
package dirigeablecore.Temperature;

import com.phidgets.TemperatureSensorPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.TemperatureChangeListener;
import com.phidgets.event.TemperatureChangeEvent;
import dirigeablecore.DirigeableInfo;

public class TempSensorTemperatureChangeListener implements TemperatureChangeListener {

    /** Creates a new instance of TempSensorTemperatureChangeListener */
    public TempSensorTemperatureChangeListener() {
    }

    @Override
    public void temperatureChanged(TemperatureChangeEvent temperatureChangeEvent) {
        try {
            TemperatureSensorPhidget temperatureSensor = (TemperatureSensorPhidget) temperatureChangeEvent.getSource();
            DirigeableInfo.measuredTemperature = temperatureSensor.getAmbientTemperature();
            
            System.out.println("[INFO]" + temperatureSensor.toString()
                    + " , Température: " + temperatureSensor.getAmbientTemperature() + " °c"
                    + "\n");
        } catch (PhidgetException ex) {
            System.out.println("[ERROR]" + ex.getDescription());
        }
    }
}
