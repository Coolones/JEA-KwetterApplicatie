package Domain.Models;

import Domain.Role;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProfileModel {

    private String email;
    private String password;

    private String userTag;
    private String userName;
    private byte[] profilePicture;
    private String bio;
    private String location;
    private String websiteURL;

    public ProfileModel() {}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserTag() {
        return userTag;
    }

    public String getUserName() {
        return userName;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }
}
