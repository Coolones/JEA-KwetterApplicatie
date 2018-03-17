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

    /*public void setRole(String userTag, Role role) throws ProfileException {
        profileDAO.setRole(userTag, role);
    }

    public void FollowOther(String userTag, String userTag2) {
        profileDAO.FollowOther(userTag, userTag2);
    }

    public void FollowMe(String userTag, String userTag2) {
        profileDAO.FollowMe(userTag, userTag2);
    }*/
}
