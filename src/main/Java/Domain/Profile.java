package Domain;

import Exceptions.ProfileException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Profile implements Serializable {

    @XmlTransient
    @JsonIgnore
    private int ID;

    @XmlTransient
    @JsonIgnore
    private String email;
    @XmlTransient
    @JsonIgnore
    private String password;

    private String userTag;
    private String userName;
    private Role role;
    private Image profilePicture;
    private String bio;
    private String location;
    private String websiteURL;

    @XmlTransient
    @JsonIgnore
    private List<Kweet> kweets;

    @XmlTransient
    @JsonIgnore
    private List<Profile> following;

    @XmlTransient
    @JsonIgnore
    private List<Profile> followers;

    public Profile() {}

    public Profile(int ID, String email, String password, String userTag, String userName, Role role, Image profilePicture, String bio, String location, String websiteURL) throws ProfileException {

        if (ID < 0 || userTag.isEmpty() || userName.isEmpty() || bio.length() > 160) {
            throw new ProfileException("Please make sure everything is filled in correctly");
        }

        this.ID = ID;
        this.email = email;
        this.password = password;
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

    public Profile(int ID, String email, String password, String userTag, String userName, Image profilePicture, String bio, String location, String websiteURL) throws ProfileException {

        if (ID < 0 || userTag.isEmpty() || userName.isEmpty() || bio.length() > 160) {
            throw new ProfileException("Please make sure everything is filled in correctly");
        }

        this.ID = ID;
        this.email = email;
        this.password = password;
        this.userTag = userTag;
        this.userName = userName;
        this.role = Role.PROFILE;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.location = location;
        this.websiteURL = websiteURL;
        this.kweets = new ArrayList<>();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public void AddKweet(Kweet kweet) {
        if (kweet != null) {
            kweets.add(kweet);
        }
    }

    public void RemoveKweet(Kweet kweet) {
        if (kweets.contains(kweet)) {
            kweets.remove(kweet);
        }
    }

    public void FollowOther(Profile profile) {

        if (!following.contains(profile)) {
            following.add(profile);
            profile.FollowMe(this);
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

    public boolean Authenticate(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public int getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setRole(Role role) {
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
        if (bio.length() > 160) throw new ProfileException("Your bio is longer then 160 characters");
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
