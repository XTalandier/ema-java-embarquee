/*
 * @author Manu, Yvan
 */
package dirigeablecore.Temperature;

import com.phidgets.event.ErrorListener;
import com.phidgets.event.ErrorEvent;
import dirigeablecore.DirigeableInfo;
import javax.swing.JFrame;

public class TempSensorErrorListener implements ErrorListener {

    private JFrame appFrame;

    /** Creates a new instance of TempSensorErrorListener */
    public TempSensorErrorListener(JFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void error(ErrorEvent errorEvent) {

        DirigeableInfo.temperatureSensorStatus.setCode(1);
        DirigeableInfo.temperatureSensorStatus.setMessage(errorEvent.toString());

        System.out.println("[ERROR]" + errorEvent.toString());
    }
}

