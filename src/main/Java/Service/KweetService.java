package Service;

import Domain.Kweet;
import Domain.Profile;
import Domain.Trend;
import Exceptions.KweetException;
import iDAO.IKweetDAO;
import iDAO.IProfileDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class KweetService {

    @Inject
    private IKweetDAO kweetDAO;

    @Inject
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
        return kweetDAO.getKweetsByTrend(kweetDAO.getTrendByTag(trend));
    }

    public Kweet AddKweet(String ownerTag, String message) throws KweetException {
        if (!profileDAO.IsUniqueUserTag(ownerTag)) {
            List<Profile> mentions = getMentionsByKweet(message);
            List<Trend> trends = getTrendsByKweet(message);
            Kweet kweet = kweetDAO.AddKweet(profileDAO.getProfile(ownerTag), message, mentions, trends);

            for (Trend trend : kweet.getTrends()) {
                trend.AddKweet(kweet);
            }

            kweet.getOwner().AddKweet(kweet);

            return kweet;
        }
        return null;
    }

    public Kweet getKweetByID(int ID) {
        return kweetDAO.getKweetByID(ID);
    }

    public void RemoveKweet(Kweet kweet) {
        kweetDAO.RemoveKweet(kweet);
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
}
