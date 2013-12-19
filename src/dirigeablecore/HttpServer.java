/*
 * @author Manu, Yvan
 * 
 */
package dirigeablecore;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HttpServer implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException 
    {
        InputStreamReader isr = new InputStreamReader(t.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }

        if (t.getRequestURI().toString().startsWith("/netbeans-tomcat-status-test"))
        {
            return;
        } 
        else 
        {
           // Gson gson = new GsonBuilder().excludeFieldsWithModifiers().create();
            DirigeableCommande request = new DirigeableCommande();
            System.out.println("[INFO]Réception d'une requete Http : \n" + buf.toString());
            try
            {
                System.out.println("[INFO]Désérialisation de l'objet 'DirigeableCommande'...");
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(buf.toString());
                JSONObject jsonObject = (JSONObject) obj;
                
                request.cmdType = (String) jsonObject.get("cmdType");
                
                if(jsonObject.containsKey("altitude") && jsonObject.containsKey("longitude") && jsonObject.containsKey("latitude"))
                {
                    request.altitude = Double.parseDouble(jsonObject.get("altitude").toString());
                    request.longitude = Double.parseDouble(jsonObject.get("longitude").toString());
                    request.latitude = Double.parseDouble(jsonObject.get("latitude").toString());
                }   
            }
            catch(Exception error)
            {
                System.out.println("[ERROR]" + error.getMessage()); 
                DirigeableInfo.message.put("Code", 6);
                DirigeableInfo.message.put("Message", "Error in JSON deserialization");        
                SendResponse(t); 
            }

            if (request != null) 
            {
                try
                {
                    System.out.println("[INFO]Désérialisation réussie");
                    ProccessRequest(request, t);
                }
                catch(Exception error)
                {
                    System.out.println("[ERROR]" + error.getMessage());                    
                    DirigeableInfo.message.put("Code", 3);
                    DirigeableInfo.message.put("Message", error.getMessage());        
                    SendResponse(t);
                }
            } 
            else
            {
                t.sendResponseHeaders(10, "Mauvais format JSON".length());
                OutputStream os = t.getResponseBody();
                System.out.println("[ERROR]Requête non reconnue");
                os.write("Reception d'une commande JSON au format non reconnu".getBytes());
                os.close();
            }
        }
    }    
    
    private void SendResponse(HttpExchange t)
    {        
        System.out.println("[INFO]Sérialisation de l'objet 'DirigeableInfo' au format JSON...");
        //String jsonResponse = gson.toJson(new DirigeableInfo());
        
        JSONObject obj = new JSONObject();
	obj.put("dirigeableId", DirigeableInfo.dirigeableId);
        obj.put("date", DirigeableInfo.date.toString());
        obj.put("message", DirigeableInfo.message);
        obj.put("gpsStatus", DirigeableInfo.gpsStatus);
        obj.put("temperatureSensorStatus", DirigeableInfo.temperatureSensorStatus);
        obj.put("altitude", DirigeableInfo.altitude);
        obj.put("longitude", DirigeableInfo.longitude);
        obj.put("latitude", DirigeableInfo.latitude);
        obj.put("velocity", DirigeableInfo.velocity);
        obj.put("heading", DirigeableInfo.heading);
        obj.put("measuredTemperature", DirigeableInfo.measuredTemperature); 
        
        String jsonResponse = obj.toJSONString();     
        System.out.println("[INFO]Sérialisation réussie : \n" + jsonResponse);

        try 
        {
            t.sendResponseHeaders(200, jsonResponse.getBytes().length);
            OutputStream os = t.getResponseBody();
            System.out.println("[INFO]Envoie de la réponse JSON");
            os.write(jsonResponse.getBytes());
            os.close();
        } 
        catch (Exception error) 
        {
            System.out.println("[ERROR]" + error.getMessage());
        }        
    }

    private void ProccessRequest(DirigeableCommande request, HttpExchange t) throws IOException 
    {
        if (request.cmdType.equalsIgnoreCase("GoToGPSPosition")) {
            System.out.println("[INFO]Traitement de la commande 'GoToGPSPosition'");
            DirigeableInfo.message.put("Code", 11);
            DirigeableInfo.message.put("Message", "Command recognized but not implemented");
            //TODO
        } else if (request.cmdType.equalsIgnoreCase("Forward")) {
            System.out.println("[INFO]Traitement de la commande 'Forward'");
            DirigeableInfo.message.put("Code", 11);
            DirigeableInfo.message.put("Message", "Command recognized but not implemented");
            //TODO
        } else if (request.cmdType.equalsIgnoreCase("GetInfos")) {
            System.out.println("[INFO]Traitement de la commande 'GetInfos'");
            DirigeableInfo.message.put("Code", 4);
            DirigeableInfo.message.put("Message", "OK");
        } else if (request.cmdType.equalsIgnoreCase("Left")) {
            System.out.println("[INFO]Traitement de la commande 'Left'");            
            DirigeableInfo.message.put("Code", 11);
            DirigeableInfo.message.put("Message", "Command recognized but not implemented");
            //TODO
        } else if (request.cmdType.equalsIgnoreCase("Right")) {
            System.out.println("[INFO]Traitement de la commande 'Right'");
            DirigeableInfo.message.put("Code", 11);
            DirigeableInfo.message.put("Message", "Command recognized but not implemented");
            //TODO
        } else if (request.cmdType.equalsIgnoreCase("Back")) {
            System.out.println("[INFO]Traitement de la commande 'Back'");
            DirigeableInfo.message.put("Code", 11);
            DirigeableInfo.message.put("Message", "Command recognized but not implemented");
            //TODO
        } else if (request.cmdType.equalsIgnoreCase("Up")) {
            System.out.println("[INFO]Traitement de la commande 'Up'");
            DirigeableInfo.message.put("Code", 11);
            DirigeableInfo.message.put("Message", "Command recognized but not implemented");
            //TODO
        } else if (request.cmdType.equalsIgnoreCase("Down")) {
            System.out.println("[INFO]Traitement de la commande 'Down'");
            DirigeableInfo.message.put("Code", 11);
            DirigeableInfo.message.put("Message", "Command recognized but not implemented");
            //TODO
        } else {
            System.out.println("[WARN]Commande inconnue");
            DirigeableInfo.message.put("Code", 9);
            DirigeableInfo.message.put("Message", "Error - Unknown commande");
        }
        
        SendResponse(t);        
    }
}