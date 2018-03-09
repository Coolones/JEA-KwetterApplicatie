package Domain;

import java.util.Date;
import java.util.List;

public class Kweet {

    private int kweetID;
    private String ownerTag;
    private String kweet;
    private Date postDate;
    private List<String> mentions;
    private List<String> trends;
    private List<String> likes;
    private List<String> appreciatedBy;

    public Kweet(int kweetID, String ownerTag, String kweet, Date postDate, List<String> mentions, List<String> trends, List<String> likes, List<String> appreciatedBy) {

        this.kweetID = kweetID;
        this.ownerTag = ownerTag;
        this.kweet = kweet;
        this.postDate = postDate;
        this.mentions = mentions;
        this.trends = trends;
        this.likes = likes;
        this.appreciatedBy = appreciatedBy;
    }
}
