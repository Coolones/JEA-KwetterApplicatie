package DAO;

import Domain.Kweet;
import Domain.Profile;
import Domain.Role;
import Exceptions.ProfileException;
import iDAO.IProfileDAO;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Default
public class ProfileDAO implements IProfileDAO {

    private List<Profile> profiles;

    //@PostConstruct
    public void init() {
        profiles = new ArrayList<>();

        /*try {
            AddProfile("@JaspervSon", "Jasper van Son", null, "Hi ik ben Jasper", "Tilburg", "www.youtube.com");
            AddProfile("@StefanoVerhoeven", "Stefano Verhoeven", null, "Hi ik ben Stefano", "Neverland", "www.youtube.com");
            AddProfile("@Wazzup", "Wazzup", null, "Wolla", "Tilburg", "lemonparty.org");
        } catch (ProfileException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public List<Profile> getProfiles() {
        return profiles;
    }

    @Override
    public Profile getProfile(String userTag) {

        for (Profile profile : profiles) {
            if (profile.getUserTag().equals(userTag)) return profile;
        }

        return null;
    }

    @Override
    public Profile getProfileByUserName(String userName) {

        for (Profile profile : profiles) {
            if (profile.getUserTag().equals(userName)) return profile;
        }

        return null;
    }

    @Override
    public Profile AddProfile(Profile profile) throws ProfileException {

        if (!IsUniqueUserTag(profile.getUserTag())) throw new ProfileException("UserTag is already in use");

        profile = new Profile(profile);
        profiles.add(profile);

        return profile;
    }

    @Override
    public Profile EditProfile(Profile profile) throws ProfileException {

        profile = getProfile(profile.getUserTag()).EditProfile(profile);

        return profile;
    }


    @Override
    public boolean IsUniqueUserTag(String userTag) {
        for (Profile profile : profiles) {
            if (profile.getUserTag().equals(userTag)) return false;
        }

        return true;
    }

    @Override
    public void FollowProfile(Profile myProfile, Profile otherProfile) {
        myProfile.FollowOther(otherProfile);
        otherProfile.FollowMe(myProfile);
    }
}
