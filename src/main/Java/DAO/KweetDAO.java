package DAO;

import Domain.Kweet;
import iDAO.IKweetDAO;

import java.util.*;

public class KweetDAO implements IKweetDAO {

    private Map<Integer, Kweet> kweetID;
    private Map<String, List<Kweet>> userKweets;
    private Map<String, List<Integer>> trends;

    public KweetDAO() {

        kweetID = new HashMap<>();
        userKweets = new HashMap<>();
        trends = new HashMap<>();
    }


    @Override
    public List<Kweet> getKweets() {
        return new ArrayList<Kweet>(kweetID.values());
    }

    @Override
    public List<Kweet> getKweetsFromUser(String ownerTag) {
        return userKweets.get(ownerTag);
    }

    @Override
    public List<Kweet> getTenKweetsFromUser(String ownerTag) {
        List<Kweet> kweets = userKweets.get(ownerTag);
        return kweets.subList(kweets.size() - 11, kweets.size() - 1);
    }

    @Override
    public List<String> getMostPopularTrends() {

        Map<Integer, String> trendCount = new HashMap<>();

        for (Map.Entry<String, List<Integer>> trend : trends.entrySet()) {
            trendCount.put(trend.getValue().size(), trend.getKey());
        }

        List<String> sortedTrends = new ArrayList(new TreeMap(trendCount).values());

        return sortedTrends.subList(sortedTrends.size() - 11, sortedTrends.size() - 1);
    }

    @Override
    public List<Kweet> getKweetsByTrend(String trend) {

        List<Kweet> kweets = new ArrayList<>();

        for (int kweet : trends.get(trend)) {
            kweets.add(kweetID.get(kweet));
        }

        return kweets;
    }

    @Override
    public Kweet AddKweet(String ownerTag, String kweet) throws IllegalArgumentException {

        Kweet obj = new Kweet(kweetID.size() + 1, ownerTag, kweet);

        AddKweetToMaps(obj);

        return obj;
    }

    @Override
    public Kweet getKweetByID(int ID) {
        return kweetID.get(ID);
    }

    @Override
    public void RemoveKweet(Kweet kweet) {

        kweetID.remove(kweet.getKweetID());
        userKweets.get(kweet.getOwnerTag()).remove(kweet);

        for (String trend : kweet.getTrends()) {
            trends.get(trend).remove(kweet);
        }
    }

    private void AddKweetToMaps (Kweet kweet) {

        if (kweetID.get(kweet.getKweetID()) != null) {
            kweetID.put(kweet.getKweetID(), kweet);
        }

        if (userKweets.get(kweet.getOwnerTag()) == null) {
            userKweets.put(kweet.getOwnerTag(), new ArrayList<Kweet>());
        }
        userKweets.get(kweet.getOwnerTag()).add(kweet);

        for (String trend : kweet.getTrends()) {
            if (trends.get(trend) == null) {
                trends.put(trend, new ArrayList<Integer>());
            }
            trends.get(trend).add(kweet.getKweetID());
        }
    }
}
