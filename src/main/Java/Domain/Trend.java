package Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Trend implements Serializable {

    private int ID;
    private String trend;
    @XmlTransient
    @JsonIgnore
    private List<Kweet> kweets;

    public Trend() {}

    public Trend(int ID, String trend) {
        this.ID = ID;
        this.trend = trend;
        this.kweets = new ArrayList<>();
    }

    public void AddKweet(Kweet kweet) {
        kweets.add(kweet);
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
