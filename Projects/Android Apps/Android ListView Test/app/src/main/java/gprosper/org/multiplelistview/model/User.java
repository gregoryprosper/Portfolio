package gprosper.org.multiplelistview.model;

import android.graphics.Bitmap;

/**
 * Created by a on 12/24/15.
 */
public class User {
    private static User sharedInstance = null;

    private String name;
    private Bitmap profilePic;

    protected User(){

    }

    public static User getSharedUser(){
        if (sharedInstance == null){
            sharedInstance = new User();
        }
        return sharedInstance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }
}
