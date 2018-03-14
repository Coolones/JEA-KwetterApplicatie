package Domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Role implements Serializable {

    private String role;
    private boolean canDeleteOthers;
    private boolean canGiveRights;
    private boolean canBlockUsers;

    public Role() {}

    public Role(String role, boolean canDeleteOthers, boolean canGiveRights, boolean canBlockUsers) {

        if (role.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.role = role;
        this.canDeleteOthers = canDeleteOthers;
        this.canGiveRights = canGiveRights;
        this.canBlockUsers = canBlockUsers;
    }

    public String getRole() {
        return role;
    }

    public boolean isCanDeleteOthers() {
        return canDeleteOthers;
    }

    public boolean isCanGiveRights() {
        return canGiveRights;
    }

    public boolean isCanBlockUsers() {
        return canBlockUsers;
    }
}
