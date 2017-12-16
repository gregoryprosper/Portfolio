package gprosper.org.multiplelistview.model;

import android.graphics.Bitmap;

/**
 * Created by a on 12/20/15.
 */
public abstract class Post {
    private String profileName;
    private Bitmap profilePic;
    private String statusTime;
    private int likes;
    private int comments;
    private int shares;


    public Bitmap getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getStatusTime() {
        return statusTime;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }

    public int getShares() {
        return shares;
    }
}
