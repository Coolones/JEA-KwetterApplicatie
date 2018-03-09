package Domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Profile {

    private String userTag;
    private String userName;
    private Role role;
    private Image profilePicture;
    private String bio;
    private String location;
    private String websiteURL;
    private List<String> following;
    private List<String> followers;

    public Profile(String userTag, String userName, Role role, Image profilePicture, String bio, String location, String websiteURL) {

        if (userTag.isEmpty() || userName.isEmpty() || role.equals(null) || bio.length() > 160) {
            throw new IllegalArgumentException();
        }

        this.userTag = userTag;
        this.userName = userName;
        this.role = role;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.location = location;
        this.websiteURL = websiteURL;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public void FollowOther(String userTag) {

        if (!following.contains(userTag)) {
            following.add(userTag);
        }
    }

    public void FollowMe(String userTag) {

        if (!followers.contains(userTag)) {
            followers.add(userTag);
        }
    }

    public String getUserTag() {
        return userTag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public List<String> getFollowing() {
        return following;
    }

    public List<String> getFollowers() {
        return followers;
    }
}
