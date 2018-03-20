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

    public Profile getProfile(int id) {
        return profileDAO.getProfile(id);
    }

    public Profile getProfile(String userTag) {
        return profileDAO.getProfile(userTag);
    }

    public Profile getProfileByUserName(String userName) {
        return profileDAO.getProfileByUserName(userName);
    }

    public Profile AddProfile(Profile profile) throws ProfileException {

        if (!IsUniqueUserTag(profile.getUserTag()) || profile.getUserTag().isEmpty() || profile.getUserName().isEmpty() || profile.getBio().length() > 160) {
            throw new ProfileException("Please make sure everything is filled in correctly");
        }
        return profileDAO.AddProfile(profile);
    }

    public void EditProfile(Profile profile) throws ProfileException {
        if (IsUniqueUserTag(profile.getUserTag()) || profile.getUserTag().isEmpty() || profile.getUserName().isEmpty() || profile.getBio().length() > 160) {
            throw new ProfileException("Please make sure everything is filled in correctly");
        }
        profileDAO.EditProfile(profile);
    }

    public boolean IsUniqueUserTag(String userTag) {
        return profileDAO.IsUniqueUserTag(userTag);
    }

    public void setRole(int changerID, String userTag, String role) throws ProfileException {

        if (getProfile(changerID).getRole() == Role.ADMINISTRATOR && !IsUniqueUserTag(userTag)) {
            Role enumRole;

            switch (role) {
                case "MODERATOR": {
                    enumRole = Role.MODERATOR;
                    break;
                }
                case "ADMINISTRATOR": {
                    enumRole = Role.ADMINISTRATOR;
                    break;
                }
                default: {
                    enumRole = Role.PROFILE;
                }
            }
            profileDAO.setRole(getProfile(userTag), enumRole);
        }
    }

    public void FollowProfile(String myUserTag, String otherUserTag) {
        if (!IsUniqueUserTag(myUserTag) && !IsUniqueUserTag(otherUserTag)) {
            profileDAO.FollowProfile(getProfile(myUserTag), getProfile(otherUserTag));
        }
    }

    public List<Profile> getFollowing(String userTag) {
        return profileDAO.getFollowing(userTag);
    }

    public List<Profile> getFollowers(String userTag) {
        return profileDAO.getFollowers(userTag);
    }

    public void removeProfile(int id) {
        if (getProfile(id) != null) {
            profileDAO.removeProfile(getProfile(id));
        }
    }

    public Profile Authenticate(String email, String password) {
        for (Profile profile : getProfiles()) {
            if (profile.Authenticate(email, password)) return profile;
        }
        return null;
    }
}
