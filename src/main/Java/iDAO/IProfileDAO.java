package iDAO;

import Domain.Profile;
import Domain.Role;

import java.awt.*;
import java.util.List;

public interface IProfileDAO {

    List<Profile> getProfiles();

    Profile getProfile(String userTag);

    Profile getProfileByUserName(String userName);

    Profile AddProfile(String userTag, String userName, Role role, Image profilePicture, String bio, String location, String websiteURL) throws IllegalArgumentException;

    boolean IsUniqueUserTag(String userTag);

    void setUserName(String userTag, String userName);

    void setRole(String userTag, Role role);

    void setProfilePicture(String userTag, Image profilePicture);

    void setBio(String userTag, String bio);

    void setLocation(String userTag, String location);

    void setWebsiteURL(String userTag, String websiteURL);

    void FollowOhter(String userTag, String userTag2);

    void FollowMe(String userTag, String userTag2);
}
