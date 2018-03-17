package Domain;

import Exceptions.ProfileException;

import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Profile implements Serializable {

    private int ID;
    private String userTag;
    private String userName;
    private Role role;
    private Image profilePicture;
    private String bio;
    private String location;
    private String websiteURL;
    private List<Kweet> kweets;
    private List<Profile> following;
    private List<Profile> followers;

    public Profile() {}

    public Profile(Profile profile) throws ProfileException {

        if (profile.getID() < 0 || profile.getRole() == null || profile.getUserTag().isEmpty() || profile.getUserName().isEmpty() || profile.getBio().length() > 160) {
            throw new ProfileException("Please make sure everything is filled in corectly");
        }

        this.ID = profile.getID();
        this.userTag = profile.getUserTag();
        this.userName = profile.getUserName();
        this.role = profile.getRole();
        this.profilePicture = profile.getProfilePicture();
        this.bio = profile.getBio();
        this.location = profile.getLocation();
        this.websiteURL = profile.getWebsiteURL();
        this.kweets = new ArrayList<>();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public Profile(int ID, String userTag, String userName, Role role, Image profilePicture, String bio, String location, String websiteURL) throws ProfileException {

        if (ID < 0 || role == null || userTag.isEmpty() || userName.isEmpty() || bio.length() > 160) {
            throw new ProfileException("Please make sure everything is filled in corectly");
        }

        this.ID = ID;
        this.userTag = userTag;
        this.userName = userName;
        this.role = role;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.location = location;
        this.websiteURL = websiteURL;
        this.kweets = new ArrayList<>();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public void AddKweet(Kweet kweet) {
        kweets.add(kweet);
    }

    public void FollowOther(Profile profile) {

        if (!following.contains(profile)) {
            following.add(profile);
        }
    }

    public void FollowMe(Profile profile) {

        if (!followers.contains(profile)) {
            followers.add(profile);
        }
    }

    public Profile EditProfile(Profile profile) throws ProfileException {

        setUserName(profile.getUserName());
        setProfilePicture(profile.getProfilePicture());
        setBio(profile.getBio());
        setLocation(profile.getLocation());
        setWebsiteURL(profile.getWebsiteURL());

        return this;
    }

    public int getID() {
        return ID;
    }

    public String getUserTag() {
        return userTag;
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) throws ProfileException {
        if (role.equals(null)) throw new ProfileException("Role is empty");
        this.role = role;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    private void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    private void setBio(String bio) throws ProfileException {
        if (bio.length() > 160) throw new ProfileException("Your bio is to damm long");
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    private void setLocation(String location) {
        this.location = location;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    private void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public List<Profile> getFollowing() {
        return following;
    }

    public List<Profile> getFollowers() {
        return followers;
    }
}
