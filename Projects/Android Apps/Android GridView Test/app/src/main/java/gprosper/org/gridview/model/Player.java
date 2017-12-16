package gprosper.org.gridview.model;

import android.graphics.Bitmap;

/**
 * Created by a on 12/28/15.
 */
public class Player {
    public static final String GUARD = "Guard";
    public static final String FORWARD = "Forward";
    public static final String FORWARD_CENTER = "Forward-Center";
    public static final String CENTER = "Center";

    private String firstName;
    private String lastname;
    private int jerseyNumber;
    private String position;
    Bitmap picture;

    public Player(String firstName, String lastName, int jerseyNumber, String position, Bitmap picture) {
        this.firstName = firstName;
        this.lastname = lastName;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
        this.picture = picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public String getPostition() {
        return position;
    }
}
