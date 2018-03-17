package Service;

import Domain.Kweet;
import Exceptions.KweetException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class KweetServiceTest {

    @Inject
    KweetService kweetService;

    Kweet kweet;
    String message = "Hallo @FrankC @Michel @LijpeMofo @HelloWorld vandaag zijn we bijeengekomen om #SamenZijn #JEAhalen #SchoolIsCool en er een leuke dag van te maken.";

    @Before
    public void setUp() throws Exception {
        kweetService.AddKweet("@Jasper", message);
        kweet.AppreciateKweet("@Stefano");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getKweets() {
        assertEquals(1, kweetService.getKweets().size());
    }

    @Test
    public void getKweetsFromUser() {
        assertEquals(1, kweetService.getKweetsFromUser("@Jasper").size());
    }

    @Test
    public void getTenKweetsFromUser() {
        assertEquals(1, kweetService.getKweetsFromUser(kweet.getOwnerTag()));
    }

    @Test
    public void getMostPopularTrends() {
        assertEquals(3, kweetService.getMostPopularTrends().size());
    }

    @Test
    public void getKweetsByTrend() {
        assertEquals(1, kweetService.getKweetsByTrend("#SchoolIsCool").size());
    }

    @Test
    public void addKweet() throws KweetException {
        kweetService.AddKweet("@Stefano", message);

        assertEquals(1, kweetService.getKweetsFromUser("@Stefano").size());
    }

    @Test
    public void getKweetByID() {
        assertEquals(message, kweetService.getKweetByID(kweet.getKweetID()).getKweet());
    }

    @Test
    public void removeKweet() {
        kweetService.RemoveKweet(kweet);
        assertEquals(0, kweetService.getKweetsFromUser("@Jasper"));
    }
}