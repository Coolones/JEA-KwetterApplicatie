package Domain;

import Exceptions.KweetException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Kweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    private Profile owner;
    private String kweet;
    private Date postDate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "kweet_mention")
    @LazyCollection(LazyCollectionOption.FALSE)
    @XmlTransient
    @JsonIgnore
    private List<Profile> mentions;
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy ="kweets")
    @LazyCollection(LazyCollectionOption.FALSE)
    @XmlTransient
    @JsonIgnore
    private List<Trend> trends;
    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name = "kweet_appreciate")
    @LazyCollection(LazyCollectionOption.FALSE)
    @XmlTransient
    @JsonIgnore
    private List<Profile> appreciatedBy;

    public Kweet() {}

    public Kweet(String kweet) {
        this.kweet = kweet;
    }

    public Kweet(String kweet, List<Profile> mentions) throws KweetException {

        if (kweet.isEmpty() || kweet.length() > 140) {
            throw new KweetException("Something went wrong");
        }

        this.kweet = kweet;
        this.postDate = new Date();
        this.mentions = mentions;
        this.trends = new ArrayList<>();
        this.appreciatedBy = new ArrayList<>();
    }

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

        owner.AddKweet(this);
    }

    public void AppreciateKweet(Profile profile) {

        if (!appreciatedBy.contains(profile)) {
            appreciatedBy.add(profile);
        }
    }

    public void AddTrend(Trend trend) {
        trends.add(trend);
    }

    public int getID() {
        return ID;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
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

    public void Remove() {
        for (Trend trend : trends) {
            trend.RemoveKweet(this);
        }
        owner.RemoveKweet(this);
    }
}
