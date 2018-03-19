package Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.assertEquals;

public class KweetTest {

    Kweet kweet;
    String message = "Hallo @FrankC @Michel @LijpeMofo @HelloWorld vandaag zijn we bijeengekomen om #SamenZijn #JEAhalen #SchoolIsCool en er een leuke dag van te maken.";
    Profile profile;
    Profile stefano;

    @Before
    public void setUp() throws Exception {
        Profile profile = new Profile(0, "@JaspervSon", "Jasper van Son", null, "Hi ik ben Jasper", "Tilburg", "www.youtube.com");
        Profile stefano = new Profile(1,"@StefanoVerhoeven", "Stefano Verhoeven", null, "Hi ik ben Stefano", "Neverland", "www.youtube.com");
        kweet = new Kweet(0, profile, message, new ArrayList<>(), new ArrayList<>());
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void appreciatieKweet() {
        kweet.AppreciateKweet(stefano);

        assertEquals(1, kweet.getAppreciatedBy().size());
    }

    @Test
    public void getKweetID() {
        assertEquals(0, kweet.getID());
    }

    @Test
    public void getOwnerTag() {
        assertEquals("@JaspervSon", kweet.getOwner().getUserTag());
    }

    @Test
    public void getKweet() {
        assertEquals(message, kweet.getKweet());
    }

    /*@Test
    public void setKweet() {
        String newMessage = "Hello world. Lorem Ipsum Dolor ... @Stefano #ThisTakesSoLong";

        kweet.setKweet(newMessage);

        assertEquals(newMessage, kweet.getKweet());
        assertEquals(1, kweet.getMentions().size());
        assertEquals(1, kweet.getTrends());
    }*/

    @Test
    public void getPostDate() {
        assertEquals(new Date(), kweet.getPostDate());
    }

    @Test
    public void getMentions() {
        assertEquals(4, kweet.getMentions().size());
    }

    @Test
    public void getTrends() {
        assertEquals(3, kweet.getTrends().size());
    }

    @Test
    public void getAppreciatedBy() {
        assertEquals(1, kweet.getAppreciatedBy().size());
    }
}