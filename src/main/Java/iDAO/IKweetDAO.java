package iDAO;

import Domain.Kweet;
import Exceptions.KweetException;

import java.util.List;

public interface IKweetDAO {

    List<Kweet> getKweets();

    List<Kweet> getKweetsFromUser(String ownerTag);

    List<Kweet> getTenKweetsFromUser(String ownerTag);

    List<String> getMostPopularTrends();

    List<Kweet> getKweetsByTrend(String trend);

    Kweet AddKweet(String ownerTag, String kweet) throws IllegalArgumentException, KweetException;

    Kweet getKweetByID(int ID);

    void RemoveKweet(Kweet kweet);
}
