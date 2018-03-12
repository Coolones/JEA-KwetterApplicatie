package iDAO;

import Domain.Profile;
import Domain.Role;
import Exceptions.ProfileException;

import java.awt.*;
import java.util.List;

public interface IProfileDAO {

    List<Profile> getProfiles();

    Profile getProfile(String userTag);

    Profile getProfileByUserName(String userName);

    Profile AddProfile(String userTag, String userName, Image profilePicture, String bio, String location, String websiteURL) throws IllegalArgumentException, ProfileException;

    boolean IsUniqueUserTag(String userTag);

    void setUserName(String userTag, String userName);

    void setRole(String userTag, Role role) throws ProfileException;

    void setProfilePicture(String userTag, Image profilePicture);

    void setBio(String userTag, String bio) throws ProfileException;

    void setLocation(String userTag, String location);

    void setWebsiteURL(String userTag, String websiteURL);

    void FollowOther(String userTag, String userTag2);

    void FollowMe(String userTag, String userTag2);
}
