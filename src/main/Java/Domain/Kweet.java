package Domain;

import Exceptions.KweetException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Kweet implements Serializable {

    private int ID;
    private Profile owner;
    private String kweet;
    private Date postDate;

    @XmlTransient
    @JsonIgnore
    private List<Profile> mentions;
    @XmlTransient
    @JsonIgnore
    private List<Trend> trends;
    @XmlTransient
    @JsonIgnore
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
        this.appreciatedBy = new ArrayList<>();
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
