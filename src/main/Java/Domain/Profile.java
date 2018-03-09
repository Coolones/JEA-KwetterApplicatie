package Domain;

import java.awt.*;
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

    public Profile(String userTag, String userName, Role role, Image profilePicture, String bio, String location, String websiteURL, List<String> following, List<String> followers) {

        this.userTag = userTag;
        this.userName = userName;
        this.role = role;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.location = location;
        this.websiteURL = websiteURL;
        this.following = following;
        this.followers = followers;
    }
}
