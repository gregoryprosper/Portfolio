package gprosper.org.multiplelistview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gprosper.org.multiplelistview.model.ImagePost;
import gprosper.org.multiplelistview.model.Post;
import gprosper.org.multiplelistview.model.TextPost;

/**
 * Created by a on 12/20/15.
 */
public class PostArrayAdapter extends ArrayAdapter<Post> {

    private static final int TYPE_TEXT_POST = 0;
    private static final int TYPE_IMAGE_POST = 1;
    private static final int TYPE_MAX_COUNT = TYPE_TEXT_POST + 1;

    public PostArrayAdapter(Context context, ArrayList<Post> posts){
        super(context,0,posts);
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        Post post = getItem(position);
        int type = 0;

        if (post instanceof ImagePost) {
            type = TYPE_IMAGE_POST;
        }
        return type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("PostArrayAdapter",String.valueOf(position));
        View row = convertView;
        int type = getItemViewType(position);

        if (row == null || type != (int) row.getTag(R.id.PostType)){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (type == TYPE_TEXT_POST){
                row = inflater.inflate(R.layout.status_post_row, parent, false);
                row.setTag(R.id.PostType, type);
            }
            else if (type == TYPE_IMAGE_POST){
                row = inflater.inflate(R.layout.image_post_row, parent, false);
                row.setTag(R.id.PostType, type);
            }
        }

        PostHolder postHolder = (PostHolder) row.getTag(R.id.PostHolder);

        if (postHolder == null){
            postHolder = new PostHolder(row);
            row.setTag(R.id.PostHolder, postHolder);
        }

        Post post = getItem(position);
        bindPostToHolder(post,postHolder);

        //Set Onclick Listeners for buttons
        ButtonOnClickListener buttonOnClickListener = new ButtonOnClickListener(post);
        postHolder.likeButton.setOnClickListener(buttonOnClickListener);
        postHolder.commentButton.setOnClickListener(buttonOnClickListener);
        postHolder.shareButton.setOnClickListener(buttonOnClickListener);

        //Set OnTouch Listeners for status info LinearLayout
        StatusInfoOnTouchListener statusInfoOnTouchListener = new StatusInfoOnTouchListener(postHolder.statusInfo, post);
        postHolder.statusInfo.setOnTouchListener(statusInfoOnTouchListener);

        return row;
    }

    private static void bindPostToHolder(TextPost post, PostHolder postHolder){
        postHolder.statusContentTextView.setText(post.getStatusText());
        postHolder.viewsTextView.setVisibility(View.INVISIBLE);
    }

    private static void bindPostToHolder(ImagePost post, PostHolder postHolder){
        postHolder.statusContentTextView.setText(post.getCaption());
        postHolder.statusPictureImageView.setImageBitmap(post.getImage());
        postHolder.viewsTextView.setText(String.format("%s views",post.getViews()));
    }

    private static void bindPostToHolder(Post post, PostHolder postHolder){
        //Bind Base post properties
        postHolder.profileNameTextView.setText(post.getProfileName());
        postHolder.profilePicImageView.setImageBitmap(post.getProfilePic());
        postHolder.statusTimeTextView.setText(post.getStatusTime());
        postHolder.commentsTextView.setText(String.format("%s comments",post.getComments()));
        postHolder.likesTextView.setText(String.format("%s likes",post.getLikes()));
        postHolder.sharesTextView.setText(String.format("%s shares",post.getShares()));

        //Bind Class Specific post properties
        if (post instanceof TextPost){
            bindPostToHolder(((TextPost) post),postHolder);
        }
        else if (post instanceof ImagePost){
            bindPostToHolder(((ImagePost) post),postHolder);
        }
    }

    private static class PostHolder{
        public ImageView profilePicImageView;
        public TextView profileNameTextView;
        public TextView statusTimeTextView;
        public TextView statusContentTextView;
        public ImageView statusPictureImageView;
        public TextView likesTextView;
        public TextView commentsTextView;
        public TextView sharesTextView;
        public TextView viewsTextView;
        public Button likeButton;
        public Button shareButton;
        public Button commentButton;
        public LinearLayout statusInfo;

        public PostHolder(View view){
            profilePicImageView = (ImageView) view.findViewById(R.id.profilePicImageView);
            profileNameTextView = (TextView) view.findViewById(R.id.profileNameTextView);
            statusTimeTextView = (TextView) view.findViewById(R.id.statusTimeTextView);
            statusContentTextView = (TextView) view.findViewById(R.id.statusContentTextView);
            statusPictureImageView = (ImageView) view.findViewById(R.id.statusPictureImageView);
            likesTextView = (TextView) view.findViewById(R.id.likesTextView);
            commentsTextView = (TextView) view.findViewById(R.id.commentsTextView);
            sharesTextView = (TextView) view.findViewById(R.id.sharesTextView);
            viewsTextView = (TextView) view.findViewById(R.id.viewsTextView);
            likeButton = (Button) view.findViewById(R.id.likeButton);
            shareButton = (Button) view.findViewById(R.id.shareButton);
            commentButton = (Button) view.findViewById(R.id.commentButton);
            statusInfo = (LinearLayout) view.findViewById(R.id.statusInfo);
        }
    }

    private class ButtonOnClickListener implements View.OnClickListener{
        private Post post;

        public ButtonOnClickListener(Post post){
            this.post = post;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.likeButton:
                    Toast.makeText(PostArrayAdapter.this.getContext(),"Like Button Clicked for " + post.getProfileName(), Toast.LENGTH_LONG).show();
                    break;
                case R.id.commentButton:
                    Toast.makeText(PostArrayAdapter.this.getContext(),"Comment Button Clicked for " + post.getProfileName(), Toast.LENGTH_LONG).show();
                    break;
                case R.id.shareButton:
                    Toast.makeText(PostArrayAdapter.this.getContext(),"Share Button Clicked for " + post.getProfileName(), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    private class StatusInfoOnTouchListener implements View.OnTouchListener{
        private LinearLayout statusInfo;
        private Post post;

        public StatusInfoOnTouchListener(LinearLayout statusInfo, Post post){
            this.statusInfo = statusInfo;
            this.post = post;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                statusInfo.setBackgroundColor(Color.LTGRAY);
                return true;
            }
            else if (event.getAction() == MotionEvent.ACTION_UP){
                Toast.makeText(PostArrayAdapter.this.getContext(),"Status Info Clicked for " + post.getProfileName(), Toast.LENGTH_LONG).show();
                statusInfo.setBackgroundColor(Color.TRANSPARENT);
                return true;
            }
            else if (event.getAction() == MotionEvent.ACTION_CANCEL){
                statusInfo.setBackgroundColor(Color.TRANSPARENT);
                return true;
            }
            else{
                return false;
            }
        }
    }
}
