package DAO;

import Domain.Kweet;
import Domain.Profile;
import Domain.Trend;
import Exceptions.KweetException;
import iDAO.IKweetDAO;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.*;

@Stateless
@Default
public class KweetDAO implements IKweetDAO {

    private List<Kweet> kweets;
    private List<Trend> trends;

    @PostConstruct
    public void init() {
        System.out.print("TestKweet");
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
        if (kweets.size() > 10) {
            return kweets.subList(kweets.size() - 11, kweets.size() - 1);
        }
        else return kweets;
    }

    @Override
    public List<Trend> getTrends() {
        return trends;
    }

    @Override
    public Trend getTrendByTag(String trendTag) {
        for (Trend trend : trends) {
            if (trend.getTrend().equals(trendTag)) return trend;
        }

        return null;
    }

    @Override
    public List<Trend> getMostPopularTrends() {

        Map<Integer, Trend> trendCount = new HashMap<>();

        for (Trend trend : trends) {
            trendCount.put(trend.getKweets().size(), trend);
        }

        List<Trend> sortedTrends = new ArrayList(new TreeMap(trendCount).values());

        if (sortedTrends.size() > 10) {
            return sortedTrends.subList(sortedTrends.size() - 11, sortedTrends.size() - 1);
        }
        else return sortedTrends;
    }

    @Override
    public List<Kweet> getKweetsByTrend(Trend trend) {

        return trend.getKweets();
    }

    @Override
    public Kweet AddKweet(Profile owner, String message, List<Profile> mentions, List<Trend> trends) throws KweetException {

        Kweet kweet = new Kweet(kweets.size(), owner, message, mentions, trends);

        kweets.add(kweet);

        return kweet;
    }

    @Override
    public Trend AddTrend(String name) {
        Trend trend = new Trend(trends.size(), name);

        trends.add(trend);

        return trend;
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
        kweet.Remove();
        kweets.remove(kweet);
    }

    @Override
    public void AppreciateKweet(Kweet kweet, Profile profile) {
        kweet.AppreciateKweet(profile);
    }
}
