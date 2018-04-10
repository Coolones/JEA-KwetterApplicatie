package Domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
public class ProfileGroup implements Serializable {

    @Id
    private String groupName;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.ALL)
    private List<Profile> profiles;*/

    public ProfileGroup() {}

    public ProfileGroup(String groupName) {
        this.groupName = groupName;
        //this.profiles = new ArrayList<>();
    }

    public String getName() {
        return groupName;
    }

    public void setName(String groupName) {
        this.groupName = groupName;
    }

    public List<Profile> getProfiles() {
        //return profiles;
        return null;
    }

    public void setProfiles(List<Profile> profiles) {
        //this.profiles = profiles;
    }
}
