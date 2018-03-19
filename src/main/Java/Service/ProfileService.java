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

    public Profile AddProfile(Profile profile) throws ProfileException {

        return profileDAO.AddProfile(profile);
    }

    public void EditProfile(Profile profile) throws ProfileException {
        profileDAO.EditProfile(profile);
    }

    public boolean IsUniqueUserTag(String userTag) {
        return profileDAO.IsUniqueUserTag(userTag);
    }

    public void setRole(String userTag, Role role) throws ProfileException {
        if (!IsUniqueUserTag(userTag)) {
            profileDAO.setRole(getProfile(userTag), role);
        }
    }

    public void FollowProfile(String myUserTag, String otherUserTag) {
        if (!IsUniqueUserTag(myUserTag) && !IsUniqueUserTag(otherUserTag)) {
            profileDAO.FollowProfile(getProfile(myUserTag), getProfile(otherUserTag));
        }
    }
}
