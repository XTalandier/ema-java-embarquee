/* 
 * @author Manu, Yvan
 * 
 */
package dirigeablecore.GPS;

import com.phidgets.GPSPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachListener;
import com.phidgets.event.AttachEvent;
import dirigeablecore.DirigeableInfo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class GAttachListener implements AttachListener {

    public GAttachListener() {
    }

    public void attached(AttachEvent an) {
        System.out.println("[INFO]GPS branché");
        DirigeableInfo.gpsStatus.setCode(0);
        DirigeableInfo.gpsStatus.setMessage("Plugged");

        final GPSPhidget attached = (GPSPhidget) an.getSource();
        int startTime = 0;
        int delayTime = 100; //milliseconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Calendar cal;
                try {
                    cal = attached.getDateAndTime();
                    DateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyyy");
                    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS z");
                } catch (PhidgetException ex) {
                }
            }
        }, startTime, delayTime);


    }
}
