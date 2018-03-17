package Domain;

public class Heart {

    private int ID;
    private Kweet kweet;
    private Profile apreciatedBy;

    public Heart() {}

    public Heart(int ID, Kweet kweet, Profile apreciatedBy) {
        this.ID = ID;
        this.kweet = kweet;
        this.apreciatedBy = apreciatedBy;
    }

    public int getID() {
        return ID;
    }

    public Kweet getKweet() {
        return kweet;
    }

    public Profile getApreciatedBy() {
        return apreciatedBy;
    }
}
