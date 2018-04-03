package iDAO;

import Domain.Kweet;
import Domain.Profile;
import Domain.Role;
import Exceptions.ProfileException;

import java.awt.*;
import java.util.List;

public interface IProfileDAO {

    List<Profile> getProfiles();

    Profile getProfile(int id);

    Profile getProfile(String userTag);

    Profile getProfileByUserName(String userName);

    Profile AddProfile(Profile profile) throws ProfileException;

    Profile EditProfile(Profile profile) throws ProfileException;

    boolean IsUniqueUserTag(String userTag);

    void FollowProfile(Profile myProfile, Profile otherProfile);

    void setRole(Profile profile, Role role);

    List<Profile> getFollowing(String userTag);

    List<Profile> getFollowers(String userTag);

    void AddKweet(String userTag, Kweet kweet);

    void removeProfile(Profile profile);
}
