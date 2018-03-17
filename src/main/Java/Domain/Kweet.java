package Domain;

import Exceptions.KweetException;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Kweet implements Serializable {

    private int ID;
    private Profile owner;
    private String kweet;
    private Date postDate;
    private List<Profile> mentions;
    private List<Trend> trends;
    private List<Profile> appreciatedBy;

    public Kweet() {}

    public Kweet(int ID, Profile owner, String kweet, List<Profile> mentions, List<Trend> trends) throws KweetException {

        if (owner == null || kweet.isEmpty() || kweet.length() > 140) {
            throw new KweetException("Something went wrong");
        }

        this.ID = ID;
        this.owner = owner;
        this.kweet = kweet;
        this.postDate = new Date();
        this.mentions = mentions;
        this.trends = trends;
        //this.mentions = getReferencesByTag(kweet, "@");
        //this.trends = getReferencesByTag(kweet, "#");
        this.appreciatedBy = new ArrayList<>();
    }

    private List<String> getReferencesByTag(String kweet, String tag) {

        List<String> references = new ArrayList<>();

        for (String word : kweet.split(" ")) {
            if (word.startsWith(tag) && !references.contains(word)) {
                references.add(word);
            }
        }

        return references;
    }

    public void AppreciateKweet(Profile profile) {

        if (!appreciatedBy.contains(profile)) {
            appreciatedBy.add(profile);
        }
    }

    public int getID() {
        return ID;
    }

    public Profile getOwner() {
        return owner;
    }

    public String getKweet() {
        return kweet;
    }

    public Date getPostDate() {
        return postDate;
    }

    public List<Profile> getMentions() {
        return mentions;
    }

    public List<Trend> getTrends() {
        return trends;
    }

    public List<Profile> getAppreciatedBy() {
        return appreciatedBy;
    }
}
