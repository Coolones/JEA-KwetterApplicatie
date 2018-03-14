package Service;

import DAO.ProfileDAO;
import Domain.Kweet;
import Domain.Profile;
import Exceptions.KweetException;
import iDAO.IKweetDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class KweetService {

    //@Inject
    //private ProfileDAO profileDAO;

    @Inject
    private IKweetDAO kweetDAO;

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

    public List<String> getMostPopularTrends() {
        return kweetDAO.getMostPopularTrends();
    }

    public List<Kweet> getKweetsByTrend(String trend) {
        return kweetDAO.getKweetsByTrend(trend);
    }

    public Kweet AddKweet(String ownerTag, String kweet) throws KweetException {
        return kweetDAO.AddKweet(ownerTag, kweet);
    }

    public Kweet getKweetByID(int ID) {
        return kweetDAO.getKweetByID(ID);
    }

    public void RemoveKweet(Kweet kweet) {
        kweetDAO.RemoveKweet(kweet);
    }
}
