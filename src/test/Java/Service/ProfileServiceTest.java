package Service;

import Domain.Profile;
import Exceptions.ProfileException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class ProfileServiceTest {

    @Inject
    ProfileService profileService;

    Profile profile;
    Profile stefano;
    Profile random;

    @Before
    public void setUp() throws Exception {
        profile = profileService.AddProfile(new Profile(0, "noreply@JaspervSon.nl", "JaspervSon", "@JaspervSon", "Jasper van Son", null, "Hi ik ben Jasper", "Tilburg", "www.youtube.com"));
        stefano = profileService.AddProfile(new Profile(1, "noreply@StefanoVerhoeve.nl", "StefanoVerhoeve","@StefanoVerhoeven", "Stefano Verhoeven", null, "Hi ik ben Stefano", "Neverland", "www.youtube.com"));
        random = profileService.AddProfile(new Profile(2, "noreply@Wazzup.nl", "Wazzup","@Wazzup", "Wazzup", null, "Wolla", "Tilburg", "lemonparty.org"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void followProfile() {
        profileService.FollowProfile(profile.getUserTag(), stefano.getUserTag());

        assertEquals(1, profile.getFollowing().size());

        profileService.FollowProfile(stefano.getUserTag(), profile.getUserTag());
        profileService.FollowProfile(random.getUserTag(), profile.getUserTag());

        assertEquals(2, profile.getFollowers().size());
    }

    /*@Test
    public void followOther() {
        profileService.FollowOther(profile.getUserTag(), "@Stefano");

        assertEquals(1, profile.getFollowing().size());
    }

    @Test
    public void followMe() {
        profileService.FollowMe(profile.getUserTag(), "@Stefano");
        profileService.FollowMe(profile.getUserTag(), "@FrankC");

        assertEquals(2, profile.getFollowers().size());
    }*/

    /*@Test
    public void setUserName() {
        profileService.setUserName(profile.getUserTag(), "JaspervSon");

        assertEquals("JaspervSon", profile.getUserName());
    }*/

    @Test(expected= ProfileException.class)
    public void EditProfile() throws ProfileException {
        Profile temp = new Profile(0, "noreply@JaspervSon.nl", "JaspervSon", "@JaspervSon", "Jasper van Son Joh", null, "Hi ik ben Jasper Joh", "Tilburg Joh", "www.youtube.com/Joh");

        profile.EditProfile(temp);
    }

    /*@Test(expected= ProfileException.class)
    public void setBio() throws ProfileException {
            String randomBio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Diam donec adipiscing tristique risus nec feugiat in fermentum. Id eu nisl nunc mi ipsum faucibus vitae aliquet. Viverra orci sagittis eu volutpat. Facilisis gravida neque convallis a cras semper auctor neque vitae. Et malesuada fames ac turpis egestas maecenas pharetra convallis posuere. Viverra accumsan in nisl nisi scelerisque eu ultrices. Scelerisque mauris pellentesque pulvinar pellentesque habitant morbi tristique senectus et. Condimentum mattis pellentesque id nibh tortor id aliquet. Vulputate mi sit amet mauris commodo quis imperdiet massa. Laoreet id donec ultrices tincidunt arcu non sodales neque. Ultricies tristique nulla aliquet enim tortor at auctor. Cursus mattis molestie a iaculis at erat pellentesque adipiscing. Sem integer vitae justo eget magna fermentum iaculis eu. Integer malesuada nunc vel risus commodo. Nullam vehicula ipsum a arcu cursus vitae. Nunc eget lorem dolor sed viverra ipsum nunc. Sit amet consectetur adipiscing elit duis tristique sollicitudin. Vitae elementum curabitur vitae nunc sed velit dignissim sodales. Penatibus et magnis dis parturient montes nascetur ridiculus mus.\n" +
                    "\n" +
                    "Nisi vitae suscipit tellus mauris a diam maecenas sed. Bibendum neque egestas congue quisque egestas diam in. Ut placerat orci nulla pellentesque. Odio pellentesque diam volutpat commodo sed egestas egestas. Faucibus ornare suspendisse sed nisi lacus sed viverra tellus in. Eu turpis egestas pretium aenean. Quam elementum pulvinar etiam non. Ut lectus arcu bibendum at. Donec et odio pellentesque diam volutpat commodo sed egestas. Vitae aliquet nec ullamcorper sit. In eu mi bibendum neque. Mattis nunc sed blandit libero volutpat sed cras ornare arcu. Porttitor leo a diam sollicitudin tempor. Est velit egestas dui id ornare arcu odio ut sem. Pretium vulputate sapien nec sagittis aliquam malesuada bibendum arcu vitae. Eget mi proin sed libero enim sed faucibus turpis.\n" +
                    "\n" +
                    "Dui id ornare arcu odio ut sem nulla pharetra. Enim eu turpis egestas pretium aenean pharetra magna ac. Venenatis tellus in metus vulputate eu. Ultrices vitae auctor eu augue. Congue quisque egestas diam in arcu cursus euismod quis viverra. Mattis vulputate enim nulla aliquet porttitor lacus luctus accumsan tortor. Sollicitudin ac orci phasellus egestas tellus. Duis tristique sollicitudin nibh sit amet commodo nulla facilisi nullam. At tempor commodo ullamcorper a lacus vestibulum sed. Aliquam nulla facilisi cras fermentum odio eu feugiat.\n" +
                    "\n" +
                    "Hendrerit dolor magna eget est lorem ipsum dolor sit. Sit amet consectetur adipiscing elit duis tristique sollicitudin. Enim praesent elementum facilisis leo vel. Volutpat odio facilisis mauris sit amet. Platea dictumst vestibulum rhoncus est pellentesque. Pharetra sit amet aliquam id. Fermentum et sollicitudin ac orci. Mattis ullamcorper velit sed ullamcorper morbi tincidunt ornare massa. Mi ipsum faucibus vitae aliquet nec ullamcorper. Massa sapien faucibus et molestie ac feugiat sed lectus vestibulum. Risus ultricies tristique nulla aliquet enim tortor. Diam sit amet nisl suscipit adipiscing bibendum est ultricies. Tortor aliquam nulla facilisi cras fermentum odio eu. Turpis in eu mi bibendum neque egestas congue quisque. Posuere sollicitudin aliquam ultrices sagittis orci a. Ac auctor augue mauris augue neque gravida in. Pretium nibh ipsum consequat nisl vel. Sit amet dictum sit amet justo.\n" +
                    "\n" +
                    "Vivamus at augue eget arcu dictum varius duis at. Sem et tortor consequat id porta nibh venenatis cras. Amet tellus cras adipiscing enim eu turpis egestas pretium aenean. Ut tristique et egestas quis ipsum suspendisse ultrices gravida dictum. Enim nunc faucibus a pellentesque sit amet porttitor eget dolor. Faucibus pulvinar elementum integer enim neque volutpat ac. Netus et malesuada fames ac turpis egestas. Purus sit amet luctus venenatis lectus. Consectetur adipiscing elit pellentesque habitant morbi tristique senectus et. Blandit libero volutpat sed cras ornare arcu dui vivamus.";

            profileService.setBio(profile.getUserTag(), randomBio);
    }*/
}