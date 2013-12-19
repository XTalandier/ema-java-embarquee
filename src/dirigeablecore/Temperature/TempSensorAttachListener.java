/*
 * @author Manu, Yvan
 */
package dirigeablecore.Temperature;

import com.phidgets.TemperatureSensorPhidget;
import com.phidgets.event.AttachListener;
import com.phidgets.event.AttachEvent;
import dirigeablecore.DirigeableInfo;

public class TempSensorAttachListener implements AttachListener {

    /** Creates a new instance of TempSensorAttachListener */
    public TempSensorAttachListener() {
        
    }
    
    @Override
    public void attached(AttachEvent ae) {
        System.out.println("[INFO]Capteur température branché");
        DirigeableInfo.temperatureSensorStatus.setCode(0);
        DirigeableInfo.temperatureSensorStatus.setMessage("Plugged");
        TemperatureSensorPhidget attached = (TemperatureSensorPhidget) ae.getSource();
    }
}


