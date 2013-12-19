
/**
 *
 * @author Manu, Yvan
 */
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.json.simple.JSONObject;

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
        String commandeGson = "{\"altitude\":\"187\",\"longitude\":\"2255\",\"latitude\":\"2556\"}";

          
        JSONObject obj = new JSONObject();
        obj.put("altitude", "187");
        obj.put("longitude", "2255");
        obj.put("latitude", "2556");

        String jsonResponse = obj.toJSONString();     
         
        Assert.assertEquals(commandeGson, jsonResponse);
    }

 /*   @Test
    public void testCommunicationPostWrite() throws IOException {

        // comamnde sous forme de chaine de caractere pour créer le gson
        String commandeGson = "{\"altitude\":\"187\",\"longitude\":\"2255\",\"latitude\":\"2556\",\"cmdType\":\"GoToGPSPosition\"}";

        RestServeurPost serveurPostTest = new RestServeurPost();

        serveurPostTest.run();
        RestClientPost clientPostTest = new RestClientPost();


        Assert.assertTrue(clientPostTest.communicationClientRestPost());
        Assert.assertTrue(clientPostTest.sendJson(commandeGson));

    }
*/
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
