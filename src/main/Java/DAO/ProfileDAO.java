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

    @PostConstruct
    public void init() {
        System.out.print("Test");
        profiles = new ArrayList<>();

        try {
            profiles.add(new Profile(0, "noreply@JaspervSon.nl", "JaspervSon", "@JaspervSon", "Jasper van Son", Role.ADMINISTRATOR, null, "Hi ik ben Jasper", "Tilburg", "www.youtube.com"));
            profiles.add(new Profile(1, "noreply@StefanoVerhoeve.nl", "StefanoVerhoeve", "@StefanoVerhoeve", "Stefano Verhoeve", null, "Hi ik ben Stefano", "Neverland", "www.youtube.com"));
            profiles.add(new Profile(2, "noreply@Wazzup.nl", "Wazzup", "@Wazzup", "Wazzup", null, "Wolla", "Tilburg", "lemonparty.org"));
        } catch (ProfileException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Profile> getProfiles() {
        return profiles;
    }

    @Override
    public Profile getProfile(int id) {

        for (Profile profile : profiles) {
            if (profile.getID() == id) {
                return profile;
            }
        }
        return null;
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

        profile = new Profile(profiles.size(), profile.getEmail(), profile.getPassword(), profile.getUserTag(), profile.getUserName(), profile.getProfilePicture(), profile.getBio(), profile.getLocation(), profile.getWebsiteURL());
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
        //otherProfile.FollowMe(myProfile);
    }

    @Override
    public void setRole(Profile profile, Role role) {
        profile.setRole(role);
    }

    @Override
    public List<Profile> getFollowing(String userTag) {
        return getProfile(userTag).getFollowing();
    }

    @Override
    public List<Profile> getFollowers(String userTag) {
        return getProfile(userTag).getFollowers();
    }

    @Override
    public void AddKweet(String userTag, Kweet kweet) {
        getProfile(userTag).AddKweet(kweet);
    }

    @Override
    public void removeProfile(Profile profile) {
        profiles.remove(profile);
    }

    @Override
    public void removeKweet(String userTag, Kweet kweet) {

    }

    @Override
    public Profile getProfileByEmail(String email) {

        for (Profile profile : profiles) {
            if (profile.getEmail().equals(email)) return profile;
        }

        return null;
    }

    @Override
    public void Load() {

    }
}
