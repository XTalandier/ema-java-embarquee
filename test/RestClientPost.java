
/**
 *
 * @author yvanbarbaria
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestClientPost {

    public String input = "";
    public HttpURLConnection connection;

    public void RestClientPost() {
    }

    public boolean communicationClientRestPost() {

        boolean valueEnd = true;
        try {
            System.out.println("Création du serveur client ...");


            URL url = new URL("http://localhost:8080/");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.disconnect();

        } catch (MalformedURLException e) {

            valueEnd = false;

        } catch (IOException e) {

            valueEnd = false;
        }
        return valueEnd;
    }

    public boolean sendJson(String commandeJson) {
        boolean valueEnd = true;
        try {
            //String commandeJson = "{\"altitude\":\"187\",\"longitude\":\"2255\",\"latitude\":\"2556\",\"cmdType\":\"GoToGPSPosition\"}";
            System.out.println("Envoi de la commande JSon");
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF8");
            out.write(commandeJson);
            out.flush();
        } catch (IOException e) {
            valueEnd = false;
        }
        return valueEnd;

    }
}
