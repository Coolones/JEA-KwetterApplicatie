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

    private Map<String, Profile> profiles;
    private Map<String, Profile> profilesByUserName;

    //@PostConstruct
    public void init() {
        profiles = new HashMap<>();
        profilesByUserName = new HashMap<>();

        try {
            AddProfile("@JaspervSon", "Jasper van Son", null, "Hi ik ben Jasper", "Tilburg", "www.youtube.com");
            AddProfile("@StefanoVerhoeven", "Stefano Verhoeven", null, "Hi ik ben Stefano", "Neverland", "www.youtube.com");
            AddProfile("@Wazzup", "Wazzup", null, "Wolla", "Tilburg", "lemonparty.org");
        } catch (ProfileException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Profile> getProfiles() {
        return new ArrayList(profiles.values());
    }

    @Override
    public Profile getProfile(String userTag) {
        return profiles.get(userTag);
    }

    @Override
    public Profile getProfileByUserName(String userName) {
        return profilesByUserName.get(userName);
    }

    @Override
    public Profile AddProfile(String userTag, String userName, Image profilePicture, String bio, String location, String websiteURL) throws ProfileException {

        if (!IsUniqueUserTag(userTag)) throw new ProfileException("UserTag is already in use");

        Profile profile = new Profile(userTag, userName, profilePicture, bio, location, websiteURL);
        AddProfileToMaps(profile);

        return profile;
    }

    @Override
    public boolean IsUniqueUserTag(String userTag) {
        if (profiles.containsKey(userTag)) return false;
        else return true;
    }

    @Override
    public void setUserName(String userTag, String userName) {
        getProfile(userTag).setUserName(userName);
    }

    @Override
    public void setRole(String userTag, Role role) throws ProfileException {
        getProfile(userTag).setRole(role);
    }

    @Override
    public void setProfilePicture(String userTag, Image profilePicture) {
        getProfile(userTag).setProfilePicture(profilePicture);
    }

    @Override
    public void setBio(String userTag, String bio) throws ProfileException {
        getProfile(userTag).setBio(bio);
    }

    @Override
    public void setLocation(String userTag, String location) {
        getProfile(userTag).setLocation(location);
    }

    @Override
    public void setWebsiteURL(String userTag, String websiteURL) {
        getProfile(userTag).setWebsiteURL(websiteURL);
    }

    @Override
    public void FollowOther(String userTag, String userTag2) {
        getProfile(userTag).FollowOther(userTag2);
    }

    @Override
    public void FollowMe(String userTag, String userTag2) {
        getProfile(userTag).FollowMe(userTag2);
    }

    private void AddProfileToMaps (Profile profile) {

        if (profiles.get(profile.getUserTag()) == null) {
            profiles.put(profile.getUserTag(), profile);
        }

        if (profilesByUserName.get(profile.getUserName()) == null) {
            profilesByUserName.put(profile.getUserName(), profile);
        }
    }
}
