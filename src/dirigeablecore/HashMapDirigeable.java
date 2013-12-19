/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dirigeablecore;

import java.util.HashMap;

/**
 *
 * @author Jean-Hugues
 * @param <Object>
 */
public class HashMapDirigeable extends HashMap<String, Object> {
    public void setMessage(Object mess) {
        this.put("Message", mess);
    }
    
    public void setCode(Object code) {
        this.put("Code", code);
    }
}
