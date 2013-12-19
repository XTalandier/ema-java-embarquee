/* 
 * @author Manu, Yvan
 * 
 */
package dirigeablecore.GPS;

import com.phidgets.GPSPhidget;
import com.phidgets.event.DetachListener;
import com.phidgets.event.DetachEvent;
import dirigeablecore.DirigeableInfo;

public class GDetachListener implements DetachListener {

    public GDetachListener() {
    }

    public void detached(DetachEvent de) {
        System.out.println("[INFO]GPS débranché");
        DirigeableInfo.gpsStatus.setCode(2);
        DirigeableInfo.gpsStatus.setMessage("Unplugged");

        GPSPhidget detached = (GPSPhidget) de.getSource();
    }
}
