package DAO;

import Domain.Kweet;
import Domain.Profile;
import Domain.Role;
import iDAO.IProfileDAO;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileDAO implements IProfileDAO {

    private Map<String, Profile> profiles;
    private Map<String, Profile> profilesByUserName;

    public ProfileDAO() {
        profiles = new HashMap<>();
        profilesByUserName = new HashMap<>();
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
    public Profile AddProfile(String userTag, String userName, Role role, Image profilePicture, String bio, String location, String websiteURL) throws IllegalArgumentException {

        Profile profile = new Profile(userTag, userName, role, profilePicture, bio, location, websiteURL);
        AddProfileToMaps(profile);

        return profile;
    }

    @Override
    public boolean IsUniqueUserTag(String userTag) {
        return false;
    }

    @Override
    public void setUserName(String userTag, String userName) {
        getProfile(userTag).setUserName(userName);
    }

    @Override
    public void setRole(String userTag, Role role) {
        getProfile(userTag).setRole(role);
    }

    @Override
    public void setProfilePicture(String userTag, Image profilePicture) {
        getProfile(userTag).setProfilePicture(profilePicture);
    }

    @Override
    public void setBio(String userTag, String bio) {
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
    public void FollowOhter(String userTag, String userTag2) {
        getProfile(userTag).FollowOther(userTag2);
    }

    @Override
    public void FollowMe(String userTag, String userTag2) {
        getProfile(userTag).FollowMe(userTag2);
    }

    private void AddProfileToMaps (Profile profile) {

        if (profiles.get(profile.getUserTag()) != null) {
            profiles.put(profile.getUserTag(), profile);
        }

        if (profilesByUserName.get(profile.getUserName()) != null) {
            profilesByUserName.put(profile.getUserName(), profile);
        }
    }
}
