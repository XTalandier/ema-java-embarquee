
/**
 *
 * @author Manu, Yvan
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dirigeablecore.DirigeableCommande;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;

public class DirigeableTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testJson() {

        // comamnde sous forme de chaine de caractere pour créer le gson
        String commandeGson = "{\"altitude\":\"187\",\"longitude\":\"2255\",\"latitude\":\"2556\",\"cmdType\":\"GoToGPSPosition\"}";

        // Création de la commande identique au gson
        DirigeableCommande testCommandeDirigeable = new DirigeableCommande();
        testCommandeDirigeable.altitude = 187;
        testCommandeDirigeable.longitude = 2255;
        testCommandeDirigeable.cmdType = "GoToGPSPosition";
        testCommandeDirigeable.latitude = 2556;

        // Création de l'objet gson depuis la chaine de caractère
        Gson gson = new GsonBuilder().create();
        DirigeableCommande dirigeableCommandeFromJson = gson.fromJson(commandeGson, DirigeableCommande.class);
        Assert.assertEquals(dirigeableCommandeFromJson, testCommandeDirigeable);
    }

    @Test
    public void testCommunicationPostWrite() throws IOException {

        // comamnde sous forme de chaine de caractere pour créer le gson
        String commandeGson = "{\"altitude\":\"187\",\"longitude\":\"2255\",\"latitude\":\"2556\",\"cmdType\":\"GoToGPSPosition\"}";

        RestServeurPost serveurPostTest = new RestServeurPost();

        serveurPostTest.run();
        RestClientPost clientPostTest = new RestClientPost();

        
        Assert.assertTrue(clientPostTest.communicationClientRestPost());
        Assert.assertTrue(clientPostTest.sendJson(commandeGson));

    }

   /* @Test
    public void testCommunicationPostWriteRead() throws IOException {

        // comamnde sous forme de chaine de caractere pour créer le gson
        String commandeGson = "{\"altitude\":\"187\",\"longitude\":\"2255\",\"latitude\":\"2556\",\"cmdType\":\"GoToGPSPosition\"}";

        RestServeurPost serveurPostTest = new RestServeurPost();

        RestClientPost clientPostTest = new RestClientPost();
        clientPostTest.input = commandeGson;

        serveurPostTest.communicationServeurRestPost();
        Assert.assertTrue(clientPostTest.communicationClientRestPost(""));

    }*/
}
