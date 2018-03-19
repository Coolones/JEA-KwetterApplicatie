package Service;

import Domain.Kweet;
import Domain.Profile;
import Exceptions.KweetException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class KweetServiceTest {

    Profile profile;
    Profile stefano;
    Profile random;

    @Inject
    KweetService kweetService;

    @Inject
    ProfileService profileService;

    Kweet kweet;
    String message = "Hallo @FrankC @Michel @LijpeMofo @HelloWorld vandaag zijn we bijeengekomen om #SamenZijn #JEAhalen #SchoolIsCool en er een leuke dag van te maken.";

    @Before
    public void setUp() throws Exception {

        profile = profileService.AddProfile(new Profile(0, "@JaspervSon", "Jasper van Son", null, "Hi ik ben Jasper", "Tilburg", "www.youtube.com"));
        stefano = profileService.AddProfile(new Profile(1,"@StefanoVerhoeven", "Stefano Verhoeven", null, "Hi ik ben Stefano", "Neverland", "www.youtube.com"));
        random = profileService.AddProfile(new Profile(2,"@Wazzup", "Wazzup", null, "Wolla", "Tilburg", "lemonparty.org"));
        kweet = kweetService.AddKweet("@JaspervSon", message);
        kweet.AppreciateKweet(stefano);
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
        assertEquals(1, kweetService.getKweetsFromUser(profile.getUserTag()));
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
        assertEquals(message, kweetService.getKweetByID(kweet.getID()).getKweet());
    }

    @Test
    public void removeKweet() {
        kweetService.RemoveKweet(kweet);
        assertEquals(0, kweetService.getKweetsFromUser("@JaspervSon"));
    }
}