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

    public void setUserName(String userTag, String userName) {
        profileDAO.setUserName(userTag, userName);
    }

    public void setRole(String userTag, Role role) throws ProfileException {
        profileDAO.setRole(userTag, role);
    }

    public void setProfilePicture(String userTag, Image profilePicture) {
        profileDAO.setProfilePicture(userTag, profilePicture);
    }

    public void setBio(String userTag, String bio) throws ProfileException {
        profileDAO.setBio(userTag, bio);
    }

    public void setLocation(String userTag, String location) {
        profileDAO.setLocation(userTag, location);
    }

    public void setWebsiteURL(String userTag, String websiteURL) {
        profileDAO.setWebsiteURL(userTag, websiteURL);
    }

    public void FollowOther(String userTag, String userTag2) {
        profileDAO.FollowOther(userTag, userTag2);
    }

    public void FollowMe(String userTag, String userTag2) {
        profileDAO.FollowMe(userTag, userTag2);
    }
}
