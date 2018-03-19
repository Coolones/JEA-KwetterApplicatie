package iDAO;

import Domain.Kweet;
import Domain.Profile;
import Domain.Trend;
import Exceptions.KweetException;

import java.util.List;

public interface IKweetDAO {

    List<Kweet> getKweets();

    List<Kweet> getKweetsFromUser(String ownerTag);

    List<Kweet> getTenKweetsFromUser(String ownerTag);

    List<Trend> getTrends();

    Trend getTrendByTag(String trendTag);

    List<Trend> getMostPopularTrends();

    List<Kweet> getKweetsByTrend(Trend trend);

    Kweet AddKweet(Profile owner, String kweet, List<Profile> mentions, List<Trend> trends) throws IllegalArgumentException, KweetException;

    Trend AddTrend(String name);

    Kweet getKweetByID(int ID);

    void RemoveKweet(Kweet kweet);
}
