package Domain;

import Domain.Models.ProfileModel;
import Exceptions.ProfileException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    private Role role;
    private byte[] profilePicture;
    private String bio;
    private String location;
    private String websiteURL;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "owner")
    @LazyCollection(LazyCollectionOption.FALSE)
    @XmlTransient
    @JsonIgnore
    private List<Kweet> kweets;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @XmlTransient
    @JsonIgnore
    private List<Profile> following;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "following")
    @LazyCollection(LazyCollectionOption.FALSE)
    @XmlTransient
    @JsonIgnore
    private List<Profile> followers;

    public Profile() {}

    public Profile(String userTag) {
        this.userTag = userTag;
    }

    public Profile(ProfileModel profile) {
        this.email = profile.getEmail();
        this.password = profile.getPassword();
        this.userTag = profile.getUserTag();
        this.userName = profile.getUserName();
        this.role = Role.PROFILE;
        this.profilePicture = profile.getProfilePicture();
        this.bio = profile.getBio();
        this.location = profile.getLocation();
        this.websiteURL = profile.getWebsiteURL();
    }

    public Profile(String email, String password, String userTag, String userName, Role role, byte[] profilePicture, String bio, String location, String websiteURL) throws ProfileException {

        if (userTag.isEmpty() || userName.isEmpty() || bio.length() > 160) {
            throw new ProfileException("Please make sure everything is filled in correctly");
        }

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

    public Profile(String email, String password, String userTag, String userName, byte[] profilePicture, String bio, String location, String websiteURL) throws ProfileException {

        if (userTag.isEmpty() || userName.isEmpty() || bio.length() > 160) {
            throw new ProfileException("Please make sure everything is filled in correctly");
        }

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


    public Profile(int ID, String email, String password, String userTag, String userName, Role role, byte[] profilePicture, String bio, String location, String websiteURL) throws ProfileException {

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

    public Profile(int ID, String email, String password, String userTag, String userName, byte[] profilePicture, String bio, String location, String websiteURL) throws ProfileException {

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
            kweet.setOwner(this);
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

    private void FollowMe(Profile profile) {

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

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    private void setProfilePicture(byte[] profilePicture) {
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
