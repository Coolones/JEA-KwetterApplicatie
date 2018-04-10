package Beans.admin;

import Domain.Kweet;
import Service.KweetService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class AdminKweetBean implements Serializable {

    @Inject
    private KweetService kweetService;

    private List<Kweet> kweets;

    private String filter;
    private Kweet kweet;

    public List<Kweet> getKweets() {
        kweets = kweetService.getKweets();

        if (filter != null && filter.length() > 0) {
            List<Kweet> filtered = new ArrayList<>();
            for (Kweet kweet : kweets) {
                if (kweet.SearchKweet(filter.toLowerCase())) {
                    filtered.add(kweet);
                }
            }
            return filtered;
        }
        else return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public void removeKweet(Kweet kweet) {
        kweetService.RemoveKweet(1, kweet.getID());
        getKweets();
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Kweet getKweet() {
        return kweet;
    }

    public void setKweet(Kweet kweet) {
        this.kweet = kweet;
    }
}
