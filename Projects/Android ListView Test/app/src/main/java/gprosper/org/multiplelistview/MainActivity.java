package gprosper.org.multiplelistview;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

import gprosper.org.multiplelistview.model.ImagePost;
import gprosper.org.multiplelistview.model.Post;
import gprosper.org.multiplelistview.model.TextPost;
import gprosper.org.multiplelistview.model.User;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    ArrayList<Post> posts = new ArrayList<>();
    PostArrayAdapter postArrayAdapter;

    AbsListView.OnScrollListener postScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (firstVisibleItem >= 1){
                postArrayAdapter.unFocusStatusEditText();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        createUser();
        addPosts();


        postArrayAdapter = new PostArrayAdapter(this, posts);
        listView.setAdapter(postArrayAdapter);
        listView.setOnScrollListener(postScrollListener);
    }

    @Override
    public void onBackPressed() {
        if (!postArrayAdapter.unFocusStatusEditText()){
            super.onBackPressed();
        }
    }

    private void createUser() {
        //Create User

        User user = User.getSharedUser();
        user.setName("Gregory Prosper");

        Bitmap UserPic = ((BitmapDrawable) getResources().getDrawable(R.drawable.greg_pic)).getBitmap();
        user.setProfilePic(UserPic);
    }

    private void addPosts() {
        //Post 1
        TextPost kennysPost = new TextPost();
        kennysPost.setProfileName("Kenny Prosper");
        kennysPost.setStatusText("On my way like Ice Berg!");
        kennysPost.setStatusTime("29 mins");
        kennysPost.setLikes(12);
        kennysPost.setComments(3);
        kennysPost.setShares(2);
        Bitmap kennyProfilePic = ((BitmapDrawable) getResources().getDrawable(R.drawable.kenny_pic)).getBitmap();
        kennysPost.setProfilePic(kennyProfilePic);

        //Post 2
        ImagePost gregsPost = new ImagePost();
        gregsPost.setProfileName("Gregory Prosper");
        gregsPost.setCaption("Miami this Weekend!");
        gregsPost.setStatusTime("1 hr");
        gregsPost.setLikes(67);
        gregsPost.setComments(123);
        gregsPost.setViews(403);
        gregsPost.setShares(97);

        Bitmap gregsProfilePic = ((BitmapDrawable) getResources().getDrawable(R.drawable.greg_pic)).getBitmap();
        gregsPost.setProfilePic(gregsProfilePic);

        Bitmap postImage = ((BitmapDrawable) getResources().getDrawable(R.drawable.post)).getBitmap();
        gregsPost.setImage(postImage);

        //Post 3
        TextPost wilnersPost = new TextPost();
        wilnersPost.setProfileName("Wilner Prosper");
        wilnersPost.setStatusText("Can't wait for the Heat game tonight!!!");
        wilnersPost.setStatusTime("2 hr");
        wilnersPost.setLikes(123);
        wilnersPost.setComments(39);
        wilnersPost.setShares(93);

        Bitmap wilnerProfilePic = ((BitmapDrawable) getResources().getDrawable(R.drawable.wilner)).getBitmap();
        wilnersPost.setProfilePic(wilnerProfilePic);

        //Post 4
        ImagePost midelinesPost = new ImagePost();
        midelinesPost.setProfileName("Mideline Jean");
        midelinesPost.setCaption("J.Cole!!!!!!");
        midelinesPost.setStatusTime("3 hr");
        midelinesPost.setLikes(90);
        midelinesPost.setShares(34);
        midelinesPost.setComments(48);
        midelinesPost.setViews(231);

        Bitmap midelinesProfilePic = ((BitmapDrawable) getResources().getDrawable(R.drawable.mideline_pic)).getBitmap();
        midelinesPost.setProfilePic(midelinesProfilePic);

        Bitmap postImage2 = ((BitmapDrawable) getResources().getDrawable(R.drawable.post2)).getBitmap();
        midelinesPost.setImage(postImage2);

        //Add post to ArrayList
        posts.add(kennysPost);
        posts.add(gregsPost);
        posts.add(wilnersPost);
        posts.add(midelinesPost);
    }
}
