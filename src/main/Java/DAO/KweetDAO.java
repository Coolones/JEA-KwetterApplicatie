package DAO;

import Domain.Kweet;
import Domain.Profile;
import Domain.Trend;
import Exceptions.KweetException;
import iDAO.IKweetDAO;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.*;

@Stateless
@Default
public class KweetDAO implements IKweetDAO {

    private List<Kweet> kweets;
    private List<Trend> trends;

    public KweetDAO() {

        kweets = new ArrayList<>();
        trends = new ArrayList<>();
    }


    @Override
    public List<Kweet> getKweets() {
        return kweets;
    }

    @Override
    public List<Kweet> getKweetsFromUser(String userTag) {

        List<Kweet> userKweets = new ArrayList<>();

        for (Kweet kweet : kweets) {
            if (kweet.getOwner().getUserTag() == userTag) {
                userKweets.add(kweet);
            }
        }
        return userKweets;
    }

    @Override
    public List<Kweet> getTenKweetsFromUser(String ownerTag) {
        List<Kweet> kweets = getKweetsFromUser(ownerTag);
        return kweets.subList(kweets.size() - 11, kweets.size() - 1);
    }

    @Override
    public List<Trend> getMostPopularTrends() {

        Map<Integer, Trend> trendCount = new HashMap<>();

        for (Trend trend : trends) {
            trendCount.put(trend.getKweets().size(), trend);
        }

        List<Trend> sortedTrends = new ArrayList(new TreeMap(trendCount).values());

        return sortedTrends.subList(sortedTrends.size() - 11, sortedTrends.size() - 1);
    }

    @Override
    public List<Kweet> getKweetsByTrend(Trend trend) {

        return trend.getKweets();
    }

    @Override
    public Kweet AddKweet(int ID, Profile owner, String message, List<Profile> mentions, List<Trend> trends) throws KweetException {

        Kweet kweet = new Kweet(kweets.size(), owner, message, mentions, trends);

        //AddKweetToMaps(obj);

        return kweet;
    }

    @Override
    public Kweet getKweetByID(int ID) {

        for (Kweet kweet : kweets) {
            if (kweet.getID() == ID) return kweet;
        }

        return null;
    }

    @Override
    public void RemoveKweet(Kweet kweet) {

        kweets.remove(kweet);
    }
}
