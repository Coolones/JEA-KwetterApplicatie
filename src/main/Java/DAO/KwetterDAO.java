package DAO;

import Domain.Kweet;
import Domain.Profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KwetterDAO {

    private List<Profile> profiles;
    private Map<String, List<Kweet>> userKweets;
    private Map<String, List<Integer>> trends;

    public KwetterDAO() {

        profiles = new ArrayList<Profile>();
        userKweets = new HashMap<String, List<Kweet>>();
        trends = new HashMap<String, List<Integer>>();
    }
}
