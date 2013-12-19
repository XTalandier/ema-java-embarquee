/* 
 * @author Manu, Yvan
 */
package dirigeablecore.GPS;

import com.phidgets.event.ErrorListener;
import com.phidgets.event.ErrorEvent;
import dirigeablecore.DirigeableInfo;
import javax.swing.JFrame;

public class GErrorListener implements ErrorListener {

    private JFrame appFrame;

    public GErrorListener(JFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void error(ErrorEvent errorEvent) 
    { 
        DirigeableInfo.gpsStatus.setCode(1);
        DirigeableInfo.gpsStatus.setMessage(errorEvent.toString());
        System.out.println("[ERROR]" + errorEvent.toString());
    }
}

