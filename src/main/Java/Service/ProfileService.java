package Service;

import Domain.Profile;
import Domain.Role;
import Exceptions.ProfileException;
import iDAO.IProfileDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.awt.*;
import java.util.List;

@Stateless
public class ProfileService {

    @Inject
    private IProfileDAO profileDAO;

    public ProfileService() {}

    public List<Profile> getProfiles() {
        return profileDAO.getProfiles();
    }

    public Profile getProfile(String userTag) {
        return profileDAO.getProfile(userTag);
    }

    public Profile getProfileByUserName(String userName) {
        return profileDAO.getProfileByUserName(userName);
    }

    public Profile AddProfile(String userTag, String userName, Image profilePicture, String bio, String location, String websiteURL) throws ProfileException {

        return profileDAO.AddProfile(userTag, userName, profilePicture, bio, location, websiteURL);
    }

    public void EditProfile(Profile profile) throws ProfileException {
        profileDAO.setUserName(profile.getUserTag(), profile.getUserName());
        profileDAO.setProfilePicture(profile.getUserTag(), profile.getProfilePicture());
        profileDAO.setBio(profile.getUserTag(), profile.getBio());
        profileDAO.setLocation(profile.getUserTag(), profile.getLocation());
        profileDAO.setWebsiteURL(profile.getUserTag(), profile.getWebsiteURL());
    }

    public boolean IsUniqueUserTag(String userTag) {
        return profileDAO.IsUniqueUserTag(userTag);
    }

    public void setRole(String userTag, Role role) throws ProfileException {
        profileDAO.setRole(userTag, role);
    }

    public void FollowOther(String userTag, String userTag2) {
        profileDAO.FollowOther(userTag, userTag2);
    }

    public void FollowMe(String userTag, String userTag2) {
        profileDAO.FollowMe(userTag, userTag2);
    }
}
