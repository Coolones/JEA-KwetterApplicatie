package Service;

import Domain.Profile;
import Domain.Role;
import Exceptions.ProfileException;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Startup
public class StartUp {

    @Inject
    KweetService kweetService;

    @Inject
    ProfileService profileService;

    public StartUp() {}

    @PostConstruct
    private void initData() {
        /*try {
            profileService.AddProfile(new Profile(0, "noreply@JaspervSon.nl", "JaspervSon", "@JaspervSon", "Jasper van Son", Role.ADMINISTRATOR, null, "Hi ik ben Jasper", "Tilburg", "www.youtube.com"));
            profileService.AddProfile(new Profile(1, "noreply@StefanoVerhoeve.nl", "StefanoVerhoeve","@StefanoVerhoeve", "Stefano Verhoeve", null, "Hi ik ben Stefano", "Neverland", "www.youtube.com"));
            profileService.AddProfile(new Profile(2, "noreply@Wazzup.nl", "Wazzup","@Wazzup", "Wazzup", null, "Wolla", "Tilburg", "lemonparty.org"));
        } catch (ProfileException e) {
            e.printStackTrace();
        }*/
        /*try {
            profileService.AddProfile("@JaspervSon", "Jasper van Son", null, "Hi ik ben Jasper", "Tilburg", "www.youtube.com");
            profileService.AddProfile("@StefanoVerhoeven", "Stefano Verhoeven", null, "Hi ik ben Stefano", "Neverland", "www.youtube.com");
            profileService.AddProfile("@Wazzup", "Wazzup", null, "Wolla", "Tilburg", "lemonparty.org");
        } catch (ProfileException e) {
            e.printStackTrace();
        }*/
    }
}
