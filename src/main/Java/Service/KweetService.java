package Service;

import Domain.Kweet;
import Domain.Profile;
import Domain.Role;
import Domain.Trend;
import Exceptions.KweetException;
import iDAO.IKweetDAO;
import iDAO.IProfileDAO;
import iDAO.JPA;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.*;

@Stateless
public class KweetService implements Serializable {

    @Inject
    @JPA
    private IKweetDAO kweetDAO;

    @Inject
    @JPA
    private IProfileDAO profileDAO;

    public KweetService() {}

    public List<Kweet> getKweets() {
        return kweetDAO.getKweets();
    }

    public List<Kweet> getKweetsFromUser(String ownerTag) {
        return kweetDAO.getKweetsFromUser(ownerTag);
    }

    public List<Kweet> getTenKweetsFromUser(String ownerTag) {
        return kweetDAO.getTenKweetsFromUser(ownerTag);
    }

    public List<Trend> getMostPopularTrends() {
        return kweetDAO.getMostPopularTrends();
    }

    public List<Kweet> getKweetsByTrend(String trend) {
        Trend trendObj = kweetDAO.getTrendByTag(trend);

        if (trendObj != null) {
            return kweetDAO.getKweetsByTrend(trendObj);
        }
        else return new ArrayList<>();
    }

    public Kweet AddKweet(String ownerTag, String message) throws KweetException {

        if (profileDAO.IsUniqueUserTag(ownerTag) || message.isEmpty() || message.length() > 140) {
            throw new KweetException("Something went wrong");
        }
        List<Profile> mentions = getMentionsByKweet(message);
        List<Trend> trends = getTrendsByKweet(message);
        Kweet kweet = kweetDAO.AddKweet(profileDAO.getProfile(ownerTag), message, mentions, trends);

        return kweet;
    }

    public Kweet getKweetByID(int ID) {
        return kweetDAO.getKweetByID(ID);
    }

    public void RemoveKweet(String removerEmail, int ID) {

        Profile removerProfile = profileDAO.getProfileByEmail(removerEmail);

        if (removerProfile.getEmail() == getKweetByID(ID).getOwner().getEmail() ||
                removerProfile.getRole().equals(Role.MODERATOR) ||
                removerProfile.getRole().equals(Role.ADMINISTRATOR)) {

            profileDAO.removeKweet(getKweetByID(ID).getOwner().getUserTag(),getKweetByID(ID));
            kweetDAO.RemoveKweet(getKweetByID(ID));
        }
    }

    public void AppreciateKweet(String myEmail, int kweetID) {
        kweetDAO.AppreciateKweet(getKweetByID(kweetID), profileDAO.getProfileByEmail(myEmail));
    }

    private List<Profile> getMentionsByKweet(String kweet) {

        List<Profile> mentions = new ArrayList<>();

        for (String word : kweet.split(" ")) {
            if (word.startsWith("@")) {
                if(!profileDAO.IsUniqueUserTag(word)) {
                    mentions.add(profileDAO.getProfile(word));
                }
            }
        }
        return mentions;
    }

    private List<Trend> getTrendsByKweet(String kweet) {

        List<Trend> trends = new ArrayList<>();

        for (String word : kweet.split(" ")) {
            if (word.startsWith("#")) {
                if (kweetDAO.getTrendByTag(word) == null) {
                    trends.add(kweetDAO.AddTrend(word));
                }
                trends.add(kweetDAO.getTrendByTag(word));
            }
        }
        return trends;
    }

    public List<Kweet> getTimelineFromUser(String userTag) {

        Profile profile = profileDAO.getProfile(userTag);

        List<Kweet> timeline = new ArrayList<>();

        for (Kweet kweet : getKweets()) {
            if (kweet.getOwner().equals(profile) || kweet.getOwner().getFollowers().contains(profile)) {
                timeline.add(kweet);
            }
        }
        return timeline;
    }

    public List<Kweet> getMentionsOfUser(String userTag) {

        Profile profile = profileDAO.getProfile(userTag);

        List<Kweet> timeline = new ArrayList<>();

        for (Kweet kweet : getKweets()) {
            if (kweet.getMentions().contains(profile)) {
                timeline.add(kweet);
            }
        }
        return timeline;
    }

    public List<Kweet> findKweets(String find) {
        List<Kweet> found = new ArrayList<>();

        for (Kweet kweet : getKweets()) {
            if (kweet.getKweet().toLowerCase().contains(find.toLowerCase())) {
                found.add(kweet);
            }
        }
        return found;
    }
}
