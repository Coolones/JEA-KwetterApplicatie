package Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Trend implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String trend;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "trends")
    @XmlTransient
    @JsonIgnore
    private List<Kweet> kweets;

    public Trend() {}

    public Trend(String trend) {
        this.trend = trend;
        this.kweets = new ArrayList<>();
    }

    public Trend(int ID, String trend) {
        this.ID = ID;
        this.trend = trend;
        this.kweets = new ArrayList<>();
    }

    public void AddKweet(Kweet kweet) {
        kweets.add(kweet);
        kweet.AddTrend(this);
    }

    public void RemoveKweet(Kweet kweet) {
        kweets.remove(kweet);
    }

    public int getID() {
        return ID;
    }

    public String getTrend() {
        return trend;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }
}
