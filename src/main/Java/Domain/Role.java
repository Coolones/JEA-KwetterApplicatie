package Domain;

public class Role {

    private String role;
    private boolean canDeleteOthers;
    private boolean canGiveRights;
    private boolean canBlockUsers;

    public Role(String role, boolean canDeleteOthers, boolean canGiveRights, boolean canBlockUsers) {

        this.role = role;
        this.canDeleteOthers = canDeleteOthers;
        this.canGiveRights = canGiveRights;
        this.canBlockUsers = canBlockUsers;
    }
}
