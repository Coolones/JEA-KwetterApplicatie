package Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kweet {

    private int kweetID;
    private String ownerTag;
    private String kweet;
    private Date postDate;
    private List<String> mentions;
    private List<String> trends;
    private List<String> appreciatedBy;

    public Kweet(int kweetID, String ownerTag, String kweet) {

        if (ownerTag.isEmpty() || kweet.isEmpty() || kweet.length() > 140) {
            throw new IllegalArgumentException();
        }

        this.kweetID = kweetID;
        this.ownerTag = ownerTag;
        this.kweet = kweet;
        this.postDate = new Date();
        this.mentions = getReferencesByTag(kweet, "@");
        this.trends = getReferencesByTag(kweet, "#");
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

    public void AppreciatieKweet(String userTag) {

        if (!appreciatedBy.contains(userTag)) {
            appreciatedBy.add(userTag);
        }
    }

    public int getKweetID() {
        return kweetID;
    }

    public String getOwnerTag() {
        return ownerTag;
    }

    public void setOwnerTag(String ownerTag) {
        this.ownerTag = ownerTag;
    }

    public String getKweet() {
        return kweet;
    }

    public void setKweet(String kweet) {
        this.kweet = kweet;
    }

    public Date getPostDate() {
        return postDate;
    }

    public List<String> getMentions() {
        return mentions;
    }

    public List<String> getTrends() {
        return trends;
    }

    public List<String> getAppreciatedBy() {
        return appreciatedBy;
    }
}
