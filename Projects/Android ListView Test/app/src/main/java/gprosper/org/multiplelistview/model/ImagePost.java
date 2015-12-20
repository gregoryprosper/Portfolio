package gprosper.org.multiplelistview.model;

import android.graphics.Bitmap;

/**
 * Created by a on 12/20/15.
 */
public class ImagePost extends Post{
    private Bitmap image;
    private String caption;
    private int views;

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
